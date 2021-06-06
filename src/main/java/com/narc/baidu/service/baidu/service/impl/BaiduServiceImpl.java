package com.narc.baidu.service.baidu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.narc.baidu.service.baidu.service.BaiduService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
@Service
@Slf4j
public class BaiduServiceImpl implements BaiduService {

    @Value("${baidu.appId}")
    private String APP_ID;
    @Value("${baidu.apiKey}")
    private String API_KEY;
    @Value("${baidu.secretKey}")
    private String SECRET_KEY;


    @Override
    public JSONObject doOCR(JSONObject paramObject) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(1000);
        client.setSocketTimeoutInMillis(3000);

        // 调用接口
        String path = paramObject.getString("picUrl");
        org.json.JSONObject orcRes = client.basicGeneralUrl(path, new HashMap<String, String>());
        JSONObject resObj = JSON.parseObject(orcRes.toString(2));
        JSONArray jsonArray = resObj.getJSONArray("words_result");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            sb.append(jsonObject.getString("words")).append("\n");
        }
        JSONObject res = new JSONObject();
        res.put("res", sb.toString());
        return res;
    }
}
