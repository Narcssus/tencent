package com.narc.tencent.service.wechat.dao.mapper;

import com.narc.tencent.service.wechat.entity.WxtUserInfo;
import com.narc.tencent.service.wechat.entity.WxtUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author NarcMybatisGenerator
 * @date 2021-01-28
*/
@Mapper
@SuppressWarnings("unused")
public interface WxtUserInfoMapper {
    long countByExample(WxtUserInfoExample example);

    int deleteByExample(WxtUserInfoExample example);

    int deleteByPrimaryKey(String userId);

    int insert(WxtUserInfo record);

    int insertSelective(WxtUserInfo record);

    List<WxtUserInfo> selectByExample(WxtUserInfoExample example);

    WxtUserInfo selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") WxtUserInfo record, @Param("example") WxtUserInfoExample example);

    int updateByExample(@Param("record") WxtUserInfo record, @Param("example") WxtUserInfoExample example);

    int updateByPrimaryKeySelective(WxtUserInfo record);

    int updateByPrimaryKey(WxtUserInfo record);

    int insertBatch(List<WxtUserInfo> records);
}