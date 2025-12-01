package com.company.datareport.mapper.data;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 数据查询Mapper
 * 直接执行SQL语句，返回Map结果集
 *
 * @author system
 * @since 2025-11-27
 */
@Mapper
public interface DataQueryMapper {

    /**
     * 执行查询SQL，返回多行结果
     *
     * @param sql 要执行的SQL语句
     * @return 查询结果列表，每行数据为一个Map
     */
    List<Map<String, Object>> executeQuery(@Param("sql") String sql);

    /**
     * 执行查询SQL，返回多行结果（支持动态参数）
     *
     * @param sql 要执行的SQL语句（支持#{param}占位符）
     * @param params 参数Map
     * @return 查询结果列表，每行数据为一个Map
     */
    List<Map<String, Object>> executeQueryWithParams(@Param("sql") String sql, @Param("params") Map<String, Object> params);

    /**
     * 执行查询SQL，返回单行结果
     *
     * @param sql 要执行的SQL语句
     * @return 单行查询结果Map
     */
    Map<String, Object> executeQueryOne(@Param("sql") String sql);

    /**
     * 执行查询SQL，返回单行结果（支持动态参数）
     *
     * @param sql 要执行的SQL语句（支持#{param}占位符）
     * @param params 参数Map
     * @return 单行查询结果Map
     */
    Map<String, Object> executeQueryOneWithParams(@Param("sql") String sql, @Param("params") Map<String, Object> params);

    /**
     * 执行更新SQL（INSERT、UPDATE、DELETE）
     *
     * @param sql 要执行的SQL语句
     * @return 影响的行数
     */
    int executeUpdate(@Param("sql") String sql);

    /**
     * 执行更新SQL（INSERT、UPDATE、DELETE）（支持动态参数）
     *
     * @param sql 要执行的SQL语句（支持#{param}占位符）
     * @param params 参数Map
     * @return 影响的行数
     */
    int executeUpdateWithParams(@Param("sql") String sql, @Param("params") Map<String, Object> params);

    /**
     * 查询统计数据示例
     * 按业务类型统计接口调用次数
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 统计结果
     */
    @Select("SELECT business_type, COUNT(*) as count, " +
            "SUM(CASE WHEN status = 1 THEN 1 ELSE 0 END) as success_count, " +
            "AVG(cost_time) as avg_cost_time " +
            "FROM interface_log " +
            "WHERE request_time BETWEEN #{startTime} AND #{endTime} " +
            "GROUP BY business_type")
    List<Map<String, Object>> queryInterfaceStatistics(@Param("startTime") String startTime,
                                                        @Param("endTime") String endTime);

    /**
     * 查询表结构信息
     *
     * @param tableName 表名
     * @return 表结构信息
     */
    @Select("SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT " +
            "FROM INFORMATION_SCHEMA.COLUMNS " +
            "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = #{tableName}")
    List<Map<String, Object>> queryTableStructure(@Param("tableName") String tableName);

    /**
     * 动态查询数据
     *
     * @param tableName 表名
     * @param whereClause WHERE条件（不包含WHERE关键字）
     * @param orderBy 排序字段
     * @param limit 限制条数
     * @return 查询结果
     */
    List<Map<String, Object>> queryDynamicData(@Param("tableName") String tableName,
                                                @Param("whereClause") String whereClause,
                                                @Param("orderBy") String orderBy,
                                                @Param("limit") Integer limit);

    /**
     * 查询数据总数
     *
     * @param tableName 表名
     * @param whereClause WHERE条件（不包含WHERE关键字）
     * @return 数据总数
     */
    Long queryDataCount(@Param("tableName") String tableName,
                        @Param("whereClause") String whereClause);
}
