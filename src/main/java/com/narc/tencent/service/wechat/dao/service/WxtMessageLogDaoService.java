package com.narc.tencent.service.wechat.dao.service;

import com.narc.tencent.service.wechat.dao.mapper.WxtMessageLogMapper;
import com.narc.tencent.service.wechat.dao.mapper.WxtMessageLogMapperExtend;
import com.narc.tencent.service.wechat.entity.WxtMessageLog;

import javax.annotation.Resource;

import com.narc.tencent.service.wechat.entity.WxtMessageLogExample;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        wxtMessageLogMapper.insertSelective(record);
    }

    public boolean isExist(String fromUserName, Date createdDatetime) {
        WxtMessageLogExample example = new WxtMessageLogExample();
        example.createCriteria().andFromUserNameEqualTo(fromUserName)
                .andCreatedDatetimeEqualTo(createdDatetime);
        return wxtMessageLogMapper.countByExample(example) > 0;
    }


}