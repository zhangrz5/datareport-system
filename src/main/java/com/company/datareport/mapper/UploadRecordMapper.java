package com.company.datareport.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.datareport.common.constants.DataSourceConstants;
import com.company.datareport.entity.UploadRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 上报记录 Mapper 接口
 *
 * @author qwe
 * @since 2025-01-24
 */
@Mapper
@DS(DataSourceConstants.MASTER)
public interface UploadRecordMapper extends BaseMapper<UploadRecord> {

    /**
     * 根据任务ID查询上报记录
     *
     * @param taskId 任务ID
     * @return 上报记录列表
     */
    List<UploadRecord> selectByTaskId(@Param("taskId") Long taskId);

    /**
     * 根据批次号查询上报记录
     *
     * @param batchNo 批次号
     * @return 上报记录列表
     */
    List<UploadRecord> selectByBatchNo(@Param("batchNo") String batchNo);
}
