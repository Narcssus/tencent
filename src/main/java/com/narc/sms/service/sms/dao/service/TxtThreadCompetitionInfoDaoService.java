package com.narc.sms.service.sms.dao.service;

import com.narc.sms.service.sms.dao.mapper.TxtThreadCompetitionInfoMapper;
import com.narc.sms.service.sms.dao.mapper.TxtThreadCompetitionInfoMapperExtend;
import com.narc.sms.service.sms.entity.TxtThreadCompetitionInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * DaoService
 * @author NarcMybatisGenerator
 * @date 2021-03-19
 */
@Service
public class TxtThreadCompetitionInfoDaoService {
    @Resource
    private TxtThreadCompetitionInfoMapper txtThreadCompetitionInfoMapper;

    @Resource
    private TxtThreadCompetitionInfoMapperExtend txtThreadCompetitionInfoMapperExtend;

    public TxtThreadCompetitionInfo getCompetitionInfo(int threadId) {
        return txtThreadCompetitionInfoMapper.selectByPrimaryKey(threadId);
    }

    public void updateCompetitionInfo(TxtThreadCompetitionInfo info) {
        txtThreadCompetitionInfoMapper.updateByPrimaryKeySelective(info);
    }


}