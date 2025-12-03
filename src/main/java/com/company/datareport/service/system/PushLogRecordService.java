package com.company.datareport.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.datareport.entity.system.PushLogRecord;

import java.time.LocalDateTime;

public interface PushLogRecordService extends IService<PushLogRecord> {

    PushLogRecord createRecord(String requestUrl, String requestBody, String startDate,
                               String endDate, LocalDateTime requestTime);

    void updateSuccess(Long id, String responseJson, LocalDateTime responseTime, Long costTime);

    void updateFailed(Long id, String errorMsg, LocalDateTime responseTime, Long costTime);
}