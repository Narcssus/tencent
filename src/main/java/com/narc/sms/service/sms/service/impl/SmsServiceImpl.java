package com.narc.sms.service.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.narc.sms.service.sms.dao.service.TxtSmsLogDaoService;
import com.narc.sms.service.sms.dao.service.TxtSmsTaskDaoService;
import com.narc.sms.service.sms.dao.service.TxtSmsTemplateDaoService;
import com.narc.sms.service.sms.dao.service.TxtSmsWhitePhoneDaoService;
import com.narc.sms.service.sms.entity.TxtSmsLog;
import com.narc.sms.service.sms.entity.TxtSmsTask;
import com.narc.sms.service.sms.entity.TxtSmsTemplate;
import com.narc.sms.service.sms.service.SmsService;
import com.narc.sms.service.sms.utils.MailUtils;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
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
    @Value("${sms.adminPhone}")
    private String adminPhone;
    @Value("${sms.mailKey}")
    private String mailKey;

    @Autowired
    private TxtSmsLogDaoService smsLogDaoService;
    @Autowired
    private TxtSmsTaskDaoService smsTaskDaoService;
    @Autowired
    private TxtSmsTemplateDaoService smsTemplateDaoService;
    @Autowired
    private TxtSmsWhitePhoneDaoService smsWhitePhoneDaoService;

    @Override
    public void sendMessage(String templateId, String[] phones, String[] templateParam, String taskId) {
        log.debug("发送邮件，{}，{}，{}，{}", templateId, JSON.toJSONString(phones)
                , JSONObject.toJSONString(templateParam), taskId);
        //发邮件
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);
        session.setDebug(true);
        try {
            List<String> whitePhones = smsWhitePhoneDaoService.selectWhitePhone(Arrays.asList(phones));
            TxtSmsTemplate template = smsTemplateDaoService.getTemplateById(templateId);
            String content = template.getTemplateContent();
            for (int i = 1; i <= templateParam.length; i++) {
                content = content.replace("{" + i + "}", templateParam[i - 1]);
            }
            MimeMessage message = MailUtils.createMimeMessage(session, adminPhone, whitePhones,
                    template.getTemplateDesc(), content);
            Transport transport = session.getTransport();
            transport.connect(adminPhone, mailKey);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            log.error("发送邮件失败", e);
        }

    }

