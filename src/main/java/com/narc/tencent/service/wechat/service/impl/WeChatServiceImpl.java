package com.narc.tencent.service.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.alimama.service.AlimamaService;
import com.narc.tencent.service.nlp.service.NlpService;
import com.narc.tencent.service.sms.bo.AddSmsTaskReq;
import com.narc.tencent.service.sms.enums.SmsTaskType;
import com.narc.tencent.service.sms.service.SmsService;
import com.narc.tencent.service.wechat.dao.service.CftPermissionDaoService;
import com.narc.tencent.service.wechat.dao.service.WxtMessageLogDaoService;
import com.narc.tencent.service.wechat.dao.service.WxtUserInfoDaoService;
import com.narc.tencent.service.wechat.dao.service.WxtUserRoleDaoService;
import com.narc.tencent.service.wechat.entity.CftPermission;
import com.narc.tencent.service.wechat.entity.WxtMessageLog;
import com.narc.tencent.service.wechat.entity.WxtUserInfo;
import com.narc.tencent.service.wechat.entity.WxtUserRole;
import com.narc.tencent.service.wechat.entity.message.TextMessage;
import com.narc.tencent.service.wechat.service.WeChatService;
import com.narc.tencent.utils.DateUtils;
import com.narc.tencent.utils.UuidUtils;
import com.narc.tencent.utils.WechatMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {

    private final AlimamaService alimamaService;
    private final WxtUserInfoDaoService wxtUserInfoDaoService;
    private final WxtUserRoleDaoService wxtUserRoleDaoService;
    private final CftPermissionDaoService cftPermissionDaoService;
    private final WxtMessageLogDaoService wxtMessageLogDaoService;

    public WeChatServiceImpl(AlimamaService alimamaService, WxtUserInfoDaoService wxtUserInfoDaoService, WxtUserRoleDaoService wxtUserRoleDaoService, CftPermissionDaoService cftPermissionDaoService, WxtMessageLogDaoService wxtMessageLogDaoService) {
        this.alimamaService = alimamaService;
        this.wxtUserInfoDaoService = wxtUserInfoDaoService;
        this.wxtUserRoleDaoService = wxtUserRoleDaoService;
        this.cftPermissionDaoService = cftPermissionDaoService;
        this.wxtMessageLogDaoService = wxtMessageLogDaoService;
    }

    @Autowired
    private NlpService nlpService;
    @Autowired
    private SmsService smsService;

    @Override
    public String processRequest(HttpServletRequest request) {
        String res = "";
        WxtMessageLog msgLog = new WxtMessageLog();
        try {
            Map<String, String> map = WechatMessageUtil.xmlToMap(request);
            String jsonStr = JSON.toJSONString(map);
            log.info("收到微信公众号请求:{}", jsonStr);
            msgLog = JSON.parseObject(jsonStr, WxtMessageLog.class);
            msgLog.setCreateTime(new Date(Long.parseLong(map.get("CreateTime") + "000")));
            //判断重复
            if (wxtMessageLogDaoService.isExist(msgLog.getFromUserName(), msgLog.getCreateTime())) {
                return "";
            }
            String fromUserName = msgLog.getFromUserName();
            String toUserName = msgLog.getToUserName();
            String content = msgLog.getContent();
            log.info("收到用户{}消息：{}", fromUserName, content);
            //获取用户信息
            WxtUserInfo userInfo = wxtUserInfoDaoService.getUserByOpenId(fromUserName);
            if (userInfo == null) {
                //新用户新增
                userInfo = initNewUser(fromUserName);
            }
            String rspContent = "暂不支持";
            switch (msgLog.getMsgType()) {
                case WechatMessageUtil.MESSAGE_EVENT:
                    rspContent = dealEvent(msgLog.getEvent(), userInfo);
                    break;
                case WechatMessageUtil.MESSAGE_TEXT:
                    rspContent = dealText(msgLog.getContent(), userInfo);
                    break;
                case WechatMessageUtil.MESSAGE_VOICE:
                    rspContent = dealVoice(msgLog, userInfo);
                    break;
                case WechatMessageUtil.MESSAGE_IMAGE:
                    rspContent = "暂不支持图片处理";
                    break;
                default:
                    break;
            }
            res = returnTextMessage(rspContent, fromUserName, toUserName);
            msgLog.setRtMsgContent(rspContent);
        } catch (Exception e) {
            log.error("", e);
        } finally {
            wxtMessageLogDaoService.insertOne(msgLog);
        }
        return res;
    }

    @Override
    public String dealText(String content, WxtUserInfo userInfo) {
        //获取用户所有的权限
        List<CftPermission> allPermissions = cftPermissionDaoService.selectAllByUserId(userInfo.getUserId());
        //判断是否是BASE指令
        List<CftPermission> basePermissionIds = allPermissions.stream()
                .filter(o -> "BASE".equals(o.getType()))
                .collect(Collectors.toList());
        List<CftPermission> patternCommands = allPermissions.stream()
                .filter(o -> "PATTERN".equals(o.getType()))
                .collect(Collectors.toList());
        String userPattern = userInfo.getPattern();

        CftPermission nowPattern = cftPermissionDaoService.selectByPermissionId(userPattern);
        //判断是否在修改昵称
        if (userPattern.startsWith("NAME") && userPattern.contains("|")) {
            if (content == null || content.length() < 1 || content.length() > 14) {
                return "请输入2-14个字符(中英文+数字)作为您的昵称，" +
                        "注意：修改昵称前请确保没有未返现的订单";
            }
            //判断重复
            if (wxtUserInfoDaoService.isExistName(content)) {
                return "该昵称已存在，请重新输入2-14个字符(中英文+数字)作为您的昵称";
            }
            String nextPattern = userPattern.split("\\|")[1];
            WxtUserInfo update = new WxtUserInfo();
            update.setUserId(userInfo.getUserId());
            update.setModifiedDatetime(new Date());
            update.setName(content);
            update.setPattern(nextPattern);
            wxtUserInfoDaoService.updateByPrimaryKeySelective(update);
            return "昵称设置成功！\r\n" + content + "您好！\r\n"
                    + getHelp(nextPattern);
        }

        for (CftPermission permission : basePermissionIds) {
            String pattern = permission.getCommand();
            boolean isMatch = Pattern.matches(pattern, content);
            if (!isMatch) {
                continue;
            }
            switch (permission.getPermissionId()) {
                case "CHANGE_PATTERN":
                    //切换当前模式
                    String nextPattern = content.substring(3);
                    //用户的所有口令

                    for (CftPermission cftPermission : patternCommands) {
                        if (nextPattern.equals(cftPermission.getPermissionName())) {
                            //更新用户模式
                            userInfo.setPattern(cftPermission.getPermissionId());
                            wxtUserInfoDaoService.updateByPrimaryKeySelective(userInfo);
                            return "切换成功，当前模式：" + cftPermission.getPermissionName();
                        }
                    }
                    return "切换失败，当前模式：" + nowPattern.getPermissionName();
                case "SHOW_PATTERN":
                    return "当前模式：" + nowPattern.getPermissionName();
                case "SHOW_ALL_COMMANDS":
                    //显示所有指令
                    StringBuilder sb = new StringBuilder();
                    sb.append("所有指令如下：").append("\r\n");
                    for (CftPermission p : basePermissionIds) {
                        if ("0".equals(p.getIsShow())) {
                            continue;
                        }
                        sb.append(p.getPermissionName())
                                .append("--")
                                .append(p.getCommandDesc())
                                .append("\r\n");
                    }
                    sb.append(nowPattern.getPermissionName())
                            .append("--")
                            .append(nowPattern.getCommandDesc());
                    return sb.toString();
                case "SHOW_ALL_PATTERNS":
                    //显示所有模式
                    sb = new StringBuilder();
                    sb.append("所有模式如下：").append("\r\n");
                    for (CftPermission p : patternCommands) {
                        if ("0".equals(p.getIsShow())) {
                            continue;
                        }
                        sb.append(p.getPermissionName())
                                .append("--")
                                .append(p.getCommandDesc())
                                .append("\r\n");
                    }
                    return sb.toString();
                case "SHOW_HELP":
                    //显示当前模式的使用说明
                    return getHelp(nowPattern.getPermissionId());
                case "SHOW_SMS_TASK":
                    //显示短信提醒任务
                    return getSmsTask(userInfo.getPhoneNo());
                case "CANCEL_SMS_TASK":
                    //取消短信提醒任务
                    return cancelSmsTask(userInfo.getPhoneNo(), content);
                case "CHANGE_NAME":
                    //设置昵称模式
                    userInfo.setPattern("NAME|" + userInfo.getPattern());
                    wxtUserInfoDaoService.updateByPrimaryKeySelective(userInfo);
                    return "请输入2-14个字符(中英文+数字)作为您的昵称，注意：修改昵称前请确保没有未返现的订单";
                default:
                    return "请输入正确的指令";
            }
        }

        //判断是否是模式指令
        switch (nowPattern.getPermissionId()) {
            case "TKL":
                return tranTkl(content, "NORMAL", userInfo.getUserId(), userInfo.getRemark());
            case "TKL_VIP":
                return tranTkl(content, "VIP", userInfo.getUserId(), userInfo.getRemark());
            case "TKL_NULL":
                return tranTkl(content, "NULL", userInfo.getUserId(), userInfo.getRemark());
            case "TKL_ADMIN":
                return dealTklAdmin(content, userInfo.getUserId(), userInfo.getRemark());
            case "CHAT":
                return "聊天模式暂未开放，敬请期待";
            case "CALC":
                return "计算模式暂未开放，敬请期待";
            default:
                return "请输入正确的指令";
        }
    }


    @Override
    public String dealVoice(WxtMessageLog msg, WxtUserInfo userInfo) {
        if (StringUtils.isBlank(userInfo.getPhoneNo())) {
            return "您尚未配置手机号码，请联系管理员配置";
        }
        String asrText = msg.getRecognition();
        if (StringUtils.isBlank(asrText)) {
            return "未识别出您的语音，请用标准话重说一次或稍候再试";
        }
        JSONObject nlpRes = nlpService.timeNlp(asrText);
        JSONArray timeList = nlpRes.getJSONArray("timeList");
        if (CollectionUtils.isEmpty(timeList)) {
            return "未识别出您的语音中的时间，请用标准话重说一次";
        }
        StringBuilder ansBuilder = new StringBuilder();
        String formatStr = nlpRes.getString("formatStr");
        ansBuilder.append("[").append(formatStr).append("]")
                .append("\r\n任务已设置完成，");
        List<AddSmsTaskReq> taskList = new ArrayList<>();
        int taskMaxNo = getSmsTaskMaxNo(userInfo.getPhoneNo());
        for (int i = 0; i < timeList.size(); i++) {
            taskMaxNo++;
            AddSmsTaskReq req = new AddSmsTaskReq();
            req.setTemplateId("899889");
            req.setPhoneNumberSet(JSON.toJSONString(Collections.singletonList(userInfo.getPhoneNo())));
            req.setExtDataA("" + taskMaxNo);
            req.setTemplateParam(JSON.toJSONString(Collections.singletonList("" + taskMaxNo)));
            JSONObject timeObj = timeList.getJSONObject(i);
            Date time = timeObj.getDate("time");
            boolean isCron = timeObj.getBoolean("isCron");
            String cron = timeObj.getString("cron");
            String expression = timeObj.getString("timeExpression");
            req.setExtDataB(formatStr.replace(expression, ""));
            if (isCron) {
                List<Date> next3Date = DateUtils.getNextExcTime(cron, 3);
                if (CollectionUtils.isEmpty(next3Date)) {
                    continue;
                }
                req.setSendTime(next3Date.get(0));
                req.setTaskType(SmsTaskType.CRON);
                req.setCronExpression(cron);
                ansBuilder.append("任务编号[").append(taskMaxNo).append("]下三次提醒时间:\r\n")
                        .append("[").append(DateUtils.convertDateToStr(next3Date.get(0), DateUtils.FORMAT_19)).append("]\r\n")
                        .append("[").append(DateUtils.convertDateToStr(next3Date.get(1), DateUtils.FORMAT_19)).append("]\r\n")
                        .append("[").append(DateUtils.convertDateToStr(next3Date.get(2), DateUtils.FORMAT_19)).append("]\r\n");
            } else {
                req.setTaskType(SmsTaskType.NO_CRON);
                req.setSendTime(time);
                ansBuilder.append("任务编号[").append(taskMaxNo).append("]提醒时间:\r\n")
                        .append("[").append(DateUtils.convertDateToStr(time, DateUtils.FORMAT_19)).append("]\r\n");
            }
            taskList.add(req);
        }
        boolean res = addTask(taskList);
        if (!res) {
            return "提醒任务添加失败，请稍后重试";
        }
        log.debug(JSON.toJSONString(taskList));
        return ansBuilder.toString();
    }

    private boolean addTask(List<AddSmsTaskReq> taskList) {
        JSONObject paramObj = new JSONObject();
        paramObj.put("list", taskList);
        JSONObject res = smsService.addSmsTask(paramObj.toJSONString());
        return "success".equals(res.getString("res"));
    }

    private int getSmsTaskMaxNo(String phoneNo) {
        List<AddSmsTaskReq> list = getSmsTaskList(phoneNo);
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        int max = 0;
        for (AddSmsTaskReq req : list) {
            try {
                int a = Integer.parseInt(req.getExtDataA());
                max = Math.max(max, a);
            } catch (Exception e) {
            }
        }
        return max;
    }

    private List<AddSmsTaskReq> getSmsTaskList(String phoneNo) {
        JSONObject req = new JSONObject();
        req.put("phoneNo", phoneNo);
        JSONObject res = smsService.getSmsTask(req.toJSONString());
        JSONArray list = res.getJSONArray("list");
        List<AddSmsTaskReq> resList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            resList.add(JSON.parseObject(list.getJSONObject(i).toJSONString(), AddSmsTaskReq.class));
        }
        return resList;
    }

    @Override
    public String dealEvent(String event, WxtUserInfo userInfo) {
        String rspContent = "暂不支持";
        switch (event) {
            case WechatMessageUtil.MESSAGE_EVENT_SUBSCRIBE:
                log.info("新用户订阅OPEN_ID:{}", userInfo.getOpenId());
                //订阅
                rspContent = "欢迎关注本公众号，请输入2-14个字符(中英文+数字)作为您的昵称";
                break;
            case WechatMessageUtil.MESSAGE_EVENT_UNSUBSCRIBE:
                //取消订阅
                break;
            default:
                break;
        }
        return rspContent;
    }

    private String returnTextMessage(String rspContent, String fromUserName, String toUserName) {
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setMsgType(WechatMessageUtil.MESSAGE_TEXT);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setContent(rspContent);
        String responseMessage = WechatMessageUtil.textMessageToXml(textMessage);
        log.info("返回文本消息:{}", responseMessage);
        return responseMessage;
    }


    private WxtUserInfo initNewUser(String openId) {
        log.info("新增用户：{}", openId);
        //初始化用户
        WxtUserInfo userInfo = new WxtUserInfo();
        userInfo.setUserId(UuidUtils.getUUID());
        userInfo.setOpenId(openId);
        userInfo.setPattern("NAME|TKL");
        //初始化用户-角色
        WxtUserRole userRole = new WxtUserRole();
        userRole.setRoleId("TKL");
        userRole.setUserId(userInfo.getUserId());
        wxtUserInfoDaoService.insertSelective(userInfo);
        wxtUserRoleDaoService.insertSelective(userRole);
        return userInfo;
    }

    private String getHelp(String nowPermissionId) {
        CftPermission nowPattern = cftPermissionDaoService.selectByPermissionId(nowPermissionId);
        if (StringUtils.isBlank(nowPattern.getHelp())) {
            return "当前模式没有相关使用说明";
        }
        return nowPattern.getHelp();
    }

    private String getSmsTask(String phoneNo) {
        List<AddSmsTaskReq> list = getSmsTaskList(phoneNo);
        if (CollectionUtils.isEmpty(list)) {
            return "您尚未设置提醒任务";
        }
        StringBuilder sb = new StringBuilder();
        for (AddSmsTaskReq req : list) {
            sb.append("====").append("任务编号[").append(req.getExtDataA()).append("]")
                    .append("====\n")
                    .append("提醒内容[").append(req.getExtDataB()).append("]\n")
                    .append("下次提醒时间[").append(DateUtils.convertDateToStr(
                    req.getSendTime(), DateUtils.FORMAT_19
            )).append("]\n");
        }
        return sb.toString();
    }

    private String cancelSmsTask(String phoneNo, String content) {
        content = content.replace("取消任务", "");
        content = content.replace("取消提醒", "");
        int seqNo = 0;
        try {
            seqNo = Integer.parseInt(content);
        } catch (Exception e) {
            return "取消提醒任务失败，请输入正确的任务编号";
        }
        JSONObject req = new JSONObject();
        req.put("phoneNo", phoneNo);
        req.put("taskSeqNo", seqNo);
        JSONObject res = smsService.cancelSmsTask(req.toJSONString());
        if (!"success".equals(res.getString("res"))) {
            return "取消提醒任务<" + seqNo + ">失败";
        }
        return "取消提醒任务<" + seqNo + ">成功";
    }

    private String tranTkl(String originalWord, String type, String senderId, String senderName) {
        JSONObject paramObject = new JSONObject();
        paramObject.put("originalWord", originalWord);
        paramObject.put("type", type);
        paramObject.put("requestId", UuidUtils.getUUID());
        paramObject.put("senderId", senderId);
        paramObject.put("senderName", senderName);
        JSONObject res = new JSONObject();
        try {
            log.info("调用alimamaService，请求：{}", paramObject.toJSONString());
            res = alimamaService.tranTkl(paramObject.toJSONString());
        } catch (Exception e) {
            log.error("", e);
        }
        log.info(res.toJSONString());
        return res.getString("tranShareWord");
    }

    private String dealTklAdmin(String originalWord, String senderId, String senderName) {
        JSONObject paramObject = new JSONObject();
        paramObject.put("originalWord", originalWord);
        paramObject.put("requestId", UuidUtils.getUUID());
        paramObject.put("senderId", senderId);
        paramObject.put("senderName", senderName);
        JSONObject res = new JSONObject();
        try {
            log.info("调用alimamaService-dealTklAdmin，请求：{}", paramObject.toJSONString());
            res = alimamaService.dealTklAdmin(paramObject.toJSONString());
        } catch (Exception e) {
            log.error("", e);
        }
        log.info(res.toJSONString());
        return res.getString("res");
    }

}
