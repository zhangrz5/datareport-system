package com.company.datareport.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.company.datareport.common.constants.BusinessConstants;
import com.company.datareport.common.exception.BusinessException;
import com.company.datareport.entity.Task;
import com.company.datareport.entity.TaskData;
import com.company.datareport.mapper.TaskDataMapper;
import com.company.datareport.service.DataCollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据采集服务实现类
 *
 * @author qwe
 * @since 2025-01-24
 */
@Slf4j
@Service
public class DataCollectServiceImpl implements DataCollectService {

    @Autowired
    private TaskDataMapper taskDataMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<TaskData> collectData(Task task) {
        log.info("开始采集任务数据, taskCode: {}, dataSource: {}", task.getTaskCode(), task.getDataSource());
        
        List<TaskData> taskDataList = new ArrayList<>();
        
        try {
            // 根据数据来源选择采集方式
            String dataSource = task.getDataSource();
            
            if (BusinessConstants.DataSource.LOCAL_DB.equals(dataSource)) {
                // 从本地数据库采集
                taskDataList = collectFromDatabase(task, task.getTargetDatasource());
                
            } else if (BusinessConstants.DataSource.ENTERPRISE_SYSTEM.equals(dataSource)) {
                // 从企业系统数据库采集
                taskDataList = collectFromDatabase(task, task.getTargetDatasource());
                
            } else if (BusinessConstants.DataSource.EXTERNAL_API.equals(dataSource)) {
                // 从外部接口采集
                taskDataList = collectFromApi(task);
                
            } else if (BusinessConstants.DataSource.EXCEL_IMPORT.equals(dataSource)) {
                // 从Excel文件采集
                taskDataList = collectFromExcel(task, null);
                
            } else {
                throw new BusinessException("不支持的数据来源: " + dataSource);
            }
            
            // 保存采集的数据
            for (TaskData taskData : taskDataList) {
                taskDataMapper.insert(taskData);
            }
            
            log.info("任务数据采集完成, taskCode: {}, 采集数量: {}", task.getTaskCode(), taskDataList.size());
            return taskDataList;
            
        } catch (Exception e) {
            log.error("任务数据采集失败, taskCode: {}", task.getTaskCode(), e);
            throw new BusinessException("数据采集失败: " + e.getMessage());
        }
    }

    @Override
    public List<TaskData> collectFromDatabase(Task task, String datasource) {
        log.info("从数据库采集数据, taskCode: {}, datasource: {}", task.getTaskCode(), datasource);
        
        List<TaskData> taskDataList = new ArrayList<>();
        
        try {
            // 切换数据源
            DynamicDataSourceContextHolder.push(datasource);
            
            // 执行采集SQL
            String collectSql = task.getCollectSql();
            if (collectSql == null || collectSql.isEmpty()) {
                throw new BusinessException("采集SQL为空");
            }
            
            List<Map<String, Object>> resultList = jdbcTemplate.queryForList(collectSql);
            
            // 转换为TaskData对象
            for (Map<String, Object> row : resultList) {
                TaskData taskData = new TaskData();
                taskData.setTaskId(task.getId());
                taskData.setTaskCode(task.getTaskCode());
                taskData.setDataCode(IdUtil.fastSimpleUUID());
                taskData.setDataContent(JSONUtil.toJsonStr(row));
                taskData.setDataSource(datasource);
                taskData.setDataStatus(2); // 已处理
                taskData.setUploadStatus(BusinessConstants.UploadStatus.NOT_UPLOADED);
                taskData.setRetryCount(0);
                
                taskDataList.add(taskData);
            }
            
            return taskDataList;
            
        } catch (Exception e) {
            log.error("从数据库采集数据失败", e);
            throw new BusinessException("从数据库采集数据失败: " + e.getMessage());
        } finally {
            // 清除数据源
            DynamicDataSourceContextHolder.clear();
        }
    }

    @Override
    public List<TaskData> collectFromApi(Task task) {
        log.info("从外部接口采集数据, taskCode: {}", task.getTaskCode());
        
        // TODO: 实现从外部接口采集数据的逻辑
        // 1. 解析采集规则,获取API地址和参数
        // 2. 调用外部API
        // 3. 解析响应数据
        // 4. 转换为TaskData对象
        
        throw new BusinessException("从外部接口采集数据功能待实现");
    }

    @Override
    public List<TaskData> collectFromExcel(Task task, String filePath) {
        log.info("从Excel文件采集数据, taskCode: {}, filePath: {}", task.getTaskCode(), filePath);
        
        // TODO: 实现从Excel文件采集数据的逻辑
        // 1. 读取Excel文件
        // 2. 解析数据
        // 3. 转换为TaskData对象
        
        throw new BusinessException("从Excel文件采集数据功能待实现");
    }
}
