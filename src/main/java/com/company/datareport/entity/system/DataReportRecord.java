package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("DATA_REPORT_RECORD")
public class DataReportRecord {

    @TableId(type = IdType.AUTO)  // 改为自增
    private Long id;

    private Integer totalCount;

    private String reportDetail;

    private Integer isReported;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
