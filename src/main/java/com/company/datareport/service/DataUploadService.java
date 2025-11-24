package com.company.datareport.service;

import com.company.datareport.entity.TaskData;

import java.util.List;

/**
 * 数据上报服务接口
 *
 * @author qwe
 * @since 2025-01-24
 */
public interface DataUploadService {

    /**
     * 上报单条数据
     *
     * @param taskData 任务数据
     * @return 是否成功
     */
    boolean uploadSingleData(TaskData taskData);

    /**
     * 批量上报数据
     *
     * @param taskDataList 任务数据列表
     * @return 上报成功的数量
     */
    int uploadBatchData(List<TaskData> taskDataList);

    /**
     * 执行定时上报任务
     */
    void executeScheduledUpload();

    /**
     * 重试上报失败的数据
     *
     * @return 重试成功的数量
     */
    int retryFailedUploads();
}
