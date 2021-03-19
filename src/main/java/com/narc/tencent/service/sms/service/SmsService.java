package com.narc.tencent.service.sms.service;

import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.alimama.service.defaultImpl.AliDefaultFallbackImpl;
import com.narc.tencent.service.sms.service.defaultImpl.SmsDefaultFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Narcssus
 * @date : 2021/3/19 16:21
 */
@Primary
@FeignClient(name = "sms", fallback = SmsDefaultFallbackImpl.class)
public interface SmsService {

    @RequestMapping(value = "/sms/addSmsTask", method = RequestMethod.POST)
    JSONObject addSmsTask(@RequestParam("param") String param);

    @RequestMapping(value = "/alimama/getSmsTask", method = RequestMethod.GET)
    JSONObject getSmsTask(@RequestParam("param") String param);

}
