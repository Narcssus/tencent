package com.narc.tencent.service.wechat.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * WXT_USER_INFO
 * 微信-用户表
 * 
 * @author Narc
 * @date 2021-03-18
 */
@Data
public class WxtUserInfo {
    /**
     * USER_ID
     * 用户ID
     */
    @Size(max=64)
    @NotNull
    private String userId;

    /**
     * OPEN_ID
     * 微信openID
     */
    @Size(max=64)
    private String openId;

    /**
     * NAME
     * 昵称
     */
    @Size(max=16)
    private String name;

    /**
     * REMARK
     * 备注
     */
    @Size(max=64)
    private String remark;

    /**
     * PATTERN
     * 当前模式
     */
    @Size(max=32)
    private String pattern;

    /**
     * PHONE_NO
     * 联系电话
     */
    @Size(max=20)
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