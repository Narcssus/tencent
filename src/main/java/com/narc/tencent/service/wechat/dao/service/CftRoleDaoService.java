package com.narc.tencent.service.wechat.dao.service;

import com.narc.tencent.service.wechat.dao.mapper.CftRoleMapper;
import com.narc.tencent.service.wechat.dao.mapper.CftRoleMapperExtend;
import com.narc.tencent.service.wechat.entity.CftRole;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * DaoService
 * @author NarcMybatisGenerator
 * @date 2020-11-26
 */
@Service
public class CftRoleDaoService {
    @Resource
    private CftRoleMapper cftRoleMapper;

    @Resource
    private CftRoleMapperExtend cftRoleMapperExtend;
}