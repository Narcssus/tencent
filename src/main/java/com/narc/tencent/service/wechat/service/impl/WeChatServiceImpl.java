package com.narc.tencent.service.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.alimama.service.AlimamaService;
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
import com.narc.tencent.utils.UuidUtils;
import com.narc.tencent.utils.WechatMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    @Override
    public String processRequest(HttpServletRequest request) {
        String res = "";
        WxtMessageLog msgLog = new WxtMessageLog();
        try {
            Map<String, String> map = WechatMessageUtil.xmlToMap(request);
            String jsonStr = JSON.toJSONString(map);
            log.info("收到微信公众号请求:{}", jsonStr);
            msgLog = JSON.parseObject(jsonStr, WxtMessageLog.class);
            String fromUserName = msgLog.getFromUserName();
            String toUserName = msgLog.getToUserName();
            String content = msgLog.getContent();
            log.info("收到用户{}消息：{}", fromUserName, content);
            if (StringUtils.isBlank(content)) {
                return "";
            }
            //获取用户信息
            WxtUserInfo userInfo = wxtUserInfoDaoService.getUserByOpenId(fromUserName);
            if (userInfo == null) {
                //新用户新增
                userInfo = initNewUser(fromUserName);
            }
            String rspContent = "暂不支持";
            switch (msgLog.getMsgType()) {
                case WechatMessageUtil.MESSAGE_TEXT:
                    rspContent = dealText(msgLog.getContent(), userInfo);
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
            msgLog.setId(UUID.randomUUID().toString());
            msgLog.setCreatedId("SYSTEM");
            msgLog.setModifiedId("SYSTEM");
            msgLog.setCreatedDatetime(new Date());
            msgLog.setModifiedDatetime(new Date());
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
        CftPermission nowPattern = cftPermissionDaoService.selectByPermissionId(userInfo.getPattern());

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
                        sb.append(p.getPermissionName())
                                .append("--")
                                .append(p.getCommandDesc())
                                .append("\r\n");
                    }
                    return sb.toString();
                case "SHOW_HELP":
                    //显示当前模式的使用说明
                    return getHelp(nowPattern);
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
            case "CHAT":
                return "聊天模式暂未开放，敬请期待";
            case "CALC":
                return "计算模式暂未开放，敬请期待";
            default:
                return "请输入正确的指令";
        }
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
        //初始化用户
        WxtUserInfo userInfo = new WxtUserInfo();
        userInfo.setUserId(UuidUtils.getUUID());
        userInfo.setCreatedId("SYSTEM");
        userInfo.setModifiedId("SYSTEM");
        userInfo.setOpenId(openId);
        userInfo.setPattern("淘口令");
        //初始化用户-角色
        WxtUserRole userRole = new WxtUserRole();
        userRole.setId(UuidUtils.getUUID());
        userRole.setRoleId("GUEST");
        userRole.setUserId(userInfo.getUserId());
        userRole.setCreatedId("SYSTEM");
        userRole.setModifiedId("SYSTEM");
        wxtUserInfoDaoService.insert(userInfo);
        wxtUserRoleDaoService.insert(userRole);
        return userInfo;
    }

    private String getHelp(CftPermission nowPattern) {
        switch (nowPattern.getPermissionId()) {
            case "TKL":
            case "TKL_VIP":
                return "淘口令使用说明";
            case "CHAT":
                return "聊天模式使用说明";
            case "CALC":
                return "计算模式使用说明";
            default:
                return "当前模式没有相关使用说明";
        }
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
}
