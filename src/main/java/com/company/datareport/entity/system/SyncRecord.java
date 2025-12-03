package com.company.datareport.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sync_record")
public class SyncRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 本次同步截止时间
     */
    private LocalDateTime syncTime;

    /**
     * 本次同步数据量
     */
    private Integer syncCount;

    /**
     * 状态: 0-失败 1-成功
     */
    private Integer syncStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 备注
     */
    private String remark;
}