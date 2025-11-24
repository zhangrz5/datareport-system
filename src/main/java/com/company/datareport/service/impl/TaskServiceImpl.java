package com.company.datareport.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.datareport.common.constants.BusinessConstants;
import com.company.datareport.common.exception.BusinessException;
import com.company.datareport.entity.Task;
import com.company.datareport.mapper.TaskMapper;
import com.company.datareport.service.DataCollectService;
import com.company.datareport.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务服务实现类
 *
 * @author qwe
 * @since 2025-01-24
 */
@Slf4j
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private DataCollectService dataCollectService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.platform.task-list-url}")
    private String taskListUrl;

    @Value("${app.platform.enterprise-code}")
    private String enterpriseCode;

    @Value("${app.platform.auth-key}")
    private String authKey;

    @Override
    public int fetchTasksFromPlatform() {
        log.info("开始从平台获取任务列表...");
        
        try {
            // 构建请求参数
            Map<String, Object> params = new HashMap<>();
            params.put("enterpriseCode", enterpriseCode);
            params.put("authKey", authKey);
            
            // 调用平台API
            String response = restTemplate.postForObject(taskListUrl, params, String.class);
            
            if (StrUtil.isEmpty(response)) {
                log.warn("平台返回空数据");
                return 0;
            }
            
            // 解析响应数据(根据实际API格式调整)
            // 这里假设返回的是JSON数组
            List<Map<String, Object>> taskList = JSONUtil.toList(response, Map.class);
            
            int count = 0;
            for (Map<String, Object> taskMap : taskList) {
                // 检查任务是否已存在
                String taskCode = (String) taskMap.get("taskCode");
                Task existTask = taskMapper.selectByTaskCode(taskCode);
                
                if (existTask == null) {
                    // 创建新任务
                    Task task = new Task();
                    task.setTaskCode(taskCode);
                    task.setTaskName((String) taskMap.get("taskName"));
                    task.setTaskType((String) taskMap.get("taskType"));
                    task.setDataSource((String) taskMap.get("dataSource"));
                    task.setTaskDesc((String) taskMap.get("taskDesc"));
                    task.setTaskStatus(BusinessConstants.TaskStatus.PENDING);
                    task.setCollectRule(JSONUtil.toJsonStr(taskMap.get("collectRule")));
                    task.setTargetDatasource((String) taskMap.get("targetDatasource"));
                    task.setRetryCount(0);
                    
                    this.save(task);
                    count++;
                }
            }
            
            log.info("从平台获取任务完成,新增任务数: {}", count);
            return count;
            
        } catch (Exception e) {
            log.error("从平台获取任务失败", e);
            throw new BusinessException("从平台获取任务失败: " + e.getMessage());
        }
    }

    @Override
    public List<Task> getPendingTasks() {
        return taskMapper.selectPendingTasks();
    }

    @Override
    public Task getByTaskCode(String taskCode) {
        return taskMapper.selectByTaskCode(taskCode);
    }

    @Override
    public boolean updateTaskStatus(Long taskId, Integer status) {
        return taskMapper.updateTaskStatus(taskId, status) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeTask(Long taskId) {
        log.info("开始执行任务, taskId: {}", taskId);
        
        Task task = this.getById(taskId);
        if (task == null) {
            throw new BusinessException("任务不存在");
        }
        
        if (!BusinessConstants.TaskStatus.PENDING.equals(task.getTaskStatus())) {
            log.warn("任务状态不是待处理,跳过执行, taskId: {}, status: {}", taskId, task.getTaskStatus());
            return;
        }
        
        try {
            // 更新任务状态为处理中
            task.setTaskStatus(BusinessConstants.TaskStatus.PROCESSING);
            task.setStartTime(LocalDateTime.now());
            this.updateById(task);
            
            // 执行数据采集
            dataCollectService.collectData(task);
            
            // 更新任务状态为已完成
            task.setTaskStatus(BusinessConstants.TaskStatus.COMPLETED);
            task.setEndTime(LocalDateTime.now());
            this.updateById(task);
            
            log.info("任务执行完成, taskId: {}", taskId);
            
        } catch (Exception e) {
            log.error("任务执行失败, taskId: {}", taskId, e);
            
            // 更新任务状态为失败
            task.setTaskStatus(BusinessConstants.TaskStatus.FAILED);
            task.setEndTime(LocalDateTime.now());
            task.setErrorMsg(e.getMessage());
            task.setRetryCount(task.getRetryCount() + 1);
            this.updateById(task);
            
            throw new BusinessException("任务执行失败: " + e.getMessage());
        }
    }
}
