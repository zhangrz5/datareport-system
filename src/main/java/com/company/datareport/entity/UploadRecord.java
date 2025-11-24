package com.company.datareport.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 上报记录实体
 *
 * @author qwe
 * @since 2025-01-24
 */
@Data
@TableName("t_upload_record")
public class UploadRecord implements Serializable {

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
     * 任务数据ID
     */
    @TableField("task_data_id")
    private Long taskDataId;

    /**
     * 批次号
     */
    @TableField("batch_no")
    private String batchNo;

    /**
     * 上报类型: 1-单条上报 2-批量上报
     */
    @TableField("upload_type")
    private Integer uploadType;

    /**
     * 上报状态: 0-未上报 1-上报中 2-上报成功 3-上报失败
     */
    @TableField("upload_status")
    private Integer uploadStatus;

    /**
     * 上报数据(JSON格式)
     */
    @TableField("upload_data")
    private String uploadData;

    /**
     * 请求URL
     */
    @TableField("request_url")
    private String requestUrl;

    /**
     * 请求参数
     */
    @TableField("request_params")
    private String requestParams;

    /**
     * 响应状态码
     */
    @TableField("response_code")
    private Integer responseCode;

    /**
     * 响应内容
     */
    @TableField("response_body")
    private String responseBody;

    /**
     * 耗时(毫秒)
     */
    @TableField("cost_time")
    private Long costTime;

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
