package com.narc.tencent.service.sms.service.defaultImpl;

import com.alibaba.fastjson.JSONObject;
import com.narc.tencent.service.alimama.service.AlimamaService;
import com.narc.tencent.service.sms.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : Narcssus
 * @date : 2021/1/22 16:12
 */
@Component
@Slf4j
public class SmsDefaultFallbackImpl implements SmsService {

    @Override
    public JSONObject addSmsTask(String param) {
        log.info("请求：{}，降级处理", param);
        return null;
    }
    @Override
    public JSONObject cancelSmsTask(String param) {
        log.info("请求：{}，降级处理", param);
        return null;
    }

    @Override
    public JSONObject getSmsTask(String param) {
        log.info("请求：{}，降级处理", param);
        return null;
    }
}
