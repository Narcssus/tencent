package com.narc.sms.service.sms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.narc.sms.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Narcssus
 * @date : 2019/11/17 17:03
 */
@RestController
@RequestMapping("/")
@Api(tags = "短信接口")
@Slf4j
public class SmsController {


    @Autowired
    private SmsService smsService;

    @Value("${sms.adminPhone}")
    private String adminPhone;

    @PostMapping(value = "sendAlimamaAuthCodeNotice")
    public JSONObject sendAlimamaAuthCodeNotice() {
        try {
            log.debug("收到sendAlimamaAuthCodeNotice消息");
            String[] phones = {adminPhone};
            String[] templateParam = {};
            smsService.sendMessage("892286", phones, templateParam, null);
            return new JSONObject();
        } catch (Exception e) {
            log.error("", e);
            return new JSONObject();
        }
    }

    @PostMapping(value = "sendAlimamaUndoOrderNotice")
    public JSONObject sendAlimamaUndoOrderNotice(String param) {
        try {
            log.debug("收到sendAlimamaUndoOrderNotice消息:{}",param);
            JSONObject jsonObject = JSON.parseObject(param);
            String[] phones = {adminPhone};
            String[] templateParam = {jsonObject.getString("num")};
            smsService.sendMessage("899355", phones, templateParam, null);
            return new JSONObject();
        } catch (Exception e) {
            log.error("", e);
            return new JSONObject();
        }
    }

    @PostMapping(value = "addSmsTask")
    public JSONObject addSmsTask(String param) {
        log.debug("收到addSmsTask消息:{}", param);
        JSONObject paramObject = JSON.parseObject(param);
        return smsService.addSmsTask(paramObject);
    }

    @PostMapping(value = "cancelSmsTask")
    public JSONObject cancelSmsTask(String param) {
        log.debug("收到cancelSmsTask消息:{}", param);
        JSONObject paramObject = JSON.parseObject(param);
        return smsService.cancelSmsTask(paramObject);
    }


    @GetMapping(value = "getSmsTask")
    public JSONObject getSmsTask(String param) {
        log.debug("收到getSmsTask消息:{}", param);
        JSONObject paramObject = JSON.parseObject(param);
        return smsService.getSmsTask(paramObject);
    }


}
