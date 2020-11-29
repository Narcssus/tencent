package com.narc.tencent.service.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.alimama.service.AlimamaService;
import com.narc.tencent.service.wechat.dao.service.CftPermissionDaoService;
import com.narc.tencent.service.wechat.dao.service.WxtUserInfoDaoService;
import com.narc.tencent.service.wechat.dao.service.WxtUserRoleDaoService;
import com.narc.tencent.service.wechat.entity.CftPermission;
import com.narc.tencent.service.wechat.entity.WxtUserInfo;
import com.narc.tencent.service.wechat.entity.WxtUserRole;
import com.narc.tencent.service.wechat.entity.message.TextMessage;
import com.narc.tencent.service.wechat.service.WeChatService;
import com.narc.tencent.utils.HttpUtils;
import com.narc.tencent.utils.UuidUtils;
import com.narc.tencent.utils.WechatMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {

    @Value("${alibabaServer.url}")
    private String apiUrl;

    @Autowired
    private AlimamaService alimamaService;

    @Autowired
    private WxtUserInfoDaoService wxtUserInfoDaoService;
    @Autowired
    private WxtUserRoleDaoService wxtUserRoleDaoService;
    @Autowired
    private CftPermissionDaoService cftPermissionDaoService;

    @Override
    public String processRequest(HttpServletRequest request) {
        Map<String, String> map = WechatMessageUtil.xmlToMap(request);
        log.info(JSON.toJSONString(map));
        // 发送方帐号（一个OpenID）
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        // 消息类型
        String msgType = map.get("MsgType");
        // 默认回复一个"success"
        String responseMessage = "success";
        String content = map.get("Content");
        log.info("收到用户{}消息：{}", fromUserName, content);
        if (StringUtils.isBlank(content)) {
            return "";
        }
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);

        //获取用户信息
        WxtUserInfo userInfo = wxtUserInfoDaoService.getUserByOpenId(fromUserName);
        if (userInfo == null) {
            //新用户新增
            userInfo = initNewUser(fromUserName);
        }
        String nowPattern = userInfo.getPattern();
        List<CftPermission> allPermissions = cftPermissionDaoService.selectAllByUserId(userInfo.getUserId());
        //获取用户所有的权限
        List<String> allPermissionIds = allPermissions.stream()
                .map(CftPermission::getPermissionId).collect(Collectors.toList());
        //用户的所有口令
        List<String> allCommands = allPermissions.stream()
                .map(CftPermission::getCommand).collect(Collectors.toList());

        // 对消息进行处理
        if (WechatMessageUtil.MESSAGE_TEXT.equals(msgType)) {
            String rspContent = "请输入正确的指令";
            //判断类型
            if (allPermissionIds.contains("CHANGE_PATTERN") && content.startsWith("切换到")) {
                //切换当前模式
                String nextPattern = content.substring(3);
                if (allCommands.contains(nextPattern)) {
                    //更新用户模式
                    userInfo.setPattern(nextPattern);
                    wxtUserInfoDaoService.updateByPrimaryKeySelective(userInfo);
                }
                return returnTextMessage(textMessage, "切换成功，当前模式：" + nextPattern);
            }
            if (allPermissionIds.contains("SHOW_PATTERN") &&
                    ("显示当前模式".equals(content) || "当前模式".equals(content))) {
                //显示当前模式
                return returnTextMessage(textMessage, "当前模式：" + userInfo.getPattern());
            }
            if (allPermissionIds.contains("SHOW_ALL_COMMANDS") && "显示所有指令".equals(content)) {
                //显示所有模式
                StringBuilder sb = new StringBuilder();
                sb.append("所有指令如下：").append("\r\n");
                for (CftPermission permission : allPermissions) {
                    sb.append(permission.getCommand())
                            .append("--")
                            .append(permission.getCommandDesc())
                            .append("\r\n");
                }
                return returnTextMessage(textMessage, sb.toString());
            }

            if ("淘口令".equals(nowPattern)) {
                rspContent = tranTkl(content);
                return returnTextMessage(textMessage, rspContent);
            }

        }
        return returnTextMessage(textMessage, "请输入正确指令");
    }


    private String returnTextMessage(TextMessage textMessage, String rspContent) {
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


    private String tranTkl(String originalWord) {
        JSONObject paramObject = new JSONObject();
        paramObject.put("originalWord", originalWord);
        paramObject.put("requestId", UuidUtils.getUUID());
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
