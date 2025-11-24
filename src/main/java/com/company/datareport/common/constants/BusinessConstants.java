package com.company.datareport.common.constants;

/**
 * 业务常量
 *
 * @author qwe
 * @since 2025-01-24
 */
public interface BusinessConstants {

    /**
     * 任务状态
     */
    interface TaskStatus {
        /** 待处理 */
        Integer PENDING = 0;
        /** 处理中 */
        Integer PROCESSING = 1;
        /** 已完成 */
        Integer COMPLETED = 2;
        /** 失败 */
        Integer FAILED = 3;
    }

    /**
     * 上报状态
     */
    interface UploadStatus {
        /** 未上报 */
        Integer NOT_UPLOADED = 0;
        /** 上报中 */
        Integer UPLOADING = 1;
        /** 上报成功 */
        Integer SUCCESS = 2;
        /** 上报失败 */
        Integer FAILED = 3;
    }

    /**
     * 数据来源
     */
    interface DataSource {
        /** 本地数据库 */
        String LOCAL_DB = "LOCAL_DB";
        /** 企业系统 */
        String ENTERPRISE_SYSTEM = "ENTERPRISE_SYSTEM";
        /** 外部接口 */
        String EXTERNAL_API = "EXTERNAL_API";
        /** Excel导入 */
        String EXCEL_IMPORT = "EXCEL_IMPORT";
    }

    /**
     * 任务类型
     */
    interface TaskType {
        /** 数据采集 */
        String DATA_COLLECT = "DATA_COLLECT";
        /** 数据上报 */
        String DATA_UPLOAD = "DATA_UPLOAD";
    }

    /**
     * Redis Key前缀
     */
    interface RedisKey {
        /** 任务锁前缀 */
        String TASK_LOCK_PREFIX = "task:lock:";
        /** 任务缓存前缀 */
        String TASK_CACHE_PREFIX = "task:cache:";
        /** 上报锁前缀 */
        String UPLOAD_LOCK_PREFIX = "upload:lock:";
    }
}
