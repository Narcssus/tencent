package com.narc.tencent.service.wechat.dao.mapper;

import com.narc.tencent.service.wechat.entity.CftRolePermission;
import com.narc.tencent.service.wechat.entity.CftRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2020-11-26
*/
@Mapper
@SuppressWarnings("unused")
public interface CftRolePermissionMapper {
    long countByExample(CftRolePermissionExample example);

    int deleteByExample(CftRolePermissionExample example);

    int deleteByPrimaryKey(String id);

    int insert(CftRolePermission record);

    int insertSelective(CftRolePermission record);

    List<CftRolePermission> selectByExample(CftRolePermissionExample example);

    CftRolePermission selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CftRolePermission record, @Param("example") CftRolePermissionExample example);

    int updateByExample(@Param("record") CftRolePermission record, @Param("example") CftRolePermissionExample example);

    int updateByPrimaryKeySelective(CftRolePermission record);

    int updateByPrimaryKey(CftRolePermission record);

    int insertBatch(List<CftRolePermission> records);
}