package com.narc.tencent.service.nlp.service;

import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.nlp.service.defaultImpl.NlpDefaultFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Narcssus
 * @date : 2021/3/18 10:59
 */
@Primary
public interface NlpService {


    /**
     * 时间语义识别接口
     */
    @RequestMapping(value = "/nlp/timeNlp", method = RequestMethod.GET)
    JSONObject timeNlp(@RequestParam("param") String param);

}
