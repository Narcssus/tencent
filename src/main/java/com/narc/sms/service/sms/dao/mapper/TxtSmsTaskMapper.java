package com.narc.sms.service.sms.dao.mapper;

import com.narc.sms.service.sms.entity.TxtSmsTask;
import com.narc.sms.service.sms.entity.TxtSmsTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-03-23
*/
@Mapper
@SuppressWarnings("unused")
public interface TxtSmsTaskMapper {
    long countByExample(TxtSmsTaskExample example);

    int deleteByExample(TxtSmsTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TxtSmsTask record);

    int insertSelective(TxtSmsTask record);

    List<TxtSmsTask> selectByExample(TxtSmsTaskExample example);

    TxtSmsTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TxtSmsTask record, @Param("example") TxtSmsTaskExample example);

    int updateByExample(@Param("record") TxtSmsTask record, @Param("example") TxtSmsTaskExample example);

    int updateByPrimaryKeySelective(TxtSmsTask record);

    int updateByPrimaryKey(TxtSmsTask record);

    int insertBatch(List<TxtSmsTask> records);
}