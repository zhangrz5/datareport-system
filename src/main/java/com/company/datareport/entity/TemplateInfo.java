package com.company.datareport.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 采集模板信息实体
 *
 * @author system
 * @since 2025-11-25
 */
@Data
@TableName("t_template_info")
public class TemplateInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模板主键ID
     */
    private String templateId;

    /**
     * 模板编码
     */
    private String templateCode;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板版本号
     */
    private String templateVersion;

    /**
     * 业务外键ID
     */
    private String businessId;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     * 模板描述
     */
    private String templateDesc;

    /**
     * 模板文件名
     */
    private String fileName;

    /**
     * 模板文件路径
     */
    private String filePath;

    /**
     * 示例文件名
     */
    private String exampleFileName;

    /**
     * 示例文件路径
     */
    private String exampleFilePath;

    /**
     * 下载时间
     */
    private LocalDateTime downloadTime;

    /**
     * 状态: 0-未启用, 1-已启用, 2-已废弃
     */
    private Integer status;

    /**
     * 启用时间
     */
    private LocalDateTime activeTime;

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
