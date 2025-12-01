package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 接口调用统一日志实体
 *
 * @author system
 * @since 2025-11-25
 */
@Data
@TableName("t_interface_log")
public class InterfaceLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 日志ID(UUID)
     */
    private String logId;

    /**
     * 接口类型: FILE_UPLOAD-数据报送, KEY_DOWNLOAD-密钥下载, TEMP_DOWNLOAD-采集目录下载,
     * TASK_DOWNLOAD-任务下载, NOTICE_DOWNLOAD-通知下载, LOG_DOWNLOAD-日志下载
     */
    private String interfaceType;

    /**
     * 接口地址
     */
    private String interfaceUrl;

    /**
     * 请求方法: GET/POST
     */
    private String requestMethod;

    /**
     * 请求参数(JSON)
     */
    private String requestParams;

    /**
     * 请求时间
     */
    private LocalDateTime requestTime;

    /**
     * 响应时间
     */
    private LocalDateTime responseTime;

    /**
     * 耗时(毫秒)
     */
    private Long costTime;

    /**
     * 响应状态码
     */
    private Integer responseCode;

    /**
     * 服务标识: 0-失败, 1-成功, 2-无权限, 3-无新文件
     */
    private String serviceFlag;

    /**
     * 响应消息
     */
    private String responseMsg;

    /**
     * 响应内容
     */
    private String responseBody;

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
     * 业务类型
     */
    private String businessType;

    /**
     * 社会信用代码
     */
    private String socialCreditCode;

    /**
     * 处理状态: 0-未处理, 1-处理中, 2-处理成功, 3-处理失败
     */
    private Integer status;

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

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 逻辑删除: 0-未删除 1-已删除
     */
    @TableLogic
    private Integer deleted;
}
