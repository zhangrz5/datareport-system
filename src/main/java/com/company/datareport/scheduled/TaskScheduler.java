package com.company.datareport.scheduled;

import com.company.datareport.common.constants.BusinessConstants;
import com.company.datareport.entity.Task;
import com.company.datareport.service.DataUploadService;
import com.company.datareport.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务调度器
 *
 * @author qwe
 * @since 2025-01-24
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "app.scheduler.enabled", havingValue = "true", matchIfMissing = true)
public class TaskScheduler {

    @Autowired
    private TaskService taskService;

    @Autowired
    private DataUploadService dataUploadService;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 定时拉取任务
     * 默认每天凌晨1点执行
     */
    @Scheduled(cron = "${app.scheduler.task-fetch-cron:0 0 1 * * ?}")
    public void fetchTasksScheduled() {
        String lockKey = BusinessConstants.RedisKey.TASK_LOCK_PREFIX + "fetch";
        RLock lock = redissonClient.getLock(lockKey);
        
        try {
            // 尝试获取锁,最多等待0秒,锁定60秒后自动释放
            if (lock.tryLock(0, 60, TimeUnit.SECONDS)) {
                log.info("开始执行定时任务: 拉取任务");
                
                int count = taskService.fetchTasksFromPlatform();
                
                log.info("定时任务执行完成: 拉取任务, 新增任务数: {}", count);
            } else {
                log.warn("定时任务正在执行中,跳过本次执行: 拉取任务");
            }
        } catch (Exception e) {
            log.error("定时任务执行失败: 拉取任务", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    /**
     * 定时执行待处理任务
     * 每5分钟执行一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void executeTasksScheduled() {
        String lockKey = BusinessConstants.RedisKey.TASK_LOCK_PREFIX + "execute";
        RLock lock = redissonClient.getLock(lockKey);
        
        try {
            if (lock.tryLock(0, 300, TimeUnit.SECONDS)) {
                log.info("开始执行定时任务: 处理待执行任务");
                
                List<Task> pendingTasks = taskService.getPendingTasks();
                
                if (pendingTasks.isEmpty()) {
                    log.info("没有待处理的任务");
                    return;
                }
                
                log.info("查询到待处理任务: {} 个", pendingTasks.size());
                
                for (Task task : pendingTasks) {
                    try {
                        taskService.executeTask(task.getId());
                    } catch (Exception e) {
                        log.error("任务执行失败, taskId: {}", task.getId(), e);
                    }
                }
                
                log.info("定时任务执行完成: 处理待执行任务");
            } else {
                log.warn("定时任务正在执行中,跳过本次执行: 处理待执行任务");
            }
        } catch (Exception e) {
            log.error("定时任务执行失败: 处理待执行任务", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    /**
     * 定时上报数据
     * 默认每天凌晨3点执行
     */
    @Scheduled(cron = "${app.scheduler.data-upload-cron:0 0 3 * * ?}")
    public void uploadDataScheduled() {
        String lockKey = BusinessConstants.RedisKey.UPLOAD_LOCK_PREFIX + "scheduled";
        RLock lock = redissonClient.getLock(lockKey);
        
        try {
            if (lock.tryLock(0, 300, TimeUnit.SECONDS)) {
                log.info("开始执行定时任务: 上报数据");
                
                dataUploadService.executeScheduledUpload();
                
                log.info("定时任务执行完成: 上报数据");
            } else {
                log.warn("定时任务正在执行中,跳过本次执行: 上报数据");
            }
        } catch (Exception e) {
            log.error("定时任务执行失败: 上报数据", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    /**
     * 定时重试失败的上报
     * 每小时执行一次
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    public void retryFailedUploadsScheduled() {
        String lockKey = BusinessConstants.RedisKey.UPLOAD_LOCK_PREFIX + "retry";
        RLock lock = redissonClient.getLock(lockKey);
        
        try {
            if (lock.tryLock(0, 60, TimeUnit.SECONDS)) {
                log.info("开始执行定时任务: 重试失败的上报");
                
                int count = dataUploadService.retryFailedUploads();
                
                log.info("定时任务执行完成: 重试失败的上报, 成功数: {}", count);
            } else {
                log.warn("定时任务正在执行中,跳过本次执行: 重试失败的上报");
            }
        } catch (Exception e) {
            log.error("定时任务执行失败: 重试失败的上报", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
