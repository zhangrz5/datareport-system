package com.company.datareport.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.datareport.entity.system.RemoteLogSync;
import org.apache.ibatis.annotations.Mapper;

/**
 * 远程日志同步Mapper
 *
 * @author system
 * @since 2025-11-25
 */
@Mapper
public interface RemoteLogSyncMapper extends BaseMapper<RemoteLogSync> {
}
