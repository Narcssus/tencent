package com.narc.sms.service.sms.dao.mapper;

import com.narc.sms.service.sms.entity.TxtSmsLog;
import com.narc.sms.service.sms.entity.TxtSmsLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-03-19
*/
@Mapper
@SuppressWarnings("unused")
public interface TxtSmsLogMapper {
    long countByExample(TxtSmsLogExample example);

    int deleteByExample(TxtSmsLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TxtSmsLog record);

    int insertSelective(TxtSmsLog record);

    List<TxtSmsLog> selectByExample(TxtSmsLogExample example);

    TxtSmsLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TxtSmsLog record, @Param("example") TxtSmsLogExample example);

    int updateByExample(@Param("record") TxtSmsLog record, @Param("example") TxtSmsLogExample example);

    int updateByPrimaryKeySelective(TxtSmsLog record);

    int updateByPrimaryKey(TxtSmsLog record);

    int insertBatch(List<TxtSmsLog> records);
}