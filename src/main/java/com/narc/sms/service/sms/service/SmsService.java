package com.narc.sms.service.sms.service;

import com.alibaba.fastjson.JSONObject;

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

}
