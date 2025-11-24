-- 数据采集上报系统数据库初始化脚本
-- 数据库: 达梦(DM) Database

-- 创建数据库
-- CREATE DATABASE datareport;

-- ============================================================
-- 任务表
-- ============================================================
CREATE TABLE t_task (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    task_code VARCHAR(100) NOT NULL,
    task_name VARCHAR(200) NOT NULL,
    task_type VARCHAR(50),
    data_source VARCHAR(50),
    task_desc TEXT,
    task_status INT DEFAULT 0,
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    deadline TIMESTAMP,
    collect_rule TEXT,
    collect_sql TEXT,
    target_datasource VARCHAR(50),
    retry_count INT DEFAULT 0,
    error_msg TEXT,
    execute_result TEXT,
    remark VARCHAR(500),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(50) DEFAULT 'system',
    update_by VARCHAR(50) DEFAULT 'system',
    deleted INT DEFAULT 0
);

-- 创建索引
CREATE INDEX idx_task_code ON t_task(task_code);
CREATE INDEX idx_task_status ON t_task(task_status);
CREATE INDEX idx_create_time ON t_task(create_time);

-- 添加表注释
COMMENT ON TABLE t_task IS '采集任务表';

-- 添加列注释
COMMENT ON COLUMN t_task.id IS '主键ID';
COMMENT ON COLUMN t_task.task_code IS '任务编号';
COMMENT ON COLUMN t_task.task_name IS '任务名称';
COMMENT ON COLUMN t_task.task_type IS '任务类型';
COMMENT ON COLUMN t_task.data_source IS '数据来源';
COMMENT ON COLUMN t_task.task_desc IS '任务描述';
COMMENT ON COLUMN t_task.task_status IS '任务状态: 0-待处理 1-处理中 2-已完成 3-失败';
COMMENT ON COLUMN t_task.start_time IS '开始时间';
COMMENT ON COLUMN t_task.end_time IS '结束时间';
COMMENT ON COLUMN t_task.deadline IS '截止时间';
COMMENT ON COLUMN t_task.collect_rule IS '采集规则(JSON格式)';
COMMENT ON COLUMN t_task.collect_sql IS '采集SQL';
COMMENT ON COLUMN t_task.target_datasource IS '目标数据源';
COMMENT ON COLUMN t_task.retry_count IS '重试次数';
COMMENT ON COLUMN t_task.error_msg IS '错误信息';
COMMENT ON COLUMN t_task.execute_result IS '执行结果(JSON格式)';
COMMENT ON COLUMN t_task.remark IS '备注';
COMMENT ON COLUMN t_task.create_time IS '创建时间';
COMMENT ON COLUMN t_task.update_time IS '更新时间';
COMMENT ON COLUMN t_task.create_by IS '创建人';
COMMENT ON COLUMN t_task.update_by IS '更新人';
COMMENT ON COLUMN t_task.deleted IS '逻辑删除: 0-未删除 1-已删除';

-- ============================================================
-- 任务数据表
-- ============================================================
CREATE TABLE t_task_data (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    task_id BIGINT NOT NULL,
    task_code VARCHAR(100) NOT NULL,
    data_code VARCHAR(100) NOT NULL,
    data_content TEXT,
    data_source VARCHAR(50),
    data_status INT DEFAULT 0,
    upload_status INT DEFAULT 0,
    upload_time TIMESTAMP,
    upload_response TEXT,
    retry_count INT DEFAULT 0,
    error_msg TEXT,
    remark VARCHAR(500),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(50) DEFAULT 'system',
    update_by VARCHAR(50) DEFAULT 'system',
    deleted INT DEFAULT 0
);

-- 创建索引
CREATE INDEX idx_task_data_task_id ON t_task_data(task_id);
CREATE INDEX idx_task_data_task_code ON t_task_data(task_code);
CREATE INDEX idx_task_data_data_code ON t_task_data(data_code);
CREATE INDEX idx_task_data_upload_status ON t_task_data(upload_status);

-- 添加表注释
COMMENT ON TABLE t_task_data IS '任务数据表';

