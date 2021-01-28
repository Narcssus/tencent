package com.narc.tencent.service.wechat.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * WXT_USER_ROLE
 * 微信-用户角色表
 * 
 * @author Narc
 * @date 2021-01-28
 */
@Data
public class WxtUserRole {
    /**
     * ID
     * 主键
     */
    @NotNull
    private Integer id;

    /**
     * USER_ID
     * 用户ID
     */
    @Size(max=64)
    private String userId;

    /**
     * ROLE_ID
     * 角色ID
     */
    @Size(max=16)
    private String roleId;

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