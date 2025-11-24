package com.company.datareport.common.dto;

import lombok.Data;

/**
 * 文件上传请求DTO
 *
 * @author system
 * @since 2025-11-25
 */
@Data
public class FileUploadRequest {

    /**
     * API编码
     */
    private String apiCode;

    /**
     * 业务类型
     */
    private String busType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 社会信用代码
     */
    private String socialCreditCode;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;
}
