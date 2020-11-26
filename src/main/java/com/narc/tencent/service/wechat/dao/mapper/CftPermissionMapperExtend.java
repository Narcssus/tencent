package com.narc.tencent.service.wechat.dao.mapper;

import com.narc.tencent.service.wechat.entity.CftPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * @author NarcMybatisGenerator
 * @date 2020-11-26
 */
@Mapper
@SuppressWarnings("unused")
public interface CftPermissionMapperExtend extends CftPermissionMapper {

    List<CftPermission> selectAllByUserId(String userId);

}