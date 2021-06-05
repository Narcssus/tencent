package com.narc.sms.service.sms.dao.service;

import com.narc.sms.service.sms.dao.mapper.TxtSmsWhitePhoneMapper;
import com.narc.sms.service.sms.dao.mapper.TxtSmsWhitePhoneMapperExtend;
import com.narc.sms.service.sms.entity.TxtSmsWhitePhone;
import javax.annotation.Resource;

import com.narc.sms.service.sms.entity.TxtSmsWhitePhoneExample;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * DaoService
 * @author NarcMybatisGenerator
 * @date 2021-03-19
 */
@Service
public class TxtSmsWhitePhoneDaoService {
    @Resource
    private TxtSmsWhitePhoneMapper txtSmsWhitePhoneMapper;

    @Resource
    private TxtSmsWhitePhoneMapperExtend txtSmsWhitePhoneMapperExtend;

    public List<String> selectWhitePhone(List<String> phones){
        TxtSmsWhitePhoneExample example = new TxtSmsWhitePhoneExample();
        example.createCriteria().andPhoneNoIn(phones);
        List<TxtSmsWhitePhone> list = txtSmsWhitePhoneMapper.selectByExample(example);
        return list.stream().map(TxtSmsWhitePhone::getPhoneNo).collect(Collectors.toList());
    }
}