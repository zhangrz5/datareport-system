package com.company.datareport.service.data.impl;

import com.company.datareport.mapper.data.DataQueryMapper;
import com.company.datareport.service.data.DataQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据查询服务实现类
 *
 * @author system
 * @since 2025-11-27
 */
@Slf4j
@Service
public class DataQueryServiceImpl implements DataQueryService {

    @Autowired
    private DataQueryMapper dataQueryMapper;

    @Override
    public List<Map<String, Object>> executeQuery(String sql) {
        log.info("执行查询SQL: {}", sql);
        try {
            List<Map<String, Object>> result = dataQueryMapper.executeQuery(sql);
            log.info("查询成功，返回 {} 条记录", result != null ? result.size() : 0);
            return result;
        } catch (Exception e) {
            log.error("执行查询SQL失败: {}", sql, e);
            throw new RuntimeException("查询失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Map<String, Object>> executeQuery(String sql, Object... params) {
        //log.info("执行查询SQL: {}, 参数: {}", sql, params);
        try {
            // 将可变参数转换为Map
            Map<String, Object> paramMap = convertParamsToMap(params);
            List<Map<String, Object>> result = dataQueryMapper.executeQueryWithParams(sql, paramMap);
            //log.info("查询成功，返回 {} 条记录", result != null ? result.size() : 0);
            return result;
        } catch (Exception e) {
            log.error("执行查询SQL失败: {}, 参数: {}", sql, params, e);
            throw new RuntimeException("查询失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> executeQueryOne(String sql) {
        log.info("执行单行查询SQL: {}", sql);
        try {
            Map<String, Object> result = dataQueryMapper.executeQueryOne(sql);
            log.info("查询成功");
            return result;
        } catch (Exception e) {
            log.error("执行单行查询SQL失败: {}", sql, e);
            throw new RuntimeException("查询失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> executeQueryOne(String sql, Object... params) {
        //log.info("执行单行查询SQL: {}, 参数: {}", sql, params);
        try {
            // 将可变参数转换为Map
            Map<String, Object> paramMap = convertParamsToMap(params);
            Map<String, Object> result = dataQueryMapper.executeQueryOneWithParams(sql, paramMap);
            //log.info("查询成功");
            return result;
        } catch (Exception e) {
            log.error("执行单行查询SQL失败: {}, 参数: {}", sql, params, e);
            throw new RuntimeException("查询失败: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int executeUpdate(String sql) {
        log.info("执行更新SQL: {}", sql);
        try {
            int rows = dataQueryMapper.executeUpdate(sql);
            log.info("更新成功，影响 {} 行", rows);
            return rows;
        } catch (Exception e) {
            log.error("执行更新SQL失败: {}", sql, e);
            throw new RuntimeException("更新失败: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int executeUpdate(String sql, Object... params) {
        //log.info("执行更新SQL: {}, 参数: {}", sql, params);
        try {
            // 将可变参数转换为Map
            Map<String, Object> paramMap = convertParamsToMap(params);
            int rows = dataQueryMapper.executeUpdateWithParams(sql, paramMap);
            //log.info("更新成功，影响 {} 行", rows);
            return rows;
        } catch (Exception e) {
            log.error("执行更新SQL失败: {}, 参数: {}", sql, params, e);
            throw new RuntimeException("更新失败: " + e.getMessage(), e);
        }
    }

    /**
     * 将可变参数数组转换为Map
     * 参数按照param0, param1, param2...的格式存储
     *
     * @param params 参数数组
     * @return 参数Map
     */
    private Map<String, Object> convertParamsToMap(Object... params) {
        Map<String, Object> paramMap = new HashMap<>();
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                paramMap.put("param" + i, params[i]);
            }
        }
        return paramMap;
    }

    @Override
    public List<Map<String, Object>> queryInterfaceStatistics(String startTime, String endTime) {
        log.info("查询接口统计数据，时间范围: {} ~ {}", startTime, endTime);
        try {
            List<Map<String, Object>> result = dataQueryMapper.queryInterfaceStatistics(startTime, endTime);
            log.info("统计查询成功，返回 {} 条记录", result != null ? result.size() : 0);
            return result;
        } catch (Exception e) {
            log.error("查询接口统计数据失败", e);
            throw new RuntimeException("统计查询失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Map<String, Object>> queryTableStructure(String tableName) {
        log.info("查询表结构: {}", tableName);
        try {
            List<Map<String, Object>> result = dataQueryMapper.queryTableStructure(tableName);
            log.info("表结构查询成功，共 {} 个字段", result != null ? result.size() : 0);
            return result;
        } catch (Exception e) {
            log.error("查询表结构失败: {}", tableName, e);
            throw new RuntimeException("表结构查询失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> queryDataByPage(String tableName, String whereClause,
                                                String orderBy, Integer pageNum, Integer pageSize) {
        log.info("分页查询数据，表: {}, 页码: {}, 每页: {}", tableName, pageNum, pageSize);

        try {
            // 查询总数
            Long total = dataQueryMapper.queryDataCount(tableName, whereClause);

            // 计算分页参数
            if (pageNum == null || pageNum < 1) {
                pageNum = 1;
            }
            if (pageSize == null || pageSize < 1) {
                pageSize = 10;
            }

            // 查询数据
            List<Map<String, Object>> records = dataQueryMapper.queryDynamicData(
                    tableName, whereClause, orderBy, pageSize);

            // 构建返回结果
            Map<String, Object> result = new HashMap<>();
            result.put("total", total);
            result.put("pageNum", pageNum);
            result.put("pageSize", pageSize);
            result.put("pages", (total + pageSize - 1) / pageSize);
            result.put("records", records);

            log.info("分页查询成功，总数: {}, 当前页: {} 条", total, records != null ? records.size() : 0);
            return result;
        } catch (Exception e) {
            log.error("分页查询失败", e);
            throw new RuntimeException("分页查询失败: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Map<String, Object>> queryDynamicData(String tableName, String whereClause,
                                                       String orderBy, Integer limit) {
        log.info("动态查询数据，表: {}, 限制: {} 条", tableName, limit);
        try {
            List<Map<String, Object>> result = dataQueryMapper.queryDynamicData(
                    tableName, whereClause, orderBy, limit);
            log.info("查询成功，返回 {} 条记录", result != null ? result.size() : 0);
            return result;
        } catch (Exception e) {
            log.error("动态查询失败", e);
            throw new RuntimeException("查询失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Long queryDataCount(String tableName, String whereClause) {
        log.info("查询数据总数，表: {}", tableName);
        try {
            Long count = dataQueryMapper.queryDataCount(tableName, whereClause);
            log.info("查询成功，总数: {}", count);
            return count;
        } catch (Exception e) {
            log.error("查询数据总数失败", e);
            throw new RuntimeException("查询失败: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean executeBatch(List<String> sqlList) {
        log.info("批量执行SQL，共 {} 条", sqlList != null ? sqlList.size() : 0);
        if (sqlList == null || sqlList.isEmpty()) {
            log.warn("SQL列表为空，无需执行");
            return true;
        }

        try {
            int successCount = 0;
            for (String sql : sqlList) {
                if (sql != null && !sql.trim().isEmpty()) {
                    dataQueryMapper.executeUpdate(sql);
                    successCount++;
                }
            }
            log.info("批量执行成功，成功 {} 条", successCount);
            return true;
        } catch (Exception e) {
            log.error("批量执行SQL失败", e);
            throw new RuntimeException("批量执行失败: " + e.getMessage(), e);
        }
    }
}
