package com.company.datareport.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.datareport.entity.system.SyncRecord;
import com.company.datareport.mapper.system.SyncRecordMapper;
import com.company.datareport.service.system.SyncRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SyncRecordServiceImpl extends ServiceImpl<SyncRecordMapper, SyncRecord> implements SyncRecordService {

    @Override
    public LocalDateTime getLastSyncTime(String tableName) {
        return baseMapper.getLastSyncTime(tableName);
    }

    @Override
    public void recordSync(String tableName, LocalDateTime syncTime, int count, boolean success, String remark) {
        SyncRecord record = new SyncRecord();
        record.setTableName(tableName);
        record.setSyncTime(syncTime);
        record.setSyncCount(count);
        record.setSyncStatus(success ? 1 : 0);
        record.setCreateTime(LocalDateTime.now());
        record.setRemark(remark);
        save(record);
    }
}