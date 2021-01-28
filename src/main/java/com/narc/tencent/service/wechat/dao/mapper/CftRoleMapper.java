package com.narc.tencent.service.wechat.dao.mapper;

import com.narc.tencent.service.wechat.entity.CftRole;
import com.narc.tencent.service.wechat.entity.CftRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-01-28
*/
@Mapper
@SuppressWarnings("unused")
public interface CftRoleMapper {
    long countByExample(CftRoleExample example);

    int deleteByExample(CftRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CftRole record);

    int insertSelective(CftRole record);

    List<CftRole> selectByExample(CftRoleExample example);

    CftRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CftRole record, @Param("example") CftRoleExample example);

    int updateByExample(@Param("record") CftRole record, @Param("example") CftRoleExample example);

    int updateByPrimaryKeySelective(CftRole record);

    int updateByPrimaryKey(CftRole record);

    int insertBatch(List<CftRole> records);
}