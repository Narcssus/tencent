package com.narc.nlp.service.nlp.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.narc.nlp.service.nlp.service.TimeNlpService;
import com.time.nlp.StringPreHandlingModule;
import com.time.nlp.TimeNormalizer;
import com.time.nlp.TimeUnit;
import com.time.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Narcssus
 * @version 1.0
 * @date 2020-10-15
 */
@Service
@Slf4j
public class TimeNlpServiceImpl implements TimeNlpService {

    @Override
    public JSONObject doTimeNlp(String str) {
        TimeNormalizer normalizer = new TimeNormalizer();
        normalizer.setPreferFuture(false);
        String timeBase = DateUtil.formatDate(new Date(), "yyyy-MM-dd-HH-mm-ss");
        normalizer.parse(str, timeBase);
        TimeUnit[] unit = normalizer.getTimeUnit();
        JSONArray timeList = new JSONArray();
        JSONObject res = new JSONObject();
        for (TimeUnit unit1 : unit) {
            JSONObject object = new JSONObject();
            object.put("timeExpression", unit1.Time_Expression);
            object.put("time", DateUtil.formatDateDefault(unit1.getTime()));
            object.put("isCron", unit1.isCron);
            object.put("cron", unit1.cron);
            timeList.add(object);
        }
        res.put("timeList", timeList);
        res.put("formatStr", StringPreHandlingModule.numberTranslator(str));
        return res;
    }
}
