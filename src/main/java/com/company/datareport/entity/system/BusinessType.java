package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 业务类型实体
 *
 * @author system
 * @since 2025-11-25
 */
@Data
@TableName("t_business_type")
public class BusinessType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 业务主键ID
     */
    private String businessId;

    /**
     * 业务编码
     */
    private String businessCode;

    /**
     * 业务名称
     */
    private String businessName;

    /**
     * 业务描述
     */
    private String businessDesc;

    /**
     * 状态: 0-禁用, 1-启用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sortOrder;

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
