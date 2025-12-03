package com.company.datareport.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.datareport.entity.system.PushLogRecord;
import com.company.datareport.mapper.system.PushLogRecordMapper;
import com.company.datareport.service.system.PushLogRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PushLogRecordServiceImpl
        extends ServiceImpl<PushLogRecordMapper, PushLogRecord>
        implements PushLogRecordService {

    @Override
    public PushLogRecord createRecord(String requestUrl, String requestBody, String startDate,
                                      String endDate, LocalDateTime requestTime) {
        PushLogRecord record = new PushLogRecord();
        record.setRequestUrl(requestUrl);
        record.setRequestBody(requestBody);
        record.setStartDate(startDate);
        record.setEndDate(endDate);
        record.setRequestTime(requestTime);
        record.setRequestStatus(0);
        record.setCreateTime(LocalDateTime.now());
        record.setUpdateTime(LocalDateTime.now());
        save(record);
        return record;
    }

    @Override
    public void updateSuccess(Long id, String responseJson, LocalDateTime responseTime, Long costTime) {
        PushLogRecord record = new PushLogRecord();
        record.setId(id);
        record.setResponseJson(responseJson);
        record.setResponseTime(responseTime);
        record.setCostTime(costTime);
        record.setRequestStatus(1);
        record.setUpdateTime(LocalDateTime.now());
        updateById(record);
    }

    @Override
    public void updateFailed(Long id, String errorMsg, LocalDateTime responseTime, Long costTime) {
        PushLogRecord record = new PushLogRecord();
        record.setId(id);
        record.setErrorMsg(errorMsg);
        record.setResponseTime(responseTime);
        record.setCostTime(costTime);
        record.setRequestStatus(2);
        record.setUpdateTime(LocalDateTime.now());
        updateById(record);
    }
}