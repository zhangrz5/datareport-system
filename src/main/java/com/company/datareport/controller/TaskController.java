package com.company.datareport.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.datareport.common.result.Result;
import com.company.datareport.entity.Task;
import com.company.datareport.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 任务控制器
 *
 * @author qwe
 * @since 2025-01-24
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 手动拉取任务
     */
    @PostMapping("/fetch")
    public Result<Integer> fetchTasks() {
        log.info("手动拉取任务");
        int count = taskService.fetchTasksFromPlatform();
        return Result.success("拉取任务成功", count);
    }

    /**
     * 查询任务列表(分页)
     */
    @GetMapping("/list")
    public Result<Page<Task>> list(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String taskCode,
            @RequestParam(required = false) Integer taskStatus) {
        
        Page<Task> page = new Page<>(current, size);
        QueryWrapper<Task> wrapper = new QueryWrapper<>();
        
        if (taskCode != null && !taskCode.isEmpty()) {
            wrapper.like("task_code", taskCode);
        }
        if (taskStatus != null) {
            wrapper.eq("task_status", taskStatus);
        }
        
        wrapper.orderByDesc("create_time");
        
        Page<Task> result = taskService.page(page, wrapper);
        return Result.success(result);
    }

    /**
     * 查询任务详情
     */
    @GetMapping("/{id}")
    public Result<Task> getById(@PathVariable Long id) {
        Task task = taskService.getById(id);
        if (task == null) {
            return Result.error("任务不存在");
        }
        return Result.success(task);
    }

    /**
     * 查询待处理任务列表
     */
    @GetMapping("/pending")
    public Result<List<Task>> getPendingTasks() {
        List<Task> tasks = taskService.getPendingTasks();
        return Result.success(tasks);
    }

    /**
     * 手动执行任务
     */
    @PostMapping("/execute/{id}")
    public Result<Void> executeTask(@PathVariable Long id) {
        log.info("手动执行任务, id: {}", id);
        taskService.executeTask(id);
        return Result.success("任务执行成功");
    }

    /**
     * 更新任务状态
     */
    @PutMapping("/status/{id}")
    public Result<Void> updateStatus(
            @PathVariable Long id,
            @RequestParam Integer status) {
        
        boolean success = taskService.updateTaskStatus(id, status);
        if (success) {
            return Result.success("状态更新成功");
        } else {
            return Result.error("状态更新失败");
        }
    }

    /**
     * 创建任务
     */
    @PostMapping
    public Result<Void> create(@Validated @RequestBody Task task) {
        taskService.save(task);
        return Result.success("任务创建成功");
    }

    /**
     * 更新任务
     */
    @PutMapping
    public Result<Void> update(@Validated @RequestBody Task task) {
        taskService.updateById(task);
        return Result.success("任务更新成功");
    }

    /**
     * 删除任务
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        taskService.removeById(id);
        return Result.success("任务删除成功");
    }
}
