package com.company.datareport.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.company.datareport.common.constants.BusinessConstants;
import com.company.datareport.common.exception.BusinessException;
import com.company.datareport.entity.TaskData;
import com.company.datareport.entity.UploadRecord;
import com.company.datareport.mapper.TaskDataMapper;
import com.company.datareport.mapper.UploadRecordMapper;
import com.company.datareport.service.DataUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据上报服务实现类
 *
 * @author qwe
 * @since 2025-01-24
 */
@Slf4j
@Service
public class DataUploadServiceImpl implements DataUploadService {

    @Autowired
    private TaskDataMapper taskDataMapper;

    @Autowired
    private UploadRecordMapper uploadRecordMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${app.platform.upload-url}")
    private String uploadUrl;

    @Value("${app.platform.enterprise-code}")
    private String enterpriseCode;

    @Value("${app.platform.auth-key}")
    private String authKey;

    @Override
    @Retryable(retryFor = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadSingleData(TaskData taskData) {
        log.info("开始上报单条数据, dataCode: {}", taskData.getDataCode());

        long startTime = System.currentTimeMillis();
        UploadRecord record = new UploadRecord();
        record.setTaskId(taskData.getTaskId());
        record.setTaskCode(taskData.getTaskCode());
        record.setTaskDataId(taskData.getId());
        record.setBatchNo(IdUtil.fastSimpleUUID());
        record.setUploadType(1); // 单条上报
        record.setUploadStatus(BusinessConstants.UploadStatus.UPLOADING);
        record.setRequestUrl(uploadUrl);
        record.setRetryCount(taskData.getRetryCount());

        try {
            // 构建请求数据
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("enterpriseCode", enterpriseCode);
            requestData.put("authKey", authKey);
            requestData.put("taskCode", taskData.getTaskCode());
            requestData.put("dataCode", taskData.getDataCode());
            requestData.put("data", JSONUtil.parseObj(taskData.getDataContent()));

            record.setUploadData(JSONUtil.toJsonStr(requestData));
            record.setRequestParams(JSONUtil.toJsonStr(requestData));

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestData, headers);

            // 发送请求
            ResponseEntity<String> response = restTemplate.postForEntity(uploadUrl, request, String.class);

            // 记录响应
            long costTime = System.currentTimeMillis() - startTime;
            record.setResponseCode(response.getStatusCode().value());
            record.setResponseBody(response.getBody());
            record.setCostTime(costTime);

            // 判断上报是否成功
            if (response.getStatusCode().is2xxSuccessful()) {
                record.setUploadStatus(BusinessConstants.UploadStatus.SUCCESS);

                // 更新任务数据状态
                taskData.setUploadStatus(BusinessConstants.UploadStatus.SUCCESS);
                taskData.setUploadTime(LocalDateTime.now());
                taskData.setUploadResponse(response.getBody());
                taskDataMapper.updateById(taskData);

                log.info("数据上报成功, dataCode: {}, costTime: {}ms", taskData.getDataCode(), costTime);
                return true;

            } else {
                record.setUploadStatus(BusinessConstants.UploadStatus.FAILED);
                record.setErrorMsg("上报失败: HTTP " + response.getStatusCode().value());

                // 更新任务数据状态
                taskData.setUploadStatus(BusinessConstants.UploadStatus.FAILED);
                taskData.setErrorMsg(record.getErrorMsg());
                taskData.setRetryCount(taskData.getRetryCount() + 1);
                taskDataMapper.updateById(taskData);

                log.error("数据上报失败, dataCode: {}, error: {}", taskData.getDataCode(), record.getErrorMsg());
                return false;
            }

        } catch (Exception e) {
            long costTime = System.currentTimeMillis() - startTime;
            record.setUploadStatus(BusinessConstants.UploadStatus.FAILED);
            record.setErrorMsg(e.getMessage());
            record.setCostTime(costTime);

            // 更新任务数据状态
            taskData.setUploadStatus(BusinessConstants.UploadStatus.FAILED);
            taskData.setErrorMsg(e.getMessage());
            taskData.setRetryCount(taskData.getRetryCount() + 1);
            taskDataMapper.updateById(taskData);

            log.error("数据上报异常, dataCode: {}", taskData.getDataCode(), e);
            throw new BusinessException("数据上报失败: " + e.getMessage());

        } finally {
            // 保存上报记录
            uploadRecordMapper.insert(record);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int uploadBatchData(List<TaskData> taskDataList) {
        log.info("开始批量上报数据, 数量: {}", taskDataList.size());

        if (taskDataList == null || taskDataList.isEmpty()) {
            return 0;
        }

        int successCount = 0;
        String batchNo = IdUtil.fastSimpleUUID();

        // 更新状态为上报中
        List<Long> ids = taskDataList.stream().map(TaskData::getId).collect(Collectors.toList());
        taskDataMapper.batchUpdateUploadStatus(ids, BusinessConstants.UploadStatus.UPLOADING);

        // 逐条上报(也可以改为真正的批量上报)
        for (TaskData taskData : taskDataList) {
            try {
                if (uploadSingleData(taskData)) {
                    successCount++;
                }
            } catch (Exception e) {
                log.error("批量上报中单条数据上报失败, dataCode: {}", taskData.getDataCode(), e);
            }
        }

        log.info("批量上报完成, batchNo: {}, 成功: {}/{}", batchNo, successCount, taskDataList.size());
        return successCount;
    }

    @Override
    public void executeScheduledUpload() {
        log.info("执行定时上报任务");

        // 查询待上报的数据(限制数量避免一次处理太多)
        List<TaskData> pendingData = taskDataMapper.selectPendingUploadData(100);

        if (pendingData.isEmpty()) {
            log.info("没有待上报的数据");
            return;
        }

        log.info("查询到待上报数据: {} 条", pendingData.size());

        // 批量上报
        uploadBatchData(pendingData);
    }

    @Override
    public int retryFailedUploads() {
        log.info("重试上报失败的数据");

        // TODO: 实现重试逻辑
        // 1. 查询上报失败且重试次数未超限的数据
        // 2. 重新上报

        return 0;
    }
}
