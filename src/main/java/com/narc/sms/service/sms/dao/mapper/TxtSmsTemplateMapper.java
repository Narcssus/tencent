package com.narc.sms.service.sms.dao.mapper;

import com.narc.sms.service.sms.entity.TxtSmsTemplate;
import com.narc.sms.service.sms.entity.TxtSmsTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-03-19
*/
@Mapper
@SuppressWarnings("unused")
public interface TxtSmsTemplateMapper {
    long countByExample(TxtSmsTemplateExample example);

    int deleteByExample(TxtSmsTemplateExample example);

    int deleteByPrimaryKey(String templateId);

    int insert(TxtSmsTemplate record);

    int insertSelective(TxtSmsTemplate record);

    List<TxtSmsTemplate> selectByExample(TxtSmsTemplateExample example);

    TxtSmsTemplate selectByPrimaryKey(String templateId);

    int updateByExampleSelective(@Param("record") TxtSmsTemplate record, @Param("example") TxtSmsTemplateExample example);

    int updateByExample(@Param("record") TxtSmsTemplate record, @Param("example") TxtSmsTemplateExample example);

    int updateByPrimaryKeySelective(TxtSmsTemplate record);

    int updateByPrimaryKey(TxtSmsTemplate record);

    int insertBatch(List<TxtSmsTemplate> records);
}