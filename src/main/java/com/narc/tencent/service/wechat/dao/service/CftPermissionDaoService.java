package com.narc.tencent.service.wechat.dao.service;

import com.narc.tencent.service.wechat.dao.mapper.CftPermissionMapper;
import com.narc.tencent.service.wechat.dao.mapper.CftPermissionMapperExtend;
import com.narc.tencent.service.wechat.entity.CftPermission;
import javax.annotation.Resource;

import com.narc.tencent.service.wechat.entity.CftPermissionExample;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DaoService
 * @author NarcMybatisGenerator
 * @date 2020-11-26
 */
@Service
public class CftPermissionDaoService {
    @Resource
    private CftPermissionMapper cftPermissionMapper;

    @Resource
    private CftPermissionMapperExtend cftPermissionMapperExtend;


    public List<CftPermission> selectAllByUserId(String userId) {
        return cftPermissionMapperExtend.selectAllByUserId(userId);
    }





}