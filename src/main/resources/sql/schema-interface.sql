-- ============================================================
-- 国资委数据采集交换平台接口相关表
-- ============================================================

-- ============================================================
-- 统一操作日志表 (所有接口调用的总日志)
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_interface_log` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `log_id` VARCHAR(100) NOT NULL COMMENT '日志ID(UUID)',
    `interface_type` VARCHAR(50) NOT NULL COMMENT '接口类型: FILE_UPLOAD-数据报送, KEY_DOWNLOAD-密钥下载, TEMP_DOWNLOAD-采集目录下载, TASK_DOWNLOAD-任务下载, NOTICE_DOWNLOAD-通知下载, LOG_DOWNLOAD-日志下载',
    `interface_url` VARCHAR(500) COMMENT '接口地址',
    `request_method` VARCHAR(10) COMMENT '请求方法: GET/POST',
    `request_params` TEXT COMMENT '请求参数(JSON)',
    `request_time` DATETIME COMMENT '请求时间',
    `response_time` DATETIME COMMENT '响应时间',
    `cost_time` BIGINT COMMENT '耗时(毫秒)',
    `response_code` INT COMMENT '响应状态码',
    `service_flag` VARCHAR(10) COMMENT '服务标识: 0-失败, 1-成功, 2-无权限, 3-无新文件',
    `response_msg` TEXT COMMENT '响应消息',
    `response_body` LONGTEXT COMMENT '响应内容',
    `file_name` VARCHAR(500) COMMENT '文件名称',
    `file_size` BIGINT COMMENT '文件大小(字节)',
    `file_path` VARCHAR(1000) COMMENT '文件保存路径',
    `business_type` VARCHAR(50) COMMENT '业务类型',
    `social_credit_code` VARCHAR(50) COMMENT '社会信用代码',
    `status` INT DEFAULT 0 COMMENT '处理状态: 0-未处理, 1-处理中, 2-处理成功, 3-处理失败',
    `error_msg` TEXT COMMENT '错误信息',
    `retry_count` INT DEFAULT 0 COMMENT '重试次数',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    INDEX `idx_log_id` (`log_id`),
    INDEX `idx_interface_type` (`interface_type`),
    INDEX `idx_request_time` (`request_time`),
    INDEX `idx_status` (`status`),
    INDEX `idx_business_type` (`business_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='接口调用统一日志表';

-- ============================================================
-- 数据报送记录表 (接口1: fileUpload)
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_file_upload_record` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `log_id` VARCHAR(100) COMMENT '关联日志ID',
    `api_code` VARCHAR(50) COMMENT 'API编码',
    `business_type` VARCHAR(50) COMMENT '业务类型',
    `file_name` VARCHAR(500) NOT NULL COMMENT '文件名称',
    `file_size` BIGINT COMMENT '文件大小(字节)',
    `file_path` VARCHAR(1000) COMMENT '文件保存路径',
    `file_md5` VARCHAR(100) COMMENT '文件MD5值',
    `social_credit_code` VARCHAR(50) COMMENT '企业社会信用代码',
    `upload_status` INT DEFAULT 0 COMMENT '上传状态: 0-待处理, 1-处理中, 2-成功, 3-失败',
    `service_flag` VARCHAR(10) COMMENT '服务标识: 0-失败, 1-成功, 2-无权限',
    `response_msg` TEXT COMMENT '响应消息',
    `parse_status` INT DEFAULT 0 COMMENT '解析状态: 0-未解析, 1-解析中, 2-解析成功, 3-解析失败',
    `parse_result` TEXT COMMENT '解析结果(JSON)',
    `error_msg` TEXT COMMENT '错误信息',
    `retry_count` INT DEFAULT 0 COMMENT '重试次数',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    INDEX `idx_log_id` (`log_id`),
    INDEX `idx_business_type` (`business_type`),
    INDEX `idx_upload_status` (`upload_status`),
    INDEX `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据报送记录表';

-- ============================================================
-- 密钥证书表 (接口2: keyDownload)
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_key_certificate` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `log_id` VARCHAR(100) COMMENT '关联日志ID',
    `key_id` VARCHAR(100) NOT NULL COMMENT '密钥主键ID',
    `business_type` VARCHAR(50) COMMENT '业务类型',
    `key_version` VARCHAR(50) COMMENT '密钥版本号',
    `sm2_public_key` TEXT COMMENT 'SM2公钥(加密存储)',
    `sm4_key` TEXT COMMENT 'SM4密钥(加密存储)',
    `file_name` VARCHAR(500) COMMENT '密钥文件名',
    `file_path` VARCHAR(1000) COMMENT '文件保存路径',
    `download_time` DATETIME COMMENT '下载时间',
    `status` INT DEFAULT 0 COMMENT '状态: 0-未启用, 1-已启用, 2-已废弃',
    `active_time` DATETIME COMMENT '启用时间',
    `expire_time` DATETIME COMMENT '过期时间',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    UNIQUE INDEX `uk_key_id` (`key_id`),
    INDEX `idx_business_type` (`business_type`),
    INDEX `idx_key_version` (`key_version`),
    INDEX `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='密钥证书表';

