package com.narc.sms.service.sms.dao.mapper;

import com.narc.sms.service.sms.entity.TxtSmsWhitePhone;
import com.narc.sms.service.sms.entity.TxtSmsWhitePhoneExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-03-19
*/
@Mapper
@SuppressWarnings("unused")
public interface TxtSmsWhitePhoneMapper {
    long countByExample(TxtSmsWhitePhoneExample example);

    int deleteByExample(TxtSmsWhitePhoneExample example);

    int deleteByPrimaryKey(String phoneNo);

    int insert(TxtSmsWhitePhone record);

    int insertSelective(TxtSmsWhitePhone record);

    List<TxtSmsWhitePhone> selectByExample(TxtSmsWhitePhoneExample example);

    TxtSmsWhitePhone selectByPrimaryKey(String phoneNo);

    int updateByExampleSelective(@Param("record") TxtSmsWhitePhone record, @Param("example") TxtSmsWhitePhoneExample example);

    int updateByExample(@Param("record") TxtSmsWhitePhone record, @Param("example") TxtSmsWhitePhoneExample example);

    int updateByPrimaryKeySelective(TxtSmsWhitePhone record);

    int updateByPrimaryKey(TxtSmsWhitePhone record);

    int insertBatch(List<TxtSmsWhitePhone> records);
}