//    public void sendMessage(String templateId, String[] phones, String[] templateParam, String taskId) {
//        Credential cred = new Credential(secretId, secretKey);
//        ClientProfile clientProfile = new ClientProfile();
//        clientProfile.setSignMethod(ClientProfile.SIGN_TC3_256);
//        SmsClient smsClient = new SmsClient(cred, "");
//        SendSmsRequest sendSmsRequest = new SendSmsRequest();
//        sendSmsRequest.setSmsSdkAppid(sdkAppId);
//        List<String> formatPhones = new ArrayList<>();
//        List<String> whitePhones = smsWhitePhoneDaoService.selectWhitePhone(Arrays.asList(phones));
//        for (String phone : whitePhones) {
//            formatPhones.add("+86" + phone);
//        }
//        sendSmsRequest.setPhoneNumberSet(formatPhones.toArray(new String[0]));
//        sendSmsRequest.setTemplateID(templateId);
//        sendSmsRequest.setTemplateParamSet(templateParam);
//        sendSmsRequest.setSign(sign);
//
//        TxtSmsLog smsLog = new TxtSmsLog();
//        smsLog.setTemplateId(sendSmsRequest.getTemplateID());
//        smsLog.setTemplateContent(smsTemplateDaoService.getContentById(sendSmsRequest.getTemplateID()));
//        smsLog.setPhoneNumberSet(JSON.toJSONString(sendSmsRequest.getPhoneNumberSet()));
//        smsLog.setTemplateParam(JSON.toJSONString(sendSmsRequest.getTemplateParamSet()));
//        smsLog.setTaskId(taskId);
//        try {
//            SendSmsResponse sendSmsResponse = smsClient.SendSms(sendSmsRequest);
//            smsLog.setRequestId(sendSmsResponse.getRequestId());
//            smsLog.setSendStatusSet(JSON.toJSONString(sendSmsResponse.getSendStatusSet()));
//            int success = 0;
//            for (SendStatus status : sendSmsResponse.getSendStatusSet()) {
//                if ("Ok".equals(status.getCode())) {
//                    success++;
//                }
//            }
//            if (success == sendSmsResponse.getSendStatusSet().length) {
//                smsLog.setSendStatus("全部成功");
//            } else if (success == 0) {
//                smsLog.setSendStatus("全部失败");
//            } else {
//                smsLog.setSendStatus("部分失败");
//            }
//            System.out.println(JSON.toJSONString(sendSmsResponse));
//        } catch (Exception e) {
//            log.error("发送短信失败", e);
//        } finally {
//            smsLogDaoService.insertOne(smsLog);
//        }
//    }


    @Override
    public JSONObject addSmsTask(JSONObject req) {
        JSONObject res = new JSONObject();
        JSONArray jsonArray = req.getJSONArray("list");
        res.put("res", "success");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject taskObj = jsonArray.getJSONObject(i);
            try {
                TxtSmsTask task = JSON.parseObject(taskObj.toJSONString(), TxtSmsTask.class);
                task.setTemplateContent(smsTemplateDaoService.getContentById(task.getTemplateId()));
                task.setTaskId(UUID.randomUUID().toString());
                task.setStatus("0");
                smsTaskDaoService.insertOne(task);
            } catch (Exception e) {
                log.error("", e);
                res.put("res", "fail");
            }
        }
        return res;
    }

    @Override
    public JSONObject cancelSmsTask(JSONObject req) {
        JSONObject res = new JSONObject();
        res.put("res", "success");
        String phoneNo = req.getString("phoneNo");
        String taskSeqNo = req.getString("taskSeqNo");
        List<TxtSmsTask> list = smsTaskDaoService.selectByPhoneNoAndSeqNo(phoneNo, taskSeqNo);
        if (CollectionUtils.isEmpty(list)) {
            res.put("res", "fail");
        } else {
            List<Integer> ids = list.stream().map(TxtSmsTask::getId).collect(Collectors.toList());
            int num = smsTaskDaoService.cancelTask(ids);
            if (num != ids.size()) {
                res.put("res", "fail");
            }
        }
        return res;
    }

    @Override
    public JSONObject getSmsTask(JSONObject req) {
        String phoneNo = req.getString("phoneNo");
        List<TxtSmsTask> doneList = smsTaskDaoService.selectLast12HourByPhoneNo(phoneNo);
        List<TxtSmsTask> list = smsTaskDaoService.selectByPhoneNo(phoneNo);
        JSONObject res = new JSONObject();
        res.put("list", list);
        res.put("doneList", doneList);
        return res;
    }

    @Override
    public JSONObject sendAlimamaAuthCodeNotice(String param) {
        try {
            log.debug("收到sendAlimamaAuthCodeNotice消息");
            String[] phones = {adminPhone};
            String[] templateParam = {};
            sendMessage("892286", phones, templateParam, null);
            return new JSONObject();
        } catch (Exception e) {
            log.error("", e);
            return new JSONObject();
        }
    }

    @Override
    public JSONObject sendAlimamaUndoOrderNotice(String param) {
        try {
            log.debug("收到sendAlimamaUndoOrderNotice消息:{}", param);
            JSONObject jsonObject = JSON.parseObject(param);
            String[] phones = {adminPhone};
            String[] templateParam = {jsonObject.getString("num")};
            sendMessage("899355", phones, templateParam, null);
            return new JSONObject();
        } catch (Exception e) {
            log.error("", e);
            return new JSONObject();
        }
    }

    @Override
    public JSONObject addSmsTask(String param) {
        log.debug("收到addSmsTask消息:{}", param);
        JSONObject paramObject = JSON.parseObject(param);
        return addSmsTask(paramObject);
    }

    @Override
    public JSONObject cancelSmsTask(String param) {
        log.debug("收到cancelSmsTask消息:{}", param);
        JSONObject paramObject = JSON.parseObject(param);
        return cancelSmsTask(paramObject);
    }

    @Override
    public JSONObject getSmsTask(String param) {
        log.debug("收到getSmsTask消息:{}", param);
        JSONObject paramObject = JSON.parseObject(param);
        return getSmsTask(paramObject);
    }

}
