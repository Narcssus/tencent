package com.narc.tencent.service.alimama.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
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
@FeignClient(name = "alibaba", fallback = AlimamaService.DefaultFallbackImpl.class)
public interface AlimamaService {

    /**
     * 淘口令转链
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/alimama/tranShareWord", method = RequestMethod.GET)
    JSONObject tranTkl(@RequestParam("param") String param);


    @Component
    @Slf4j
    class DefaultFallbackImpl implements AlimamaService {

        @Override
        public JSONObject tranTkl(String param) {
            log.info("请求：{}，降级处理", param);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tranShareWord", "目前服务不可用，请稍后再试");
            return jsonObject;
        }
    }

}
