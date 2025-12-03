package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("T_PUSH_LOG_RECORD")
public class PushLogRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String requestUrl;

    private String requestBody;

    private LocalDateTime requestTime;

    private LocalDateTime responseTime;

    private Long costTime;

    private String startDate;

    private String endDate;

    /**
     * 请求状态: 0-请求中, 1-成功, 2-失败
     */
    private Integer requestStatus;

    private String responseJson;

    private String errorMsg;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}