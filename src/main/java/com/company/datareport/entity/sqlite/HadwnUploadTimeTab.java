package com.company.datareport.entity.sqlite;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 上传时间表
 *
 * @TableName hadwn_upload_time_tab
 */
@Data
@TableName("hadwn_upload_time_tab")
public class HadwnUploadTimeTab implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 上传时间
     */
    @TableField("UPLOAD_TIME")
    private String uploadTime;
}
