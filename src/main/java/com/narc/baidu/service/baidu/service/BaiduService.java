package com.narc.baidu.service.baidu.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
public interface BaiduService {

    /**
     *  图片OCR
     * @return
     */
    JSONObject doOCR(JSONObject paramObject);

}