-- 添加列注释
COMMENT ON COLUMN t_task_data.id IS '主键ID';
COMMENT ON COLUMN t_task_data.task_id IS '任务ID';
COMMENT ON COLUMN t_task_data.task_code IS '任务编号';
COMMENT ON COLUMN t_task_data.data_code IS '数据编号';
COMMENT ON COLUMN t_task_data.data_content IS '数据内容(JSON格式)';
COMMENT ON COLUMN t_task_data.data_source IS '数据来源';
COMMENT ON COLUMN t_task_data.data_status IS '数据状态: 0-未处理 1-处理中 2-已处理 3-处理失败';
COMMENT ON COLUMN t_task_data.upload_status IS '上报状态: 0-未上报 1-上报中 2-上报成功 3-上报失败';
COMMENT ON COLUMN t_task_data.upload_time IS '上报时间';
COMMENT ON COLUMN t_task_data.upload_response IS '上报响应';
COMMENT ON COLUMN t_task_data.retry_count IS '重试次数';
COMMENT ON COLUMN t_task_data.error_msg IS '错误信息';
COMMENT ON COLUMN t_task_data.remark IS '备注';
COMMENT ON COLUMN t_task_data.create_time IS '创建时间';
COMMENT ON COLUMN t_task_data.update_time IS '更新时间';
COMMENT ON COLUMN t_task_data.create_by IS '创建人';
COMMENT ON COLUMN t_task_data.update_by IS '更新人';
COMMENT ON COLUMN t_task_data.deleted IS '逻辑删除: 0-未删除 1-已删除';

-- ============================================================
-- 上报记录表
-- ============================================================
CREATE TABLE t_upload_record (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    task_id BIGINT,
    task_code VARCHAR(100),
    task_data_id BIGINT,
    batch_no VARCHAR(100),
    upload_type INT DEFAULT 1,
    upload_status INT DEFAULT 0,
    upload_data TEXT,
    request_url VARCHAR(500),
    request_params TEXT,
    response_code INT,
    response_body TEXT,
    cost_time BIGINT,
    retry_count INT DEFAULT 0,
    error_msg TEXT,
    remark VARCHAR(500),
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(50) DEFAULT 'system',
    update_by VARCHAR(50) DEFAULT 'system',
    deleted INT DEFAULT 0
);

-- 创建索引
CREATE INDEX idx_upload_record_task_id ON t_upload_record(task_id);
CREATE INDEX idx_upload_record_batch_no ON t_upload_record(batch_no);
CREATE INDEX idx_upload_record_upload_status ON t_upload_record(upload_status);

-- 添加表注释
COMMENT ON TABLE t_upload_record IS '上报记录表';

-- 添加列注释
COMMENT ON COLUMN t_upload_record.id IS '主键ID';
COMMENT ON COLUMN t_upload_record.task_id IS '任务ID';
COMMENT ON COLUMN t_upload_record.task_code IS '任务编号';
COMMENT ON COLUMN t_upload_record.task_data_id IS '任务数据ID';
COMMENT ON COLUMN t_upload_record.batch_no IS '批次号';
COMMENT ON COLUMN t_upload_record.upload_type IS '上报类型: 1-单条上报 2-批量上报';
COMMENT ON COLUMN t_upload_record.upload_status IS '上报状态: 0-未上报 1-上报中 2-上报成功 3-上报失败';
COMMENT ON COLUMN t_upload_record.upload_data IS '上报数据(JSON格式)';
COMMENT ON COLUMN t_upload_record.request_url IS '请求URL';
COMMENT ON COLUMN t_upload_record.request_params IS '请求参数';
COMMENT ON COLUMN t_upload_record.response_code IS '响应状态码';
COMMENT ON COLUMN t_upload_record.response_body IS '响应内容';
COMMENT ON COLUMN t_upload_record.cost_time IS '耗时(毫秒)';
COMMENT ON COLUMN t_upload_record.retry_count IS '重试次数';
COMMENT ON COLUMN t_upload_record.error_msg IS '错误信息';
COMMENT ON COLUMN t_upload_record.remark IS '备注';
COMMENT ON COLUMN t_upload_record.create_time IS '创建时间';
COMMENT ON COLUMN t_upload_record.update_time IS '更新时间';
COMMENT ON COLUMN t_upload_record.create_by IS '创建人';
COMMENT ON COLUMN t_upload_record.update_by IS '更新人';
COMMENT ON COLUMN t_upload_record.deleted IS '逻辑删除: 0-未删除 1-已删除';
