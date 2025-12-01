package com.company.datareport.entity.sqlite;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 数据采集表
 *
 * @TableName hadwn_rest_gathr_tab_1
 */
@Data
@TableName("hadwn_rest_gathr_tab_1")
public class HadwnRestGathrTab implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 企业编码
     */
    @TableField("CORP_CD")
    private String corpCd;

    /**
     * 企业名称
     */
    @TableField("CORP_NM")
    private String corpNm;

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
     * 数据内容
     */
    @TableField("DATA_CONTT")
    private String dataContt;

    /**
     * 非结构化内容(二进制)
     */
    @TableField("NONSTRUCT_CONTT")
    private byte[] nonstructContt;

    /**
     * 行标识
     */
    @TableField("LIN_FLAG")
    private String linFlag;

    /**
     * 数据标识
     */
    @TableField("DATA_FLAG")
    private Integer dataFlag;

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
}
