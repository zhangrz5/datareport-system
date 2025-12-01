package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 远程日志同步实体
 *
 * @author system
 * @since 2025-11-25
 */
@Data
@TableName("t_remote_log_sync")
public class RemoteLogSync implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 远程日志主键ID
     */
    private String elId;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     * 报送文件名称
     */
    private String fileName;

    /**
     * 推送时间
     */
    private LocalDateTime pushDate;

    /**
     * 抓取时间
     */
    private LocalDateTime grabDate;

    /**
     * 补录标识: 1-正常上传, 0-主动补录, 其他-被动补录任务ID
     */
    private String repairMark;

    /**
     * 日志生成时间
     */
    private LocalDateTime elCreatedate;

    /**
     * 同步时间
     */
    private LocalDateTime syncTime;

    /**
     * 模块编码: DATAPUSHLOG-报送文件日志, TEMP-采集目录日志
     */
    private String moduleCode;

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
