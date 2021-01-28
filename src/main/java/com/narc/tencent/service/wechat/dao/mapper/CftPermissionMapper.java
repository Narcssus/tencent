package com.narc.tencent.service.wechat.dao.mapper;

import com.narc.tencent.service.wechat.entity.CftPermission;
import com.narc.tencent.service.wechat.entity.CftPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-01-28
*/
@Mapper
@SuppressWarnings("unused")
public interface CftPermissionMapper {
    long countByExample(CftPermissionExample example);

    int deleteByExample(CftPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CftPermission record);

    int insertSelective(CftPermission record);

    List<CftPermission> selectByExample(CftPermissionExample example);

    CftPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CftPermission record, @Param("example") CftPermissionExample example);

    int updateByExample(@Param("record") CftPermission record, @Param("example") CftPermissionExample example);

    int updateByPrimaryKeySelective(CftPermission record);

    int updateByPrimaryKey(CftPermission record);

    int insertBatch(List<CftPermission> records);
}