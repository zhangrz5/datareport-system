package com.company.datareport.entity.sqlite;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 信息资源表
 *
 * @TableName hadwn_info_res_tab
 */
@Data
@TableName("hadwn_info_res_tab")
public class HadwnInfoResTab implements Serializable {

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
     * 信息资源编码
     */
    @TableField("INFO_RES_CD")
    private String infoResCd;

    /**
     * 父分类ID
     */
    @TableField("PARENT_CATEGORY_ID")
    private String parentCategoryId;

    /**
     * 信息资源名称
     */
    @TableField("INFO_RES_NAME")
    private String infoResName;

    /**
     * 采集范围
     */
    @TableField("CLECT_RANGE")
    private String clectRange;

    /**
     * 是否涉密
     */
    @TableField("IS_SCRT")
    private String isScrt;

    /**
     * 信息资源摘要
     */
    @TableField("INFO_RES_ABS")
    private String infoResAbs;

    /**
     * 联系人姓名
     */
    @TableField("CONTACTS_NAME")
    private String contactsName;

    /**
     * 联系人电话
     */
    @TableField("CONTACTS_PHONE")
    private String contactsPhone;

    /**
     * 联系人手机
     */
    @TableField("CONTACTS_MOBILE")
    private String contactsMobile;

    /**
     * 联系人邮箱
     */
    @TableField("CONTACTS_EMAIL")
    private String contactsEmail;

    /**
     * 信息资源路径
     */
    @TableField("INFO_RES_ROUTE")
    private String infoResRoute;

    /**
     * 审核状态
     */
    @TableField("AUDIT_STAT")
    private String auditStat;

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
     * 数据范围
     */
    @TableField("DATA_RANGE")
    private String dataRange;

    /**
     * 采集类型
     */
    @TableField("COLLECT_TYPE")
    private Integer collectType;
}
