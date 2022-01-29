package com.narc.sms.service.sms.utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author : Narcssus
 * @date : 2022/1/29 10:55
 */
public class MailUtils {

    /**
     * 创建一封只包含文本的简单邮件
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, List<String> receiveMails,
                                                String subject, String content) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "NATC测试", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMails.get(0), "用户", "UTF-8"));
        for (int i = 1; i < receiveMails.size(); i++) {
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMails.get(i), "用户", "UTF-8"));
        }
        message.setSubject(subject, "UTF-8");
        message.setContent(content, "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}
