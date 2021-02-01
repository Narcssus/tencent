package com.narc.tencent.service.wechat.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * CFT_PERMISSION
 * 配置表-权限
 * 
 * @author Narc
 * @date 2021-02-01
 */
@Data
public class CftPermission {
    /**
     * ID
     * 主键
     */
    @NotNull
    private Integer id;

    /**
     * PERMISSION_ID
     * 权限ID
     */
    @Size(max=32)
    private String permissionId;

    /**
     * PERMISSION_NAME
     * 权限名
     */
    @Size(max=64)
    private String permissionName;

    /**
     * COMMAND
     * 口令
     */
    @Size(max=64)
    private String command;

    /**
     * COMMAND_DESC
     * 口令描述
     */
    @Size(max=256)
    private String commandDesc;

    /**
     * TYPE
     * 权限类型
     */
    @Size(max=7)
    @NotNull
    private String type;

    /**
     * IS_SHOW
     * 是否展示 0-不展示;1-展示
     */
    @Size(max=1)
    private String isShow;

    /**
     * HELP
     * 帮助内容
     */
    @Size(max=255)
    private String help;

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