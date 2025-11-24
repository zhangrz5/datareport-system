package com.company.datareport.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.datareport.common.constants.DataSourceConstants;
import com.company.datareport.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务 Mapper 接口
 *
 * @author qwe
 * @since 2025-01-24
 */
@Mapper
@DS(DataSourceConstants.MASTER)
public interface TaskMapper extends BaseMapper<Task> {

    /**
     * 查询待处理的任务列表
     *
     * @return 任务列表
     */
    List<Task> selectPendingTasks();

    /**
     * 根据任务编号查询任务
     *
     * @param taskCode 任务编号
     * @return 任务信息
     */
    Task selectByTaskCode(@Param("taskCode") String taskCode);

    /**
     * 更新任务状态
     *
     * @param taskId 任务ID
     * @param status 状态
     * @return 影响行数
     */
    int updateTaskStatus(@Param("taskId") Long taskId, @Param("status") Integer status);
}
