package com.narc.tencent.service.wechat.dao.service;

import com.narc.tencent.service.wechat.dao.mapper.WxtUserInfoMapper;
import com.narc.tencent.service.wechat.dao.mapper.WxtUserInfoMapperExtend;
import com.narc.tencent.service.wechat.entity.WxtUserInfo;

import javax.annotation.Resource;

import com.narc.tencent.service.wechat.entity.WxtUserInfoExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * DaoService
 *
 * @author NarcMybatisGenerator
 * @date 2020-11-26
 */
@Service
public class WxtUserInfoDaoService {
    @Resource
    private WxtUserInfoMapper wxtUserInfoMapper;

    @Resource
    private WxtUserInfoMapperExtend wxtUserInfoMapperExtend;


    public WxtUserInfo getUserByOpenId(String openId) {
        WxtUserInfoExample example = new WxtUserInfoExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<WxtUserInfo> list = wxtUserInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    public void insert(WxtUserInfo wxtUserInfo) {
        wxtUserInfoMapper.insert(wxtUserInfo);
    }

    public void updateByPrimaryKeySelective(WxtUserInfo wxtUserInfo){
        wxtUserInfoMapper.updateByPrimaryKeySelective(wxtUserInfo);
    }



}