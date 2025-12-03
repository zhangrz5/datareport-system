package com.company.datareport.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.datareport.entity.system.NoticeDownloadRecord;
import com.company.datareport.mapper.system.NoticeDownloadRecordMapper;
import com.company.datareport.service.system.NoticeDownloadRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoticeDownloadRecordServiceImpl
        extends ServiceImpl<NoticeDownloadRecordMapper, NoticeDownloadRecord>
        implements NoticeDownloadRecordService {

    @Override
    public NoticeDownloadRecord createRecord(String requestUrl, LocalDateTime requestTime) {
        NoticeDownloadRecord record = new NoticeDownloadRecord();
        record.setRequestUrl(requestUrl);
        record.setRequestTime(requestTime);
        record.setDownloadStatus(0);
        record.setRetryCount(0);
        record.setCreateTime(LocalDateTime.now());
        record.setUpdateTime(LocalDateTime.now());
        save(record);
        return record;
    }

    @Override
    public void updateSuccess(Long id, String fileName, String filePath, Long fileSize,
                              String contentType, LocalDateTime responseTime, Long costTime) {
        NoticeDownloadRecord record = new NoticeDownloadRecord();
        record.setId(id);
        record.setFileName(fileName);
        record.setFilePath(filePath);
        record.setFileSize(fileSize);
        record.setContentType(contentType);
        record.setResponseTime(responseTime);
        record.setCostTime(costTime);
        record.setDownloadStatus(1);
        record.setUpdateTime(LocalDateTime.now());
        updateById(record);
    }

    @Override
    public void updateFailed(Long id, String serviceFlag, String responseMsg, String errorMsg,
                             String contentType, LocalDateTime responseTime, Long costTime) {
        NoticeDownloadRecord record = getById(id);
        if (record != null) {
            record.setDownloadStatus(2);
            record.setServiceFlag(serviceFlag);
            record.setResponseMsg(responseMsg);
            record.setErrorMsg(errorMsg);
            record.setContentType(contentType);
            record.setResponseTime(responseTime);
            record.setCostTime(costTime);
            record.setRetryCount(record.getRetryCount() + 1);
            record.setUpdateTime(LocalDateTime.now());
            updateById(record);
        }
    }

    @Override
    public void updateNoFile(Long id, String responseMsg, String contentType,
                             LocalDateTime responseTime, Long costTime) {
        NoticeDownloadRecord record = new NoticeDownloadRecord();
        record.setId(id);
        record.setDownloadStatus(3);
        record.setResponseMsg(responseMsg);
        record.setContentType(contentType);
        record.setResponseTime(responseTime);
        record.setCostTime(costTime);
        record.setUpdateTime(LocalDateTime.now());
        updateById(record);
    }
}