package com.company.datareport.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 日志下载响应DTO (符合国资委接口文档规范)
 *
 * @author system
 * @since 2025-11-25
 */
@Data
public class LogDownloadDTO {

    /**
     * 日志主键ID
     */
    @JsonProperty("el_id")
    private String elId;

    /**
     * 业务编码
     */
    @JsonProperty("business_code")
    private String businessCode;

    /**
     * 报送文件名称
     */
    @JsonProperty("file_name")
    private String fileName;

    /**
     * 推送时间
     */
    @JsonProperty("push_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pushDate;

    /**
     * 抓取时间
     */
    @JsonProperty("grab_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime grabDate;

    /**
     * 补录标识: 1-正常上传, 0-主动补录, 其他-被动补录任务ID
     */
    @JsonProperty("repair_mark")
    private String repairMark;

    /**
     * 日志生成时间
     */
    @JsonProperty("el_createdate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime elCreatedate;
}
