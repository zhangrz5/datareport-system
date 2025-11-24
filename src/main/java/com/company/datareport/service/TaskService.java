package com.company.datareport.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.datareport.entity.Task;

import java.util.List;

/**
 * 任务服务接口
 *
 * @author qwe
 * @since 2025-01-24
 */
public interface TaskService extends IService<Task> {

    /**
     * 从平台获取任务列表
     *
     * @return 任务数量
     */
    int fetchTasksFromPlatform();

    /**
     * 查询待处理的任务列表
     *
     * @return 任务列表
     */
    List<Task> getPendingTasks();

    /**
     * 根据任务编号查询任务
     *
     * @param taskCode 任务编号
     * @return 任务信息
     */
    Task getByTaskCode(String taskCode);

    /**
     * 更新任务状态
     *
     * @param taskId 任务ID
     * @param status 状态
     * @return 是否成功
     */
    boolean updateTaskStatus(Long taskId, Integer status);

    /**
     * 执行任务
     *
     * @param taskId 任务ID
     */
    void executeTask(Long taskId);
}
