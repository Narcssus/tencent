package com.narc.tencent.service.wechat.dao.mapper;

import com.narc.tencent.service.wechat.entity.WxtUserRole;
import com.narc.tencent.service.wechat.entity.WxtUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-01-28
*/
@Mapper
@SuppressWarnings("unused")
public interface WxtUserRoleMapper {
    long countByExample(WxtUserRoleExample example);

    int deleteByExample(WxtUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxtUserRole record);

    int insertSelective(WxtUserRole record);

    List<WxtUserRole> selectByExample(WxtUserRoleExample example);

    WxtUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxtUserRole record, @Param("example") WxtUserRoleExample example);

    int updateByExample(@Param("record") WxtUserRole record, @Param("example") WxtUserRoleExample example);

    int updateByPrimaryKeySelective(WxtUserRole record);

    int updateByPrimaryKey(WxtUserRole record);

    int insertBatch(List<WxtUserRole> records);
}