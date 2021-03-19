package com.narc.tencent.service.sms.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author : Narcssus
 * @date : 2021/3/19 13:46
 */
@Data
public class AddSmsTaskReq {
    private String templateId;
    private String phoneNumberSet;
    private String templateParam;
    /**
     * 任务类型：1-一次性任务；2-循环性任务
     */
    private String taskType;
    private String cronExpression;
    private Date sendTime;

}
