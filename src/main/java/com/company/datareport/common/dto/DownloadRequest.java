package com.company.datareport.common.dto;

import lombok.Data;

/**
 * 下载接口请求DTO
 *
 * @author system
 * @since 2025-11-25
 */
@Data
public class DownloadRequest {

    /**
     * 业务类型
     */
    private String busType;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;

    /**
     * 系统代号(安徽模式)
     */
    private String sysCode;

    /**
     * 开始日期(日志下载)
     */
    private String startDate;

    /**
     * 结束日期(日志下载)
     */
    private String endDate;

    /**
     * 方向标识(日志下载)
     */
    private String side;
}
