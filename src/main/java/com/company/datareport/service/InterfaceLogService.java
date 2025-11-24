package com.company.datareport.service;

import com.company.datareport.entity.InterfaceLog;

/**
 * 接口日志服务
 *
 * @author system
 * @since 2025-11-25
 */
public interface InterfaceLogService {

    /**
     * 记录接口调用日志
     */
    InterfaceLog logInterfaceCall(InterfaceLog log);

    /**
     * 更新接口日志
     */
    void updateLog(InterfaceLog log);

    /**
     * 创建日志对象
     */
    InterfaceLog createLog(String interfaceType, String businessType);
}
