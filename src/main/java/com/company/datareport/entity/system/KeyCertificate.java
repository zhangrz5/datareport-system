package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 密钥证书实体
 *
 * @author system
 * @since 2025-11-25
 */
@Data
@TableName("t_key_certificate")
public class KeyCertificate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联日志ID
     */
    private String logId;

    /**
     * 密钥主键ID
     */
    private String keyId;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 密钥版本号
     */
    private String keyVersion;

    /**
     * SM2公钥(加密存储)
     */
    private String sm2PublicKey;

    /**
     * SM4密钥(加密存储)
     */
    private String sm4Key;

    /**
     * SM2公钥(解密后明文)
     */
    private String sm2PublicKeyDecrypted;

    /**
     * SM4密钥(解密后明文)
     */
    private String sm4KeyDecrypted;

    /**
     * 密钥文件名
     */
    private String fileName;

    /**
     * 文件保存路径
     */
    private String filePath;

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
     * 过期时间
     */
    private LocalDateTime expireTime;

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
