package com.narc.sms.service.sms.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * TXT_SMS_WHITE_PHONE
 * 腾讯云-短信白名单
 * 
 * @author Narc
 * @date 2021-03-19
 */
@Data
public class TxtSmsWhitePhone {
    /**
     * PHONE_NO
     * 发送任务ID
     */
    @Size(max=20)
    @NotNull
    private String phoneNo;

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