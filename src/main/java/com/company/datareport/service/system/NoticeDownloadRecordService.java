package com.company.datareport.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.datareport.entity.system.NoticeDownloadRecord;

import java.time.LocalDateTime;

public interface NoticeDownloadRecordService extends IService<NoticeDownloadRecord> {

    /**
     * 创建下载记录
     */
    NoticeDownloadRecord createRecord(String requestUrl, LocalDateTime requestTime);

    /**
     * 更新下载成功
     */
    void updateSuccess(Long id, String fileName, String filePath, Long fileSize,
                       String contentType, LocalDateTime responseTime, Long costTime);

    /**
     * 更新下载失败
     */
    void updateFailed(Long id, String serviceFlag, String responseMsg, String errorMsg,
                      String contentType, LocalDateTime responseTime, Long costTime);

    /**
     * 更新无文件
     */
    void updateNoFile(Long id, String responseMsg, String contentType,
                      LocalDateTime responseTime, Long costTime);
}