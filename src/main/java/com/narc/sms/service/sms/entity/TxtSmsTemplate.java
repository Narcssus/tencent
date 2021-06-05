package com.narc.sms.service.sms.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * TXT_SMS_TEMPLATE
 * 腾讯云-短信模板
 * 
 * @author Narc
 * @date 2021-03-19
 */
@Data
public class TxtSmsTemplate {
    /**
     * TEMPLATE_ID
     * 短信模板ID
     */
    @Size(max=64)
    @NotNull
    private String templateId;

    /**
     * TEMPLATE_DESC
     * 模板名称
     */
    @Size(max=64)
    private String templateDesc;

    /**
     * TEMPLATE_CONTENT
     * 短信模板内容
     */
    @Size(max=64)
    private String templateContent;

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