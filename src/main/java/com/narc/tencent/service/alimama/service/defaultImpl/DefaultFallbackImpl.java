package com.narc.tencent.service.alimama.service.defaultImpl;

import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.alimama.service.AlimamaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : Narcssus
 * @date : 2021/1/22 16:12
 */
@Component
@Slf4j
public class DefaultFallbackImpl implements AlimamaService {

    @Override
    public JSONObject tranTkl(String param) {
        log.info("请求：{}，降级处理", param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tranShareWord", "目前服务不可用，请稍后再试");
        return jsonObject;
    }

    @Override
    public JSONObject dealTklAdmin(String param) {
        log.info("请求：{}，降级处理", param);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("res", "目前服务不可用，请稍后再试");
        return jsonObject;
    }


}
