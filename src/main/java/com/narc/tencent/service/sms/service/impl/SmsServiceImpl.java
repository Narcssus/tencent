package com.narc.tencent.service.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.narc.tencent.service.sms.service.SmsService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author : Narcssus
 * @date : 2021/3/16 17:17
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Value("${sms.secretId}")
    private String secretId;
    @Value("${sms.secretKey}")
    private String secretKey;
    @Value("${sms.sdkAppId}")
    private String sdkAppId;
    @Value("${sms.sign}")
    private String sign;
    @Value("${sms.whiteList}")
    private String whiteList;


    @Override
    public void sendMessage(String templateId, String[] phones, String[] templateParam) {
        Credential cred = new Credential(secretId, secretKey);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setSignMethod(ClientProfile.SIGN_TC3_256);
        SmsClient smsClient = new SmsClient(cred, "");
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setSmsSdkAppid(sdkAppId);
        List<String> formatPhones = new ArrayList<>();
        Set<String> whitePhones = new HashSet<>(Arrays.asList(whiteList.split(",")));
        for (String phone : phones) {
            if (whitePhones.contains(phone)) {
                formatPhones.add("+86" + phone);
            }
        }
        sendSmsRequest.setPhoneNumberSet(formatPhones.toArray(new String[0]));
        sendSmsRequest.setTemplateID(templateId);
        sendSmsRequest.setTemplateParamSet(templateParam);
        sendSmsRequest.setSign(sign);
        try {
            SendSmsResponse sendSmsResponse = smsClient.SendSms(sendSmsRequest);
            System.out.println(JSON.toJSONString(sendSmsResponse));
        } catch (Exception e) {
            log.error("发送短信失败", e);
        }
    }
}
