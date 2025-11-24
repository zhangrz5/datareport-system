-- 数据采集上报系统数据库初始化脚本
-- 数据库: MySQL

-- 创建数据库
-- CREATE DATABASE datareport DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- ============================================================
-- 任务表
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_task` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `task_code` VARCHAR(100) NOT NULL COMMENT '任务编号',
    `task_name` VARCHAR(200) NOT NULL COMMENT '任务名称',
    `task_type` VARCHAR(50) COMMENT '任务类型',
    `data_source` VARCHAR(50) COMMENT '数据来源',
    `task_desc` TEXT COMMENT '任务描述',
    `task_status` INT DEFAULT 0 COMMENT '任务状态: 0-待处理 1-处理中 2-已完成 3-失败',
    `start_time` DATETIME COMMENT '开始时间',
    `end_time` DATETIME COMMENT '结束时间',
    `deadline` DATETIME COMMENT '截止时间',
    `collect_rule` TEXT COMMENT '采集规则(JSON格式)',
    `collect_sql` TEXT COMMENT '采集SQL',
    `target_datasource` VARCHAR(50) COMMENT '目标数据源',
    `retry_count` INT DEFAULT 0 COMMENT '重试次数',
    `error_msg` TEXT COMMENT '错误信息',
    `execute_result` TEXT COMMENT '执行结果(JSON格式)',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    INDEX `idx_task_code` (`task_code`),
    INDEX `idx_task_status` (`task_status`),
    INDEX `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采集任务表';

-- ============================================================
-- 任务数据表
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_task_data` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `task_id` BIGINT NOT NULL COMMENT '任务ID',
    `task_code` VARCHAR(100) NOT NULL COMMENT '任务编号',
    `data_code` VARCHAR(100) NOT NULL COMMENT '数据编号',
    `data_content` TEXT COMMENT '数据内容(JSON格式)',
    `data_source` VARCHAR(50) COMMENT '数据来源',
    `data_status` INT DEFAULT 0 COMMENT '数据状态: 0-未处理 1-处理中 2-已处理 3-处理失败',
    `upload_status` INT DEFAULT 0 COMMENT '上报状态: 0-未上报 1-上报中 2-上报成功 3-上报失败',
    `upload_time` DATETIME COMMENT '上报时间',
    `upload_response` TEXT COMMENT '上报响应',
    `retry_count` INT DEFAULT 0 COMMENT '重试次数',
    `error_msg` TEXT COMMENT '错误信息',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    INDEX `idx_task_data_task_id` (`task_id`),
    INDEX `idx_task_data_task_code` (`task_code`),
    INDEX `idx_task_data_data_code` (`data_code`),
    INDEX `idx_task_data_upload_status` (`upload_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务数据表';

-- ============================================================
-- 上报记录表
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_upload_record` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `task_id` BIGINT COMMENT '任务ID',
    `task_code` VARCHAR(100) COMMENT '任务编号',
    `task_data_id` BIGINT COMMENT '任务数据ID',
    `batch_no` VARCHAR(100) COMMENT '批次号',
    `upload_type` INT DEFAULT 1 COMMENT '上报类型: 1-单条上报 2-批量上报',
    `upload_status` INT DEFAULT 0 COMMENT '上报状态: 0-未上报 1-上报中 2-上报成功 3-上报失败',
    `upload_data` TEXT COMMENT '上报数据(JSON格式)',
    `request_url` VARCHAR(500) COMMENT '请求URL',
    `request_params` TEXT COMMENT '请求参数',
    `response_code` INT COMMENT '响应状态码',
    `response_body` TEXT COMMENT '响应内容',
    `cost_time` BIGINT COMMENT '耗时(毫秒)',
    `retry_count` INT DEFAULT 0 COMMENT '重试次数',
    `error_msg` TEXT COMMENT '错误信息',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    INDEX `idx_upload_record_task_id` (`task_id`),
    INDEX `idx_upload_record_batch_no` (`batch_no`),
    INDEX `idx_upload_record_upload_status` (`upload_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='上报记录表';
