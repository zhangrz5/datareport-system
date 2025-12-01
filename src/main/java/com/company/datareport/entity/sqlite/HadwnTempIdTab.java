package com.company.datareport.entity.sqlite;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 模板ID表
 *
 * @TableName hadwn_tempId_tab
 */
@Data
@TableName("hadwn_tempId_tab")
public class HadwnTempIdTab implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 模板ID
     */
    @TableField("TEMP_ID")
    private String tempId;
}
