package com.company.datareport.entity.sqlite;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 指标表
 *
 * @TableName hadwn_indx_tab
 */
@Data
@TableName("hadwn_indx_tab")
public class HadwnIndxTab implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 信息资源ID
     */
    @TableField("INFO_RES_ID")
    private String infoResId;

    /**
     * 指标ID
     */
    @TableField("INDX_ID")
    private String indxId;

    /**
     * 指标名称
     */
    @TableField("INDX_NM")
    private String indxNm;

    /**
     * 指标数据类型
     */
    @TableField("INDX_DATA_TYPE")
    private String indxDataType;

    /**
     * 指标数据长度
     */
    @TableField("INDX_DATA_LEN")
    private String indxDataLen;

    /**
     * 采集周期
     */
    @TableField("CLECT_PRD")
    private String clectPrd;

    /**
     * 共享类型
     */
    @TableField("SHARE_TYPE")
    private String shareType;

    /**
     * 共享条件
     */
    @TableField("SHARE_COND")
    private String shareCond;

    /**
     * 共享方式
     */
    @TableField("SHARE_MODE")
    private String shareMode;

    /**
     * 共享方式类型
     */
    @TableField("SHARE_MODE_TYPE")
    private String shareModeType;

    /**
     * 是否开放
     */
    @TableField("IS_OPEN")
    private String isOpen;

    /**
     * 开放条件
     */
    @TableField("OPEN_COND")
    private String openCond;

    /**
     * 预留字段1
     */
    @TableField("OBLG1")
    private String oblg1;

    /**
     * 预留字段2
     */
    @TableField("OBLG2")
    private String oblg2;

    /**
     * 预留字段3
     */
    @TableField("OBLG3")
    private String oblg3;

    /**
     * 预留字段4
     */
    @TableField("OBLG4")
    private String oblg4;

    /**
     * 预留字段5
     */
    @TableField("OBLG5")
    private String oblg5;

    /**
     * 创建时间
     */
    @TableField("SETUP_TM")
    private String setupTm;

    /**
     * 更新时间
     */
    @TableField("UPD_TM")
    private String updTm;

    /**
     * 指标编码
     */
    @TableField("INDX_CODE")
    private String indxCode;

    /**
     * 是否非结构化(0-否,1-是)
     */
    @TableField("ISUNSTRUCTED")
    private String isunstructed;

    /**
     * 指标备注
     */
    @TableField("INDEX_NOTES")
    private String indexNotes;

    /**
     * 指标描述
     */
    @TableField("INDEX_DESC")
    private String indexDesc;

    /**
     * 是否允许为空
     */
    @TableField("IS_NULL")
    private String isNull;
}
