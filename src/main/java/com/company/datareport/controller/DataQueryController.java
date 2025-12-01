package com.company.datareport.controller;

import com.company.datareport.service.data.DataQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数据查询控制器
 * 提供动态SQL查询接口示例
 *
 * @author system
 * @since 2025-11-27
 */
@Slf4j
@RestController
@RequestMapping("/api/data")
public class DataQueryController {

    @Autowired
    private DataQueryService dataQueryService;

    /**
     * 执行查询SQL
     *
     * @param sql SQL语句
     * @return 查询结果
     */
    @PostMapping("/query")
    public Map<String, Object> executeQuery(@RequestBody Map<String, String> params) {
        String sql = params.get("sql");
        log.info("接收到查询请求: {}", sql);

        try {
            List<Map<String, Object>> data = dataQueryService.executeQuery(sql);
            return Map.of(
                    "success", true,
                    "message", "查询成功",
                    "data", data
            );
        } catch (Exception e) {
            log.error("查询失败", e);
            return Map.of(
                    "success", false,
                    "message", "查询失败: " + e.getMessage()
            );
        }
    }

    /**
     * 执行单行查询SQL
     *
     * @param sql SQL语句
     * @return 查询结果
     */
    @PostMapping("/query-one")
    public Map<String, Object> executeQueryOne(@RequestBody Map<String, String> params) {
        String sql = params.get("sql");
        log.info("接收到单行查询请求: {}", sql);

        try {
            Map<String, Object> data = dataQueryService.executeQueryOne(sql);
            return Map.of(
                    "success", true,
                    "message", "查询成功",
                    "data", data
            );
        } catch (Exception e) {
            log.error("查询失败", e);
            return Map.of(
                    "success", false,
                    "message", "查询失败: " + e.getMessage()
            );
        }
    }

    /**
     * 执行更新SQL
     *
     * @param sql SQL语句
     * @return 影响行数
     */
    @PostMapping("/update")
    public Map<String, Object> executeUpdate(@RequestBody Map<String, String> params) {
        String sql = params.get("sql");
        log.info("接收到更新请求: {}", sql);

        try {
            int rows = dataQueryService.executeUpdate(sql);
            return Map.of(
                    "success", true,
                    "message", "更新成功",
                    "rows", rows
            );
        } catch (Exception e) {
            log.error("更新失败", e);
            return Map.of(
                    "success", false,
                    "message", "更新失败: " + e.getMessage()
            );
        }
    }

    /**
     * 查询接口统计数据
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 统计结果
     */
    @GetMapping("/statistics")
    public Map<String, Object> queryStatistics(
            @RequestParam String startTime,
            @RequestParam String endTime) {
        log.info("查询统计数据: {} ~ {}", startTime, endTime);

        try {
            List<Map<String, Object>> data = dataQueryService.queryInterfaceStatistics(startTime, endTime);
            return Map.of(
                    "success", true,
                    "message", "查询成功",
                    "data", data
            );
        } catch (Exception e) {
            log.error("查询统计数据失败", e);
            return Map.of(
                    "success", false,
                    "message", "查询失败: " + e.getMessage()
            );
        }
    }

    /**
     * 查询表结构
     *
     * @param tableName 表名
     * @return 表结构信息
     */
    @GetMapping("/table-structure")
    public Map<String, Object> queryTableStructure(@RequestParam String tableName) {
        log.info("查询表结构: {}", tableName);

        try {
            List<Map<String, Object>> data = dataQueryService.queryTableStructure(tableName);
            return Map.of(
                    "success", true,
                    "message", "查询成功",
                    "data", data
            );
        } catch (Exception e) {
            log.error("查询表结构失败", e);
            return Map.of(
                    "success", false,
                    "message", "查询失败: " + e.getMessage()
            );
        }
    }

    /**
     * 分页查询数据
     *
     * @param tableName 表名
     * @param whereClause WHERE条件
     * @param orderBy 排序字段
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果
     */
    @GetMapping("/page")
    public Map<String, Object> queryByPage(
            @RequestParam String tableName,
            @RequestParam(required = false) String whereClause,
            @RequestParam(required = false) String orderBy,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询: 表={}, 页码={}, 每页={}", tableName, pageNum, pageSize);

        try {
            Map<String, Object> data = dataQueryService.queryDataByPage(
                    tableName, whereClause, orderBy, pageNum, pageSize);
            return Map.of(
                    "success", true,
                    "message", "查询成功",
                    "data", data
            );
        } catch (Exception e) {
            log.error("分页查询失败", e);
            return Map.of(
                    "success", false,
                    "message", "查询失败: " + e.getMessage()
            );
        }
    }

    /**
     * 动态查询数据
     *
     * @param tableName 表名
     * @param whereClause WHERE条件
     * @param orderBy 排序字段
     * @param limit 限制条数
     * @return 查询结果
     */
    @GetMapping("/list")
    public Map<String, Object> queryList(
            @RequestParam String tableName,
            @RequestParam(required = false) String whereClause,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) Integer limit) {
        log.info("动态查询: 表={}, 限制={}", tableName, limit);

        try {
            List<Map<String, Object>> data = dataQueryService.queryDynamicData(
                    tableName, whereClause, orderBy, limit);
            return Map.of(
                    "success", true,
                    "message", "查询成功",
                    "data", data
            );
        } catch (Exception e) {
            log.error("动态查询失败", e);
            return Map.of(
                    "success", false,
                    "message", "查询失败: " + e.getMessage()
            );
        }
    }

    /**
     * 查询数据总数
     *
     * @param tableName 表名
     * @param whereClause WHERE条件
     * @return 数据总数
     */
    @GetMapping("/count")
    public Map<String, Object> queryCount(
            @RequestParam String tableName,
            @RequestParam(required = false) String whereClause) {
        log.info("查询总数: 表={}", tableName);

        try {
            Long count = dataQueryService.queryDataCount(tableName, whereClause);
            return Map.of(
                    "success", true,
                    "message", "查询成功",
                    "count", count
            );
        } catch (Exception e) {
            log.error("查询总数失败", e);
            return Map.of(
                    "success", false,
                    "message", "查询失败: " + e.getMessage()
            );
        }
    }

    /**
     * 批量执行SQL
     *
     * @param sqlList SQL列表
     * @return 执行结果
     */
    @PostMapping("/batch")
    public Map<String, Object> executeBatch(@RequestBody Map<String, List<String>> params) {
        List<String> sqlList = params.get("sqlList");
        log.info("批量执行SQL: {} 条", sqlList != null ? sqlList.size() : 0);

        try {
            boolean success = dataQueryService.executeBatch(sqlList);
            return Map.of(
                    "success", success,
                    "message", "批量执行成功"
            );
        } catch (Exception e) {
            log.error("批量执行失败", e);
            return Map.of(
                    "success", false,
                    "message", "批量执行失败: " + e.getMessage()
            );
        }
    }
}
