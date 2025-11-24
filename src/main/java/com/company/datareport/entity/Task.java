package com.company.datareport.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 采集任务实体
 *
 * @author qwe
 * @since 2025-01-24
 */
@Data
@TableName("t_task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务编号(平台下发)
     */
    @TableField("task_code")
    private String taskCode;

    /**
     * 任务名称
     */
    @TableField("task_name")
    private String taskName;

    /**
     * 任务类型
     */
    @TableField("task_type")
    private String taskType;

    /**
     * 数据来源
     */
    @TableField("data_source")
    private String dataSource;

    /**
     * 任务描述
     */
    @TableField("task_desc")
    private String taskDesc;

    /**
     * 任务状态: 0-待处理 1-处理中 2-已完成 3-失败
     */
    @TableField("task_status")
    private Integer taskStatus;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 截止时间
     */
    @TableField("deadline")
    private LocalDateTime deadline;

    /**
     * 采集规则(JSON格式)
     */
    @TableField("collect_rule")
    private String collectRule;

    /**
     * 采集SQL(如果是数据库采集)
     */
    @TableField("collect_sql")
    private String collectSql;

    /**
     * 目标数据源
     */
    @TableField("target_datasource")
    private String targetDatasource;

    /**
     * 重试次数
     */
    @TableField("retry_count")
    private Integer retryCount;

    /**
     * 错误信息
     */
    @TableField("error_msg")
    private String errorMsg;

    /**
     * 执行结果(JSON格式)
     */
    @TableField("execute_result")
    private String executeResult;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新人
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 逻辑删除: 0-未删除 1-已删除
     */
    @TableLogic
    @TableField("deleted")
    private Integer deleted;
}
