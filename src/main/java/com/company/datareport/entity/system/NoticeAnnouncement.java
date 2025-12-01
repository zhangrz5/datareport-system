package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知公告实体
 *
 * @author system
 * @since 2025-11-25
 */
@Data
@TableName("t_notice_announcement")
public class NoticeAnnouncement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联日志ID
     */
    private String logId;

    /**
     * 通知主键ID
     */
    private String noticeId;

    /**
     * 通知编码
     */
    private String noticeCode;

    /**
     * 通知标题
     */
    private String noticeTitle;

    /**
     * 通知类型: 0007-异常反馈通知, 0009-普通通知
     */
    private String noticeType;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 通知文件名
     */
    private String fileName;

    /**
     * 通知文件路径
     */
    private String filePath;

    /**
     * 通知内容(解密后)
     */
    private String noticeContentDecrypted;

    /**
     * 附件数量
     */
    private Integer attachmentCount;

    /**
     * 附件信息(JSON)
     */
    private String attachmentInfo;

    /**
     * 下载时间
     */
    private LocalDateTime downloadTime;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 阅读状态: 0-未读, 1-已读
     */
    private Integer readStatus;

    /**
     * 阅读时间
     */
    private LocalDateTime readTime;

    /**
     * 重要程度: 0-普通, 1-重要, 2-紧急
     */
    private Integer importance;

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
