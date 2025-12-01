package com.company.datareport.service.data;

import java.util.List;
import java.util.Map;

/**
 * 数据查询服务接口
 *
 * @author system
 * @since 2025-11-27
 */
public interface DataQueryService {

    /**
     * 执行查询SQL，返回多行结果
     *
     * @param sql 要执行的SQL语句
     * @return 查询结果列表
     */
    List<Map<String, Object>> executeQuery(String sql);

    /**
     * 执行查询SQL，返回多行结果（支持动态参数）
     *
     * @param sql 要执行的SQL语句（支持?占位符）
     * @param params 参数列表
     * @return 查询结果列表
     */
    List<Map<String, Object>> executeQuery(String sql, Object... params);

    /**
     * 执行查询SQL，返回单行结果
     *
     * @param sql 要执行的SQL语句
     * @return 单行查询结果
     */
    Map<String, Object> executeQueryOne(String sql);

    /**
     * 执行查询SQL，返回单行结果（支持动态参数）
     *
     * @param sql 要执行的SQL语句（支持?占位符）
     * @param params 参数列表
     * @return 单行查询结果
     */
    Map<String, Object> executeQueryOne(String sql, Object... params);

    /**
     * 执行更新SQL（INSERT、UPDATE、DELETE）
     *
     * @param sql 要执行的SQL语句
     * @return 影响的行数
     */
    int executeUpdate(String sql);

    /**
     * 执行更新SQL（INSERT、UPDATE、DELETE）（支持动态参数）
     *
     * @param sql 要执行的SQL语句（支持?占位符）
     * @param params 参数列表
     * @return 影响的行数
     */
    int executeUpdate(String sql, Object... params);

    /**
     * 查询接口统计数据
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 统计结果
     */
    List<Map<String, Object>> queryInterfaceStatistics(String startTime, String endTime);

    /**
     * 查询表结构信息
     *
     * @param tableName 表名
     * @return 表结构信息
     */
    List<Map<String, Object>> queryTableStructure(String tableName);

    /**
     * 动态查询数据（分页）
     *
     * @param tableName 表名
     * @param whereClause WHERE条件
     * @param orderBy 排序字段
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页查询结果
     */
    Map<String, Object> queryDataByPage(String tableName, String whereClause,
                                        String orderBy, Integer pageNum, Integer pageSize);

    /**
     * 动态查询数据（不分页）
     *
     * @param tableName 表名
     * @param whereClause WHERE条件
     * @param orderBy 排序字段
     * @param limit 限制条数
     * @return 查询结果
     */
    List<Map<String, Object>> queryDynamicData(String tableName, String whereClause,
                                                String orderBy, Integer limit);

    /**
     * 查询数据总数
     *
     * @param tableName 表名
     * @param whereClause WHERE条件
     * @return 数据总数
     */
    Long queryDataCount(String tableName, String whereClause);

    /**
     * 批量执行SQL语句
     *
     * @param sqlList SQL语句列表
     * @return 执行结果
     */
    boolean executeBatch(List<String> sqlList);
}
