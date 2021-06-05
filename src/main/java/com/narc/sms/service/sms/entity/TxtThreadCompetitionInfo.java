package com.narc.sms.service.sms.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * TXT_THREAD_COMPETITION_INFO
 * 
 * 
 * @author Narc
 * @date 2021-03-19
 */
@Data
public class TxtThreadCompetitionInfo {
    /**
     * THREAD_ID
     * 线程ID
     */
    @NotNull
    private Integer threadId;

    /**
     * THREAD_NAME
     * 线程名称
     */
    @Size(max=32)
    private String threadName;

    /**
     * NOW_IP
     * 现在运行任务的机器IP
     */
    @Size(max=32)
    private String nowIp;

    /**
     * LAST_RUN_TIME
     * 上次运行任务时间
     */
    private Date lastRunTime;
}