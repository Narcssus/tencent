package com.narc.tencent.service.alimama.service;

import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.alimama.service.defaultImpl.DefaultFallbackImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 阿里妈妈接口服务类
 *
 * @author : Narcssus
 * @date : 2020/11/25 21:46
 */
@Primary
@FeignClient(name = "alibaba", fallback = DefaultFallbackImpl.class)
public interface AlimamaService {

    /**
     * 淘口令转链
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/alimama/tranShareWord", method = RequestMethod.GET)
    JSONObject tranTkl(@RequestParam("param") String param);

    /**
     * 淘口令管理接口
     * @param param
     * @return
     */
    @RequestMapping(value = "/alimama/dealTklAdmin", method = RequestMethod.GET)
    JSONObject dealTklAdmin(@RequestParam("param") String param);



}
