package com.narc.tencent.service.nlp.service.defaultImpl;

import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.alimama.service.AlimamaService;
import com.narc.tencent.service.nlp.service.NlpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : Narcssus
 * @date : 2021/1/22 16:12
 */
@Component
@Slf4j
public class NlpDefaultFallbackImpl implements NlpService {

    @Override
    public JSONObject timeNlp(String param) {
        log.info("请求：{}，降级处理", param);
        return new JSONObject();
    }




}
