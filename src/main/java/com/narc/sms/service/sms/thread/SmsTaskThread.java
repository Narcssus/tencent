package com.narc.sms.service.sms.thread;

import com.alibaba.fastjson.JSON;
import com.narc.sms.service.sms.dao.service.TxtSmsTaskDaoService;
import com.narc.sms.service.sms.entity.TxtSmsTask;
import com.narc.sms.service.sms.service.SmsService;
import com.narc.sms.service.sms.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
public class SmsTaskThread extends CommonTask {


    @Autowired
    private TxtSmsTaskDaoService txtSmsTaskDaoService;
    @Autowired
    private SmsService smsService;


    private static final int THREAD_ID = 1;
    private static final int INTERVAL_TIME = 5;

    /**
     * 每分钟执行一次
     */

    @Scheduled(cron = "0 */1 * * * ?")
    public void doFetchOrders() {
        runTask(THREAD_ID, INTERVAL_TIME);
    }

    @Override
    public void doTask() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date now = calendar.getTime();
        log.debug("执行短信提醒检查任务，{}", DateUtils.convertDateToStr(now, DateUtils.FORMAT_19));
        //查询未来五分钟内到期的
        List<TxtSmsTask> list = txtSmsTaskDaoService.selectBySendTime(
                DateUtils.addMinutes(now, 5));
        log.debug("短信提醒检查任务，检索到条数{}", list.size());
        for (TxtSmsTask task : list) {
            log.debug("短信提醒检查任务，处理任务ID{}", task.getTaskId());
            sendTaskSms(task);
        }
        log.debug("短信提醒检查任务，执行完成");

    }


    private void sendTaskSms(TxtSmsTask task) {
        List<String> phones = JSON.parseArray(task.getPhoneNumberSet(), String.class);
        List<String> templateParam = JSON.parseArray(task.getTemplateParam(), String.class);
        smsService.sendMessage(task.getTemplateId(),
                phones.toArray(new String[0]), templateParam.toArray(new String[0]), task.getTaskId());
        if ("2".equals(task.getTaskType())) {
            //设置下一次执行时间
            String cron = task.getCronExpression();
            List<Date> dateList = DateUtils.getNextExcTime(cron, 2);
            if (CollectionUtils.isEmpty(dateList) || dateList.size() != 2) {
                return;
            }
            TxtSmsTask newTask = new TxtSmsTask();
            newTask.setSendTime(dateList.get(1));
            newTask.setId(task.getId());
            newTask.setModifiedDatetime(new Date());
            txtSmsTaskDaoService.updateByPrimaryKeySelective(newTask);
        } else {
            TxtSmsTask newTask = new TxtSmsTask();
            newTask.setId(task.getId());
            newTask.setModifiedDatetime(new Date());
            newTask.setStatus("1");
            txtSmsTaskDaoService.updateByPrimaryKeySelective(newTask);
        }
    }


}
