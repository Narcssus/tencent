package com.narc.nlp.service.nlp.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
public interface TimeNlpService {

    JSONObject doTimeNlp(String str);
}
