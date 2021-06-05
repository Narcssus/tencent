package com.narc.sms.service.sms.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * TXT_SMS_LOG
 * 腾讯云-短信日志
 * 
 * @author Narc
 * @date 2021-03-19
 */
@Data
public class TxtSmsLog {
    /**
     * ID
     * 主键
     */
    @NotNull
    private Integer id;

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
     * REQUEST_ID
     * 请求腾讯云ID
     */
    @Size(max=64)
    private String requestId;

    /**
     * SEND_STATUS_SET
     * 发送结果列表
     */
    @Size(max=1024)
    private String sendStatusSet;

    /**
     * SEND_STATUS
     * 发送结果:全部成功全部失败部分失败
     */
    @Size(max=4)
    private String sendStatus;

    /**
     * TASK_ID
     * 发送任务ID(只有定时任务触发的短信才有)
     */
    @Size(max=64)
    private String taskId;

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