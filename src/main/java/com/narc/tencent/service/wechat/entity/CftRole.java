package com.narc.tencent.service.wechat.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * CFT_ROLE
 * 配置表-角色
 * 
 * @author Narc
 * @date 2021-01-28
 */
@Data
public class CftRole {
    /**
     * ID
     * 主键
     */
    @NotNull
    private Integer id;

    /**
     * ROLE_ID
     * 角色ID
     */
    @Size(max=16)
    private String roleId;

    /**
     * ROLE_NAME
     * 角色名
     */
    @Size(max=64)
    private String roleName;

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