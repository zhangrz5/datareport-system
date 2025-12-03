package com.company.datareport.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.datareport.entity.system.SyncRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface SyncRecordMapper extends BaseMapper<SyncRecord> {

    /**
     * 获取指定表的最新同步时间
     */
    @Select("SELECT sync_time FROM sync_record WHERE table_name = #{tableName} AND sync_status = 1 ORDER BY sync_time DESC LIMIT 1")
    LocalDateTime getLastSyncTime(@Param("tableName") String tableName);
}