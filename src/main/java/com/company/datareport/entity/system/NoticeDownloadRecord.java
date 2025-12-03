package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("T_NOTICE_DOWNLOAD_RECORD")
public class NoticeDownloadRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 请求URL
     */
    private String requestUrl;

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
     * 通知类型: 0007-异常反馈通知, 0009-普通通知
     */
    private String noticeType;

    /**
     * 下载状态: 0-下载中, 1-成功, 2-失败, 3-无文件
     */
    private Integer downloadStatus;

    /**
     * 服务标识: 0-失败, 1-成功, 2-无权限
     */
    private String serviceFlag;

    /**
     * 响应消息
     */
    private String responseMsg;

    /**
     * 响应ContentType
     */
    private String contentType;

    /**
     * 重试次数
     */
    private Integer retryCount;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}