// SyncRecordService.java
package com.company.datareport.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.datareport.entity.system.SyncRecord;

import java.time.LocalDateTime;

public interface SyncRecordService extends IService<SyncRecord> {

    /**
     * 获取指定表的最新同步时间
     */
    LocalDateTime getLastSyncTime(String tableName);

    /**
     * 记录同步结果
     */
    void recordSync(String tableName, LocalDateTime syncTime, int count, boolean success, String remark);
}