package com.narc.sms.service.sms.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * TXT_SMS_TASK
 * 腾讯云-短信任务
 * 
 * @author Narc
 * @date 2021-03-23
 */
@Data
public class TxtSmsTask {
    /**
     * ID
     * 主键
     */
    @NotNull
    private Integer id;

    /**
     * TASK_ID
     * 发送任务ID
     */
    @Size(max=64)
    private String taskId;

    /**
     * TEMPLATE_ID
     * 短信模板ID
     */
    @Size(max=64)
    private String templateId;

    /**
     * TEMPLATE_CONTENT
     * 短信模板内容
     */
    @Size(max=64)
    private String templateContent;

    /**
     * PHONE_NUMBER_SET
     * 接受者手机号列表
     */
    @Size(max=200)
    private String phoneNumberSet;

    /**
     * TEMPLATE_PARAM
     * 模板参数列表
     */
    @Size(max=200)
    private String templateParam;

    /**
     * TASK_TYPE
     * 任务类型：1-一次性任务；2-循环性任务
     */
    @Size(max=1)
    @NotNull
    private String taskType;

    /**
     * SEND_TIME
     * 短信发送时间
     */
    private Date sendTime;

    /**
     * CRON_EXPRESSION
     * 循环性任务发送时间表达式
     */
    @Size(max=20)
    private String cronExpression;

    /**
     * STATUS
     * 任务状态：0-未执行；1-执行完成；2-取消
     */
    @Size(max=2)
    private String status;

    /**
     * EXT_DATA_A
     * 业务字段A
     */
    @Size(max=255)
    private String extDataA;

    /**
     * EXT_DATA_B
     * 业务字段B
     */
    @Size(max=255)
    private String extDataB;

    /**
     * EXT_DATA_C
     * 业务字段C
     */
    @Size(max=255)
    private String extDataC;

    /**
     * CREATED_DATETIME
     * 创建时间
     */
    private Date createdDatetime;

    /**
     * CREATED_ID
     * 创建ID
     */
    @Size(max=64)
    private String createdId;

    /**
     * MODIFIED_DATETIME
     * 修改时间
     */
    private Date modifiedDatetime;

    /**
     * MODIFIED_ID
     * 修改ID
     */
    @Size(max=64)
    private String modifiedId;
}