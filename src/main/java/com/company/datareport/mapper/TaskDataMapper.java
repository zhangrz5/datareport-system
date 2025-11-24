package com.company.datareport.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.datareport.common.constants.DataSourceConstants;
import com.company.datareport.entity.TaskData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务数据 Mapper 接口
 *
 * @author qwe
 * @since 2025-01-24
 */
@Mapper
@DS(DataSourceConstants.MASTER)
public interface TaskDataMapper extends BaseMapper<TaskData> {

    /**
     * 根据任务ID查询任务数据列表
     *
     * @param taskId 任务ID
     * @return 任务数据列表
     */
    List<TaskData> selectByTaskId(@Param("taskId") Long taskId);

    /**
     * 查询待上报的数据
     *
     * @param limit 限制条数
     * @return 任务数据列表
     */
    List<TaskData> selectPendingUploadData(@Param("limit") Integer limit);

    /**
     * 批量更新上报状态
     *
     * @param ids 数据ID列表
     * @param status 状态
     * @return 影响行数
     */
    int batchUpdateUploadStatus(@Param("ids") List<Long> ids, @Param("status") Integer status);
}