-- ============================================================
-- 业务类型表 (接口3: tempDownload - 业务类型)
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_business_type` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `business_id` VARCHAR(100) NOT NULL COMMENT '业务主键ID',
    `business_code` VARCHAR(50) NOT NULL COMMENT '业务编码',
    `business_name` VARCHAR(200) COMMENT '业务名称',
    `business_desc` TEXT COMMENT '业务描述',
    `status` INT DEFAULT 1 COMMENT '状态: 0-禁用, 1-启用',
    `sort_order` INT DEFAULT 0 COMMENT '排序',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    UNIQUE INDEX `uk_business_id` (`business_id`),
    INDEX `idx_business_code` (`business_code`),
    INDEX `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务类型表';

-- ============================================================
-- 采集模板信息表 (接口3: tempDownload - 模板信息)
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_template_info` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `template_id` VARCHAR(100) NOT NULL COMMENT '模板主键ID',
    `template_code` VARCHAR(50) NOT NULL COMMENT '模板编码',
    `template_name` VARCHAR(200) COMMENT '模板名称',
    `template_version` VARCHAR(50) COMMENT '模板版本号',
    `business_id` VARCHAR(100) COMMENT '业务外键ID',
    `business_code` VARCHAR(50) COMMENT '业务编码',
    `template_desc` TEXT COMMENT '模板描述',
    `file_name` VARCHAR(500) COMMENT '模板文件名',
    `file_path` VARCHAR(1000) COMMENT '模板文件路径',
    `example_file_name` VARCHAR(500) COMMENT '示例文件名',
    `example_file_path` VARCHAR(1000) COMMENT '示例文件路径',
    `download_time` DATETIME COMMENT '下载时间',
    `status` INT DEFAULT 0 COMMENT '状态: 0-未启用, 1-已启用, 2-已废弃',
    `active_time` DATETIME COMMENT '启用时间',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    UNIQUE INDEX `uk_template_id` (`template_id`),
    INDEX `idx_template_code` (`template_code`),
    INDEX `idx_business_id` (`business_id`),
    INDEX `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采集模板信息表';

-- ============================================================
-- 模板采集频率表 (接口3: tempDownload - 采集频率)
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_template_frequency` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `frequency_id` VARCHAR(100) NOT NULL COMMENT '频率主键ID',
    `template_id` VARCHAR(100) NOT NULL COMMENT '模板外键ID',
    `field_code` VARCHAR(100) COMMENT '字段编码',
    `field_name` VARCHAR(200) COMMENT '字段名称',
    `collect_frequency` VARCHAR(50) COMMENT '采集频率: DAILY-每日, WEEKLY-每周, MONTHLY-每月, QUARTERLY-每季度, YEARLY-每年',
    `collect_time` VARCHAR(50) COMMENT '采集时间点',
    `is_required` INT DEFAULT 0 COMMENT '是否必填: 0-否, 1-是',
    `data_type` VARCHAR(50) COMMENT '数据类型',
    `data_format` VARCHAR(100) COMMENT '数据格式',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    UNIQUE INDEX `uk_frequency_id` (`frequency_id`),
    INDEX `idx_template_id` (`template_id`),
    INDEX `idx_field_code` (`field_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模板采集频率表';

