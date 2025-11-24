package com.company.datareport.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.datareport.common.result.Result;
import com.company.datareport.entity.TaskData;
import com.company.datareport.mapper.TaskDataMapper;
import com.company.datareport.service.DataUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据控制器
 *
 * @author qwe
 * @since 2025-01-24
 */
@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private TaskDataMapper taskDataMapper;

    @Autowired
    private DataUploadService dataUploadService;

    /**
     * 查询任务数据列表(分页)
     */
    @GetMapping("/list")
    public Result<Page<TaskData>> list(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Long taskId,
            @RequestParam(required = false) Integer uploadStatus) {
        
        Page<TaskData> page = new Page<>(current, size);
        QueryWrapper<TaskData> wrapper = new QueryWrapper<>();
        
        if (taskId != null) {
            wrapper.eq("task_id", taskId);
        }
        if (uploadStatus != null) {
            wrapper.eq("upload_status", uploadStatus);
        }
        
        wrapper.orderByDesc("create_time");
        
        Page<TaskData> result = taskDataMapper.selectPage(page, wrapper);
        return Result.success(result);
    }

    /**
     * 查询任务数据详情
     */
    @GetMapping("/{id}")
    public Result<TaskData> getById(@PathVariable Long id) {
        TaskData taskData = taskDataMapper.selectById(id);
        if (taskData == null) {
            return Result.error("数据不存在");
        }
        return Result.success(taskData);
    }

    /**
     * 根据任务ID查询数据列表
     */
    @GetMapping("/task/{taskId}")
    public Result<List<TaskData>> getByTaskId(@PathVariable Long taskId) {
        List<TaskData> dataList = taskDataMapper.selectByTaskId(taskId);
        return Result.success(dataList);
    }

    /**
     * 查询待上报的数据
     */
    @GetMapping("/pending")
    public Result<List<TaskData>> getPendingUploadData(
            @RequestParam(defaultValue = "100") Integer limit) {
        
        List<TaskData> dataList = taskDataMapper.selectPendingUploadData(limit);
        return Result.success(dataList);
    }

    /**
     * 手动上报单条数据
     */
    @PostMapping("/upload/{id}")
    public Result<Void> uploadSingle(@PathVariable Long id) {
        log.info("手动上报单条数据, id: {}", id);
        
        TaskData taskData = taskDataMapper.selectById(id);
        if (taskData == null) {
            return Result.error("数据不存在");
        }
        
        boolean success = dataUploadService.uploadSingleData(taskData);
        if (success) {
            return Result.success("数据上报成功");
        } else {
            return Result.error("数据上报失败");
        }
    }

    /**
     * 手动批量上报数据
     */
    @PostMapping("/upload/batch")
    public Result<Integer> uploadBatch(@RequestBody List<Long> ids) {
        log.info("手动批量上报数据, 数量: {}", ids.size());
        
        List<TaskData> taskDataList = taskDataMapper.selectBatchIds(ids);
        int successCount = dataUploadService.uploadBatchData(taskDataList);
        
        return Result.success("批量上报完成,成功: " + successCount + "/" + ids.size(), successCount);
    }

    /**
     * 触发定时上报
     */
    @PostMapping("/upload/scheduled")
    public Result<Void> uploadScheduled() {
        log.info("手动触发定时上报");
        dataUploadService.executeScheduledUpload();
        return Result.success("定时上报执行完成");
    }

    /**
     * 重试失败的上报
     */
    @PostMapping("/upload/retry")
    public Result<Integer> retryFailedUploads() {
        log.info("手动重试失败的上报");
        int count = dataUploadService.retryFailedUploads();
        return Result.success("重试完成,成功数: " + count, count);
    }
}
