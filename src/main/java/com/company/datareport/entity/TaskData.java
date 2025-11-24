package com.company.datareport.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 任务数据实体
 *
 * @author qwe
 * @since 2025-01-24
 */
@Data
@TableName("t_task_data")
public class TaskData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务ID
     */
    @TableField("task_id")
    private Long taskId;

    /**
     * 任务编号
     */
    @TableField("task_code")
    private String taskCode;

    /**
     * 数据编号
     */
    @TableField("data_code")
    private String dataCode;

    /**
     * 数据内容(JSON格式)
     */
    @TableField("data_content")
    private String dataContent;

    /**
     * 数据来源
     */
    @TableField("data_source")
    private String dataSource;

    /**
     * 数据状态: 0-未处理 1-处理中 2-已处理 3-处理失败
     */
    @TableField("data_status")
    private Integer dataStatus;

    /**
     * 上报状态: 0-未上报 1-上报中 2-上报成功 3-上报失败
     */
    @TableField("upload_status")
    private Integer uploadStatus;

    /**
     * 上报时间
     */
    @TableField("upload_time")
    private LocalDateTime uploadTime;

    /**
     * 上报响应
     */
    @TableField("upload_response")
    private String uploadResponse;

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
