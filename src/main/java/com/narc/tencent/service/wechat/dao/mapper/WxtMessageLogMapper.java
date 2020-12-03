package com.narc.tencent.service.wechat.dao.mapper;

import com.narc.tencent.service.wechat.entity.WxtMessageLog;
import com.narc.tencent.service.wechat.entity.WxtMessageLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2020-11-30
*/
@Mapper
@SuppressWarnings("unused")
public interface WxtMessageLogMapper {
    long countByExample(WxtMessageLogExample example);

    int deleteByExample(WxtMessageLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(WxtMessageLog record);

    int insertSelective(WxtMessageLog record);

    List<WxtMessageLog> selectByExample(WxtMessageLogExample example);

    WxtMessageLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WxtMessageLog record, @Param("example") WxtMessageLogExample example);

    int updateByExample(@Param("record") WxtMessageLog record, @Param("example") WxtMessageLogExample example);

    int updateByPrimaryKeySelective(WxtMessageLog record);

    int updateByPrimaryKey(WxtMessageLog record);

    int insertBatch(List<WxtMessageLog> records);
}