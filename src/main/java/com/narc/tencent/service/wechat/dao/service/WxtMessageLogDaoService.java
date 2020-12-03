package com.narc.tencent.service.wechat.dao.service;

import com.narc.tencent.service.wechat.dao.mapper.WxtMessageLogMapper;
import com.narc.tencent.service.wechat.dao.mapper.WxtMessageLogMapperExtend;
import com.narc.tencent.service.wechat.entity.WxtMessageLog;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * DaoService
 *
 * @author NarcMybatisGenerator
 * @date 2020-11-30
 */
@Service
public class WxtMessageLogDaoService {
    @Resource
    private WxtMessageLogMapper wxtMessageLogMapper;

    @Resource
    private WxtMessageLogMapperExtend wxtMessageLogMapperExtend;

    public void insertOne(WxtMessageLog record) {
        wxtMessageLogMapper.insert(record);
    }


}