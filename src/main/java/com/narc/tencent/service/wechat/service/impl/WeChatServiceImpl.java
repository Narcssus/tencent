package com.narc.tencent.service.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.wechat.entity.message.TextMessage;
import com.narc.tencent.service.wechat.service.WeChatService;
import com.narc.tencent.utils.HttpUtils;
import com.narc.tencent.utils.WechatMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.Map;

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
        log.info("content=" + map.get("Content"));
        // 对消息进行处理
        if (WechatMessageUtil.MESSAGE_TEXT.equals(msgType)) {// 文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setMsgType(WechatMessageUtil.MESSAGE_TEXT);
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(System.currentTimeMillis());
            textMessage.setContent(getTKL(content));
            responseMessage = WechatMessageUtil.textMessageToXml(textMessage);
        }
        log.info(responseMessage);
        return responseMessage;
    }

    private String getTKL(String param) {
        JSONObject paramObject = new JSONObject();
        paramObject.put("originalWord", param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("param", paramObject);
        JSONObject res = null;
        try {
            res = HttpUtils.httpGet(apiUrl, jsonObject);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        log.info(res.toJSONString());
        return res.getString("tranShareWord");
    }
}
