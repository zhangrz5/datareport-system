package com.company.datareport.service.system.impl;

import com.company.datareport.entity.system.InterfaceLog;
import com.company.datareport.mapper.system.InterfaceLogMapper;
import com.company.datareport.service.system.InterfaceLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 接口日志服务实现
 *
 * @author system
 * @since 2025-11-25
 */
@Slf4j
@Service
public class InterfaceLogServiceImpl implements InterfaceLogService {

    @Autowired
    private InterfaceLogMapper logMapper;

    @Override
    public InterfaceLog logInterfaceCall(InterfaceLog log) {
        if (log.getLogId() == null) {
            log.setLogId(UUID.randomUUID().toString().replace("-", ""));
        }
        if (log.getRequestTime() == null) {
            log.setRequestTime(LocalDateTime.now());
        }
        if (log.getRetryCount() == null) {
            log.setRetryCount(0);
        }
        logMapper.insert(log);
        return log;
    }

    @Override
    public void updateLog(InterfaceLog log) {
        if (log.getResponseTime() == null) {
            log.setResponseTime(LocalDateTime.now());
        }
        if (log.getRequestTime() != null && log.getResponseTime() != null) {
            long costTime = java.time.Duration.between(log.getRequestTime(), log.getResponseTime()).toMillis();
            log.setCostTime(costTime);
        }
        logMapper.updateById(log);
    }

    @Override
    public InterfaceLog createLog(String interfaceType, String businessType) {
        InterfaceLog log = new InterfaceLog();
        log.setLogId(UUID.randomUUID().toString().replace("-", ""));
        log.setInterfaceType(interfaceType);
        log.setBusinessType(businessType);
        log.setRequestTime(LocalDateTime.now());
        log.setRetryCount(0);
        log.setStatus(0);
        return log;
    }
}
