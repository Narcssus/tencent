package com.narc.sms.service.sms.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
public interface SmsService {

    void sendMessage(String templateId, String[] phones, String[] templateParam,String taskId);

    JSONObject addSmsTask(JSONObject req);

    JSONObject cancelSmsTask(JSONObject req);

    JSONObject getSmsTask(JSONObject req);

    /**
     * 发送管理员通知短信
     *
     */
    JSONObject sendAlimamaAuthCodeNotice(@RequestParam("param") String param);

    /**
     * 发送管理员通知短信
     */
    JSONObject sendAlimamaUndoOrderNotice(@RequestParam("param") String param);

    JSONObject addSmsTask(String param);

    JSONObject cancelSmsTask(String param);

    JSONObject getSmsTask(String param);

}
