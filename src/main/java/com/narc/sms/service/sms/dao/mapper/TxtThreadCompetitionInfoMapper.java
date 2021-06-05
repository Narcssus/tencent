package com.narc.sms.service.sms.dao.mapper;

import com.narc.sms.service.sms.entity.TxtThreadCompetitionInfo;
import com.narc.sms.service.sms.entity.TxtThreadCompetitionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-03-19
*/
@Mapper
@SuppressWarnings("unused")
public interface TxtThreadCompetitionInfoMapper {
    long countByExample(TxtThreadCompetitionInfoExample example);

    int deleteByExample(TxtThreadCompetitionInfoExample example);

    int deleteByPrimaryKey(Integer threadId);

    int insert(TxtThreadCompetitionInfo record);

    int insertSelective(TxtThreadCompetitionInfo record);

    List<TxtThreadCompetitionInfo> selectByExample(TxtThreadCompetitionInfoExample example);

    TxtThreadCompetitionInfo selectByPrimaryKey(Integer threadId);

    int updateByExampleSelective(@Param("record") TxtThreadCompetitionInfo record, @Param("example") TxtThreadCompetitionInfoExample example);

    int updateByExample(@Param("record") TxtThreadCompetitionInfo record, @Param("example") TxtThreadCompetitionInfoExample example);

    int updateByPrimaryKeySelective(TxtThreadCompetitionInfo record);

    int updateByPrimaryKey(TxtThreadCompetitionInfo record);

    int insertBatch(List<TxtThreadCompetitionInfo> records);
}