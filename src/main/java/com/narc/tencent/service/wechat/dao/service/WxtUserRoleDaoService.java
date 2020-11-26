package com.narc.tencent.service.wechat.dao.service;

import com.narc.tencent.service.wechat.dao.mapper.WxtUserRoleMapper;
import com.narc.tencent.service.wechat.dao.mapper.WxtUserRoleMapperExtend;
import com.narc.tencent.service.wechat.entity.WxtUserRole;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * DaoService
 *
 * @author NarcMybatisGenerator
 * @date 2020-11-26
 */
@Service
public class WxtUserRoleDaoService {
    @Resource
    private WxtUserRoleMapper wxtUserRoleMapper;

    @Resource
    private WxtUserRoleMapperExtend wxtUserRoleMapperExtend;

    public void insert(WxtUserRole wxtUserRole) {
        wxtUserRoleMapper.insert(wxtUserRole);
    }


}