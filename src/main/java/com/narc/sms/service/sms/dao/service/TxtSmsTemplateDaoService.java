package com.narc.sms.service.sms.dao.service;

import com.narc.sms.service.sms.dao.mapper.TxtSmsTemplateMapper;
import com.narc.sms.service.sms.dao.mapper.TxtSmsTemplateMapperExtend;
import com.narc.sms.service.sms.entity.TxtSmsTemplate;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * DaoService
 *
 * @author NarcMybatisGenerator
 * @date 2021-03-19
 */
@Service
public class TxtSmsTemplateDaoService {
    @Resource
    private TxtSmsTemplateMapper txtSmsTemplateMapper;

    @Resource
    private TxtSmsTemplateMapperExtend txtSmsTemplateMapperExtend;

    public String getContentById(String id) {
        TxtSmsTemplate template = txtSmsTemplateMapper.selectByPrimaryKey(id);
        if (template == null) {
            return null;
        }
        return template.getTemplateContent();
    }
}