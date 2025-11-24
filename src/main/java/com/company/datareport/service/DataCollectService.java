package com.company.datareport.service;

import com.company.datareport.entity.Task;
import com.company.datareport.entity.TaskData;

import java.util.List;

/**
 * 数据采集服务接口
 *
 * @author qwe
 * @since 2025-01-24
 */
public interface DataCollectService {

    /**
     * 采集任务数据
     *
     * @param task 任务信息
     * @return 采集的数据列表
     */
    List<TaskData> collectData(Task task);

    /**
     * 从数据库采集数据
     *
     * @param task 任务信息
     * @param datasource 数据源标识
     * @return 采集的数据列表
     */
    List<TaskData> collectFromDatabase(Task task, String datasource);

    /**
     * 从外部接口采集数据
     *
     * @param task 任务信息
     * @return 采集的数据列表
     */
    List<TaskData> collectFromApi(Task task);

    /**
     * 从Excel文件采集数据
     *
     * @param task 任务信息
     * @param filePath 文件路径
     * @return 采集的数据列表
     */
    List<TaskData> collectFromExcel(Task task, String filePath);
}
