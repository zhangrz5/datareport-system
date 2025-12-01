package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 补传任务实体
 *
 * @author system
 * @since 2025-11-25
 */
@Data
@TableName("t_supplement_task")
public class SupplementTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联日志ID
     */
    private String logId;

    /**
     * 任务主键ID
     */
    private String taskId;

    /**
     * 任务编码
     */
    private String taskCode;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 模板ID
     */
    private String templateId;

    /**
     * 模板编码
     */
    private String templateCode;

    /**
     * 补传开始日期
     */
    private LocalDate startDate;

    /**
     * 补传结束日期
     */
    private LocalDate endDate;

    /**
     * 截止时间
     */
    private LocalDateTime deadline;

    /**
     * 任务描述
     */
    private String taskDesc;

    /**
     * 任务文件名
     */
    private String fileName;

    /**
     * 任务文件路径
     */
    private String filePath;

    /**
     * 任务内容(解密后)
     */
    private String taskContentDecrypted;

    /**
     * 下载时间
     */
    private LocalDateTime downloadTime;

    /**
     * 任务状态: 0-待执行, 1-执行中, 2-已完成, 3-已取消, 4-已过期
     */
    private Integer taskStatus;

    /**
     * 完成时间
     */
    private LocalDateTime completeTime;

    /**
     * 完成率(%)
     */
    private BigDecimal completeRate;

    /**
     * 备注
     */
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableLogic
    private Integer deleted;
}
