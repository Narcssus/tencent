package com.narc.sms.service.sms.dao.service;

import com.alibaba.fastjson.JSON;
import com.narc.sms.service.sms.dao.mapper.TxtSmsTaskMapper;
import com.narc.sms.service.sms.dao.mapper.TxtSmsTaskMapperExtend;
import com.narc.sms.service.sms.entity.TxtSmsTask;

import javax.annotation.Resource;

import com.narc.sms.service.sms.entity.TxtSmsTaskExample;
import com.narc.sms.service.sms.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * DaoService
 *
 * @author NarcMybatisGenerator
 * @date 2021-03-19
 */
@Service
public class TxtSmsTaskDaoService {
    @Resource
    private TxtSmsTaskMapper txtSmsTaskMapper;

    @Resource
    private TxtSmsTaskMapperExtend txtSmsTaskMapperExtend;

    public void insertOne(TxtSmsTask task) {
        txtSmsTaskMapper.insertSelective(task);
    }

    public List<TxtSmsTask> selectByPhoneNo(String phoneNo) {
        TxtSmsTaskExample example = new TxtSmsTaskExample();
        example.createCriteria().andPhoneNumberSetEqualTo(JSON.toJSONString(Collections.singletonList(phoneNo)))
                .andStatusEqualTo("0");
        return txtSmsTaskMapper.selectByExample(example);
    }

    public List<TxtSmsTask> selectLast12HourByPhoneNo(String phoneNo) {
        TxtSmsTaskExample example = new TxtSmsTaskExample();
        example.createCriteria().andPhoneNumberSetEqualTo(JSON.toJSONString(Collections.singletonList(phoneNo)))
                .andStatusNotEqualTo("0")
                .andSendTimeGreaterThanOrEqualTo(DateUtils.addHours(new Date(),-12));
        return txtSmsTaskMapper.selectByExample(example);
    }

    public List<TxtSmsTask> selectByPhoneNoAndSeqNo(String phoneNo, String seqNo) {
        TxtSmsTaskExample example = new TxtSmsTaskExample();
        example.createCriteria().andPhoneNumberSetEqualTo(JSON.toJSONString(Collections.singletonList(phoneNo)))
                .andExtDataAEqualTo(seqNo)
                .andStatusEqualTo("0");
        return txtSmsTaskMapper.selectByExample(example);
    }

    public List<TxtSmsTask> selectBySendTime(Date endTime) {
        TxtSmsTaskExample example = new TxtSmsTaskExample();
        // SEND_TIME <= endTime
        example.createCriteria()
                .andStatusEqualTo("0")
                .andSendTimeLessThanOrEqualTo(endTime);
        return txtSmsTaskMapper.selectByExample(example);
    }

    public void updateByPrimaryKeySelective(TxtSmsTask task) {
        txtSmsTaskMapper.updateByPrimaryKeySelective(task);
    }

    public int cancelTask(List<Integer> ids) {
        TxtSmsTaskExample example = new TxtSmsTaskExample();
        example.createCriteria()
                .andStatusEqualTo("0")
                .andIdIn(ids);
        TxtSmsTask newTask = new TxtSmsTask();
        newTask.setStatus("2");
        newTask.setModifiedDatetime(new Date());
        return txtSmsTaskMapper.updateByExampleSelective(newTask, example);
    }


}