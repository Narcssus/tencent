package com.narc.sms.service.sms.thread;

import com.narc.sms.service.sms.dao.service.TxtThreadCompetitionInfoDaoService;
import com.narc.sms.service.sms.entity.TxtThreadCompetitionInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author : Narcssus
 * @date : 2021/1/25 17:50
 */
@Slf4j
public class CommonTask {
    @Autowired
    private TxtThreadCompetitionInfoDaoService txtThreadCompetitionInfoDaoService;

    @Value("${local-ip}")
    private String ip;


    public void runTask(int threadId, int intervalTime) {
        try {
            TxtThreadCompetitionInfo competitionInfo = txtThreadCompetitionInfoDaoService.getCompetitionInfo(threadId);
            if (competitionInfo == null) {
                return;
            }
            Date now = new Date();
            if (!ip.equals(competitionInfo.getNowIp()) &&
                    now.compareTo(DateUtils.addMinutes(competitionInfo.getLastRunTime(), intervalTime)) > 0) {
                //竞争锁
                competitionInfo.setNowIp(ip);
                txtThreadCompetitionInfoDaoService.updateCompetitionInfo(competitionInfo);
            }
            competitionInfo = txtThreadCompetitionInfoDaoService.getCompetitionInfo(threadId);
            if (ip.equals(competitionInfo.getNowIp())) {
                doTask();
                competitionInfo.setLastRunTime(new Date());
                txtThreadCompetitionInfoDaoService.updateCompetitionInfo(competitionInfo);
            }
        } catch (Exception e) {
            log.error("定时任务执行失败", e);
        }
    }

    public void doTask() {
        //do something
    }


}
