package com.narc.tencent.service.sms.service;

/**
 * @author : Narcssus
 * @date : 2021/3/16 17:16
 */
public interface SmsService {

    void sendMessage(String templateId, String[] phones, String[] templateParam);

}
