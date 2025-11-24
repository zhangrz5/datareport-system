package com.company.datareport.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 日志下载响应DTO
 *
 * @author system
 * @since 2025-11-25
 */
@Data
public class LogDownloadDTO {

    /**
     * 日志ID
     */
    private String logId;

    /**
     * 接口类型
     */
    private String interfaceType;

    /**
     * 接口地址
     */
    private String interfaceUrl;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestTime;

    /**
     * 响应时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime responseTime;

    /**
     * 耗时(毫秒)
     */
    private Long costTime;

    /**
     * 服务标识: 0-失败, 1-成功, 2-无权限, 3-无新文件
     */
    private String serviceFlag;

    /**
     * 响应消息
     */
    private String responseMsg;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小(字节)
     */
    private Long fileSize;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 社会信用代码
     */
    private String socialCreditCode;

    /**
     * 处理状态: 0-未处理, 1-处理中, 2-处理成功, 3-处理失败
     */
    private Integer status;

    /**
     * 错误信息
     */
    private String errorMsg;
}
