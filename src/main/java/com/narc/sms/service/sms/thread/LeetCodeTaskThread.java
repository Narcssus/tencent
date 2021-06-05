package com.narc.sms.service.sms.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.narc.sms.service.sms.dao.service.TxtSmsTaskDaoService;
import com.narc.sms.service.sms.entity.TxtSmsTask;
import com.narc.sms.service.sms.service.SmsService;
import com.narc.sms.service.sms.utils.DateUtils;
import com.narc.sms.service.sms.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author : Narcssus
 * @date : 2021/1/25 16:16
 */
@Component
@EnableScheduling
@Slf4j
public class LeetCodeTaskThread extends CommonTask {


    @Autowired
    private SmsService smsService;
    @Value("${sms.adminPhone}")
    private String adminPhone;


    private static final int THREAD_ID = 2;
    //26小时
    private static final int INTERVAL_TIME = 60 * 26;


    /**
     * 每天12点执行一次
     */
    @Scheduled(cron = "0 0 12,16,18,21 * * ?")
    public void doFetchOrders() {
        runTask(THREAD_ID, INTERVAL_TIME);
    }

    @Override
    public void doTask() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());
        String url = "https://leetcode-cn.com/api/user_submission_calendar/narcssus/";
        String res = HttpUtils.sendGet(url);
        res = res.replace("\"","");
        res = res.replace("\\","\"");
        JSONObject resObj = JSON.parseObject(res);
        for (String key : resObj.keySet()) {
            Date date = new Date(Long.parseLong(key + "000"));
            if (now.equals(sdf.format(date))) {
                return;
            }
        }
        //未完成任务发送短信
        sendLeetCodeAuthCodeNotice();
    }


    private void sendLeetCodeAuthCodeNotice() {
        try {
            log.debug("sendLeetCodeAuthCodeNotice");
            String[] phones = {adminPhone};
            String[] templateParam = {};
            smsService.sendMessage("916773", phones, templateParam, null);
        } catch (Exception e) {
            log.error("", e);
        }
    }


}
