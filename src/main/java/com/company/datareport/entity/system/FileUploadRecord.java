package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据报送记录实体
 *
 * @author system
 * @since 2025-11-25
 */
@Data
@TableName("t_file_upload_record")
public class FileUploadRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联日志ID
     */
    private String logId;

    /**
     * API编码
     */
    private String apiCode;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小(字节)
     */
    private Long fileSize;

    /**
     * 文件保存路径
     */
    private String filePath;

    /**
     * 文件MD5值
     */
    private String fileMd5;

    /**
     * 企业社会信用代码
     */
    private String socialCreditCode;

    /**
     * 上传状态: 0-待处理, 1-处理中, 2-成功, 3-失败
     */
    private Integer uploadStatus;

    /**
     * 服务标识: 0-失败, 1-成功, 2-无权限
     */
    private String serviceFlag;

    /**
     * 响应消息
     */
    private String responseMsg;

    /**
     * 解析状态: 0-未解析, 1-解析中, 2-解析成功, 3-解析失败
     */
    private Integer parseStatus;

    /**
     * 解析结果(JSON)
     */
    private String parseResult;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 重试次数
     */
    private Integer retryCount;

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
