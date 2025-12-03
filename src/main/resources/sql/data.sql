-- 同步记录表（每次同步插入一条）
CREATE TABLE sync_record (
                             id              INT IDENTITY(1,1) PRIMARY KEY,
                             table_name      VARCHAR(100) NOT NULL,
                             sync_time       TIMESTAMP NOT NULL,
                             sync_count      INT DEFAULT 0,
                             sync_status     TINYINT DEFAULT 1,
                             create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             remark          VARCHAR(255)
);

-- 创建索引（按表名+时间查询最新记录）
CREATE INDEX idx_sync_record ON sync_record(table_name, sync_time DESC);

-- 注释
COMMENT ON TABLE sync_record IS '增量同步记录表';
COMMENT ON COLUMN sync_record.table_name IS '表名';
COMMENT ON COLUMN sync_record.sync_time IS '本次同步截止时间';
COMMENT ON COLUMN sync_record.sync_count IS '本次同步数据量';
COMMENT ON COLUMN sync_record.sync_status IS '状态: 0-失败 1-成功';

-- 初始化5张表的基准记录
INSERT INTO sync_record (table_name, sync_time, remark) VALUES ('SASAORGANIZATIONS', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '初始化');
INSERT INTO sync_record (table_name, sync_time, remark) VALUES ('SASAOWNERSHIP', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '初始化');
INSERT INTO sync_record (table_name, sync_time, remark) VALUES ('SASAEQUITYPART', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '初始化');
INSERT INTO sync_record (table_name, sync_time, remark) VALUES ('SASAPERSONNEL', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '初始化');
INSERT INTO sync_record (table_name, sync_time, remark) VALUES ('SASALAYERSTR', TO_TIMESTAMP('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '初始化');