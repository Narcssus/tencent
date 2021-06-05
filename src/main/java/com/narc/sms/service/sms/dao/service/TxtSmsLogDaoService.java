package com.narc.sms.service.sms.dao.service;

import com.narc.sms.service.sms.dao.mapper.TxtSmsLogMapper;
import com.narc.sms.service.sms.dao.mapper.TxtSmsLogMapperExtend;
import com.narc.sms.service.sms.entity.TxtSmsLog;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * DaoService
 * @author NarcMybatisGenerator
 * @date 2021-03-19
 */
@Service
public class TxtSmsLogDaoService {
    @Resource
    private TxtSmsLogMapper txtSmsLogMapper;

    @Resource
    private TxtSmsLogMapperExtend txtSmsLogMapperExtend;

    public void insertOne(TxtSmsLog log){
        txtSmsLogMapper.insertSelective(log);
    }
}