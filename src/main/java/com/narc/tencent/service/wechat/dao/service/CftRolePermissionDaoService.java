package com.narc.tencent.service.wechat.dao.service;

import com.narc.tencent.service.wechat.dao.mapper.CftRolePermissionMapper;
import com.narc.tencent.service.wechat.dao.mapper.CftRolePermissionMapperExtend;
import com.narc.tencent.service.wechat.entity.CftRolePermission;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * DaoService
 * @author NarcMybatisGenerator
 * @date 2020-11-26
 */
@Service
public class CftRolePermissionDaoService {
    @Resource
    private CftRolePermissionMapper cftRolePermissionMapper;

    @Resource
    private CftRolePermissionMapperExtend cftRolePermissionMapperExtend;
}