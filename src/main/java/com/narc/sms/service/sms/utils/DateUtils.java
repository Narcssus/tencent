package com.narc.sms.service.sms.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.support.CronSequenceGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : Narcssus
 * @date : 2021/1/25 15:49
 */
@Slf4j
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    public static final String FORMAT_19 = "yyyy-MM-dd HH:mm:ss";


    public static Date convertStrToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
    }


    public static String convertDateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 计算两个日期相差天数(绝对值)
     */
    public static long getDateDiffDay(Date a, Date b) {
        long diff = Math.abs(a.getTime() - b.getTime());
        return diff / (1000 * 24 * 60 * 60);
    }

    public static List<Date> getNextExcTime(String cron, int n) {
        if (StringUtils.isBlank(cron)) {
            return null;
        }
        try {
            List<Date> list = new ArrayList<>();
            CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
            Date nextTimePoint = new Date();
            for (int i = 0; i < n; i++) {
                nextTimePoint = cronSequenceGenerator.next(nextTimePoint);
                list.add(nextTimePoint);
            }
            return list;
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

}
