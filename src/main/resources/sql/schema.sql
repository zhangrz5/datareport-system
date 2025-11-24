-- 数据采集上报系统数据库初始化脚本
-- 数据库: PostgreSQL

-- 创建数据库
-- CREATE DATABASE datareport;

-- ============================================================
-- 任务表
-- ============================================================
CREATE TABLE IF NOT EXISTS t_task (
    id BIGSERIAL PRIMARY KEY,
    task_code VARCHAR(100) NOT NULL COMMENT '任务编号',
    task_name VARCHAR(200) NOT NULL COMMENT '任务名称',
    task_type VARCHAR(50) COMMENT '任务类型',
    data_source VARCHAR(50) COMMENT '数据来源',
    task_desc TEXT COMMENT '任务描述',
    task_status INTEGER DEFAULT 0 COMMENT '任务状态: 0-待处理 1-处理中 2-已完成 3-失败',
    start_time TIMESTAMP COMMENT '开始时间',
    end_time TIMESTAMP COMMENT '结束时间',
    deadline TIMESTAMP COMMENT '截止时间',
    collect_rule TEXT COMMENT '采集规则(JSON格式)',
    collect_sql TEXT COMMENT '采集SQL',
    target_datasource VARCHAR(50) COMMENT '目标数据源',
    retry_count INTEGER DEFAULT 0 COMMENT '重试次数',
    error_msg TEXT COMMENT '错误信息',
    execute_result TEXT COMMENT '执行结果(JSON格式)',
    remark VARCHAR(500) COMMENT '备注',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_by VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    update_by VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    deleted INTEGER DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除'
);

-- 创建索引
CREATE INDEX idx_task_code ON t_task(task_code);
CREATE INDEX idx_task_status ON t_task(task_status);
CREATE INDEX idx_create_time ON t_task(create_time);

-- 添加注释
COMMENT ON TABLE t_task IS '采集任务表';
COMMENT ON COLUMN t_task.id IS '主键ID';
COMMENT ON COLUMN t_task.task_code IS '任务编号';
COMMENT ON COLUMN t_task.task_name IS '任务名称';
COMMENT ON COLUMN t_task.task_status IS '任务状态: 0-待处理 1-处理中 2-已完成 3-失败';

-- ============================================================
-- 任务数据表
-- ============================================================
CREATE TABLE IF NOT EXISTS t_task_data (
    id BIGSERIAL PRIMARY KEY,
    task_id BIGINT NOT NULL COMMENT '任务ID',
    task_code VARCHAR(100) NOT NULL COMMENT '任务编号',
    data_code VARCHAR(100) NOT NULL COMMENT '数据编号',
    data_content TEXT COMMENT '数据内容(JSON格式)',
    data_source VARCHAR(50) COMMENT '数据来源',
    data_status INTEGER DEFAULT 0 COMMENT '数据状态: 0-未处理 1-处理中 2-已处理 3-处理失败',
    upload_status INTEGER DEFAULT 0 COMMENT '上报状态: 0-未上报 1-上报中 2-上报成功 3-上报失败',
    upload_time TIMESTAMP COMMENT '上报时间',
    upload_response TEXT COMMENT '上报响应',
    retry_count INTEGER DEFAULT 0 COMMENT '重试次数',
    error_msg TEXT COMMENT '错误信息',
    remark VARCHAR(500) COMMENT '备注',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_by VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    update_by VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    deleted INTEGER DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除'
);

-- 创建索引
CREATE INDEX idx_task_data_task_id ON t_task_data(task_id);
CREATE INDEX idx_task_data_task_code ON t_task_data(task_code);
CREATE INDEX idx_task_data_data_code ON t_task_data(data_code);
CREATE INDEX idx_task_data_upload_status ON t_task_data(upload_status);

-- 添加注释
COMMENT ON TABLE t_task_data IS '任务数据表';
COMMENT ON COLUMN t_task_data.id IS '主键ID';
COMMENT ON COLUMN t_task_data.task_id IS '任务ID';
COMMENT ON COLUMN t_task_data.upload_status IS '上报状态: 0-未上报 1-上报中 2-上报成功 3-上报失败';

-- ============================================================
-- 上报记录表
-- ============================================================
CREATE TABLE IF NOT EXISTS t_upload_record (
    id BIGSERIAL PRIMARY KEY,
    task_id BIGINT COMMENT '任务ID',
    task_code VARCHAR(100) COMMENT '任务编号',
    task_data_id BIGINT COMMENT '任务数据ID',
    batch_no VARCHAR(100) COMMENT '批次号',
    upload_type INTEGER DEFAULT 1 COMMENT '上报类型: 1-单条上报 2-批量上报',
    upload_status INTEGER DEFAULT 0 COMMENT '上报状态: 0-未上报 1-上报中 2-上报成功 3-上报失败',
    upload_data TEXT COMMENT '上报数据(JSON格式)',
    request_url VARCHAR(500) COMMENT '请求URL',
    request_params TEXT COMMENT '请求参数',
    response_code INTEGER COMMENT '响应状态码',
    response_body TEXT COMMENT '响应内容',
    cost_time BIGINT COMMENT '耗时(毫秒)',
    retry_count INTEGER DEFAULT 0 COMMENT '重试次数',
    error_msg TEXT COMMENT '错误信息',
    remark VARCHAR(500) COMMENT '备注',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    create_by VARCHAR(50) DEFAULT 'system' COMMENT '创建人',
    update_by VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
    deleted INTEGER DEFAULT 0 COMMENT '逻辑删除: 0-未删除 1-已删除'
);

-- 创建索引
CREATE INDEX idx_upload_record_task_id ON t_upload_record(task_id);
CREATE INDEX idx_upload_record_batch_no ON t_upload_record(batch_no);
CREATE INDEX idx_upload_record_upload_status ON t_upload_record(upload_status);

-- 添加注释
COMMENT ON TABLE t_upload_record IS '上报记录表';
COMMENT ON COLUMN t_upload_record.id IS '主键ID';
COMMENT ON COLUMN t_upload_record.upload_status IS '上报状态: 0-未上报 1-上报中 2-上报成功 3-上报失败';
