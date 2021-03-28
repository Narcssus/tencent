package com.narc.tencent.service.baidu;

import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.baidu.defaultImpl.BaiduDefaultFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Narcssus
 * @date : 2021/3/28 20:09
 */
@Primary
@FeignClient(name = "baidu", fallback = BaiduDefaultFallbackImpl.class)
public interface BaiduService {

    @RequestMapping(value = "baidu/ocr/doOCR", method = RequestMethod.GET)
    JSONObject doOCR(@RequestParam("param") String param);



}
