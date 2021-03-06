package com.narc.tencent.service.wechat.dao.service;

import com.narc.tencent.service.wechat.dao.mapper.WxtUserInfoMapper;
import com.narc.tencent.service.wechat.dao.mapper.WxtUserInfoMapperExtend;
import com.narc.tencent.service.wechat.entity.WxtUserInfo;

import javax.annotation.Resource;

import com.narc.tencent.service.wechat.entity.WxtUserInfoExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
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

    public void insertSelective(WxtUserInfo wxtUserInfo) {
        wxtUserInfo.setCreatedDatetime(new Date());
        wxtUserInfo.setModifiedDatetime(new Date());
        wxtUserInfoMapper.insertSelective(wxtUserInfo);
    }

    public void updateByPrimaryKeySelective(WxtUserInfo wxtUserInfo) {
        wxtUserInfo.setModifiedDatetime(new Date());
        wxtUserInfoMapper.updateByPrimaryKeySelective(wxtUserInfo);
    }

    public boolean isExistName(String name) {
        WxtUserInfoExample example = new WxtUserInfoExample();
        example.createCriteria().andNameEqualTo(name);
        return wxtUserInfoMapper.countByExample(example) > 0;
    }


}