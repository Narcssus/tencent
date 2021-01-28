package com.narc.tencent.service.wechat.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * CFT_ROLE_PERMISSION
 * 配置表-角色权限
 * 
 * @author Narc
 * @date 2021-01-28
 */
@Data
public class CftRolePermission {
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
     * PERMISSION_ID
     * 权限ID
     */
    @Size(max=32)
    private String permissionId;

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