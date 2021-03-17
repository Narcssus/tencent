package com.narc.tencent.service.sms.controller;

import com.narc.tencent.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Narcssus
 * @date : 2021/3/16 17:17
 */
@RestController
@RequestMapping("/sms")
@Api(value = "sms api", tags = "短信相关接口")
@Slf4j
public class SmsController {

    @Autowired
    private SmsService smsService;

    @Value("${sms.adminPhone}")
    private String adminPhone;

    @PostMapping(value = "sendAlimamaAuthCodeNotice", produces = "application/text;charset=UTF-8")
    public String sendAlimamaAuthCodeNotice() {
        try {
            String[] phones = {adminPhone};
            String[] templateParam = {};
            smsService.sendMessage("892286", phones, templateParam);
            return "success";
        } catch (Exception e) {
            log.error("", e);
            return "error";
        }
    }







}