-- ============================================================
-- 补传任务表 (接口4: taskDownload)
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_supplement_task` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `log_id` VARCHAR(100) COMMENT '关联日志ID',
    `task_id` VARCHAR(100) NOT NULL COMMENT '任务主键ID',
    `task_code` VARCHAR(50) NOT NULL COMMENT '任务编码',
    `task_name` VARCHAR(200) COMMENT '任务名称',
    `business_type` VARCHAR(50) COMMENT '业务类型',
    `template_id` VARCHAR(100) COMMENT '模板ID',
    `template_code` VARCHAR(50) COMMENT '模板编码',
    `start_date` DATE COMMENT '补传开始日期',
    `end_date` DATE COMMENT '补传结束日期',
    `deadline` DATETIME COMMENT '截止时间',
    `task_desc` TEXT COMMENT '任务描述',
    `file_name` VARCHAR(500) COMMENT '任务文件名',
    `file_path` VARCHAR(1000) COMMENT '任务文件路径',
    `download_time` DATETIME COMMENT '下载时间',
    `task_status` INT DEFAULT 0 COMMENT '任务状态: 0-待执行, 1-执行中, 2-已完成, 3-已取消, 4-已过期',
    `complete_time` DATETIME COMMENT '完成时间',
    `complete_rate` DECIMAL(5,2) COMMENT '完成率(%)',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    UNIQUE INDEX `uk_task_id` (`task_id`),
    INDEX `idx_task_code` (`task_code`),
    INDEX `idx_business_type` (`business_type`),
    INDEX `idx_task_status` (`task_status`),
    INDEX `idx_deadline` (`deadline`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='补传任务表';

-- ============================================================
-- 通知公告表 (接口5: noticeDownload)
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_notice_announcement` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `log_id` VARCHAR(100) COMMENT '关联日志ID',
    `notice_id` VARCHAR(100) NOT NULL COMMENT '通知主键ID',
    `notice_code` VARCHAR(50) COMMENT '通知编码',
    `notice_title` VARCHAR(500) COMMENT '通知标题',
    `notice_type` VARCHAR(50) COMMENT '通知类型: 0007-异常反馈通知, 0009-普通通知',
    `notice_content` LONGTEXT COMMENT '通知内容',
    `permission_code` VARCHAR(50) COMMENT '权限编码',
    `file_name` VARCHAR(500) COMMENT '通知文件名',
    `file_path` VARCHAR(1000) COMMENT '通知文件路径',
    `attachment_count` INT DEFAULT 0 COMMENT '附件数量',
    `attachment_info` TEXT COMMENT '附件信息(JSON)',
    `download_time` DATETIME COMMENT '下载时间',
    `publish_time` DATETIME COMMENT '发布时间',
    `read_status` INT DEFAULT 0 COMMENT '阅读状态: 0-未读, 1-已读',
    `read_time` DATETIME COMMENT '阅读时间',
    `importance` INT DEFAULT 0 COMMENT '重要程度: 0-普通, 1-重要, 2-紧急',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    UNIQUE INDEX `uk_notice_id` (`notice_id`),
    INDEX `idx_notice_type` (`notice_type`),
    INDEX `idx_read_status` (`read_status`),
    INDEX `idx_publish_time` (`publish_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知公告表';

-- ============================================================
-- 远程日志同步表 (接口6: logDownload)
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_remote_log_sync` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `el_id` VARCHAR(100) NOT NULL COMMENT '远程日志主键ID',
    `business_code` VARCHAR(50) COMMENT '业务编码',
    `file_name` VARCHAR(500) COMMENT '报送文件名称',
    `push_date` DATETIME COMMENT '推送时间',
    `grab_date` DATETIME COMMENT '抓取时间',
    `repair_mark` VARCHAR(100) COMMENT '补录标识: 1-正常上传, 0-主动补录, 其他-被动补录任务ID',
    `el_createdate` DATETIME COMMENT '日志生成时间',
    `sync_time` DATETIME COMMENT '同步时间',
    `module_code` VARCHAR(50) COMMENT '模块编码: DATAPUSHLOG-报送文件日志, TEMP-采集目录日志',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    UNIQUE INDEX `uk_el_id` (`el_id`),
    INDEX `idx_business_code` (`business_code`),
    INDEX `idx_push_date` (`push_date`),
    INDEX `idx_module_code` (`module_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='远程日志同步表';

-- ============================================================
-- 接口配置表
-- ============================================================
CREATE TABLE IF NOT EXISTS `t_interface_config` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `config_key` VARCHAR(100) NOT NULL COMMENT '配置键',
    `config_value` VARCHAR(1000) COMMENT '配置值',
    `config_desc` VARCHAR(500) COMMENT '配置描述',
    `config_group` VARCHAR(50) COMMENT '配置分组',
    `is_encrypted` INT DEFAULT 0 COMMENT '是否加密: 0-否, 1-是',
    `status` INT DEFAULT 1 COMMENT '状态: 0-禁用, 1-启用',
    `remark` VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    `deleted` INT DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除',
    UNIQUE INDEX `uk_config_key` (`config_key`),
    INDEX `idx_config_group` (`config_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='接口配置表';

-- ============================================================
-- 初始化接口配置数据
-- ============================================================
INSERT INTO `t_interface_config` (`config_key`, `config_value`, `config_desc`, `config_group`) VALUES
('preposed.server.host', 'http://localhost:8080', '前置服务器地址', 'SERVER'),
('preposed.server.port', '8080', '前置服务器端口', 'SERVER'),
('preposed.api.username', 'admin', '接口调用用户名', 'AUTH'),
('preposed.api.password', 'admin123', '接口调用密码', 'AUTH'),
('preposed.social.credit.code', '91110000000000000X', '企业社会信用代码', 'ENTERPRISE'),
('preposed.ah.status', '0', '安徽模式状态: 0-禁用, 1-启用', 'MODE'),
('preposed.file.upload.path', '/data/upload', '文件上传路径', 'FILE'),
('preposed.file.download.path', '/data/download', '文件下载路径', 'FILE'),
('preposed.retry.max.count', '3', '最大重试次数', 'RETRY'),
('preposed.retry.interval', '60000', '重试间隔(毫秒)', 'RETRY');
