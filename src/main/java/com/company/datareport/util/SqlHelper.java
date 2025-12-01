package com.company.datareport.util;

import com.company.datareport.config.SqlConditionProperties;
import com.company.datareport.config.SqlConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * SQL辅助工具类
 * 提供SQL构建和拼接的辅助方法
 *
 * @author qwe
 * @since 2025-01-24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SqlHelper {

    private final SqlConfigProperties sqlConfig;
    private final SqlConditionProperties sqlCondition;

    /**
     * 获取SQL语句
     *
     * @param category SQL分类
     * @param module 模块名称
     * @param key SQL键名
     * @return SQL语句
     */
    public String getSql(String category, String module, String key) {
        return sqlConfig.getSql(category, module, key);
    }

    /**
     * 获取业务SQL
     *
     * @param key SQL键名
     * @return SQL语句
     */
    public String getBusinessSql(String key) {
        return sqlConfig.getBusiness().get(key);
    }

    /**
     * 构建带条件的SQL
     *
     * @param baseSql 基础SQL
     * @param conditions 条件键值对
     * @return 完整SQL
     */
    public String buildSqlWithConditions(String baseSql, String... conditions) {
        StringBuilder sql = new StringBuilder(baseSql);

        for (String condition : conditions) {
            String conditionSql = sqlCondition.getCommonCondition(condition);
            if (conditionSql != null) {
                sql.append(" ").append(conditionSql);
            }
        }

        return sql.toString();
    }

    /**
     * 构建带排序的SQL
     *
     * @param baseSql 基础SQL
     * @param orderByKey 排序键
     * @return 完整SQL
     */
    public String buildSqlWithOrderBy(String baseSql, String orderByKey) {
        String orderBy = sqlCondition.getOrderBy(orderByKey);
        if (orderBy == null) {
            return baseSql;
        }
        return baseSql + " " + orderBy;
    }

    /**
     * 构建分页SQL
     *
     * @param baseSql 基础SQL
     * @param dbType 数据库类型（mysql/sqlserver）
     * @return 分页SQL
     */
    public String buildSqlWithPagination(String baseSql, String dbType) {
        String pagination = sqlCondition.getPagination(dbType);
        if (pagination == null) {
            pagination = sqlCondition.getPagination("mysql"); // 默认使用MySQL
        }
        return baseSql + " " + pagination;
    }

    /**
     * 构建IN查询SQL
     *
     * @param baseSql 基础SQL（包含IN关键字）
     * @param paramCount 参数数量
     * @return 完整SQL
     */
    public String buildInSql(String baseSql, int paramCount) {
        if (paramCount <= 0) {
            return baseSql;
        }

        String placeholders = String.join(", ",
            java.util.Collections.nCopies(paramCount, "?"));
        return baseSql + " (" + placeholders + ")";
    }

    /**
     * 构建批量插入SQL
     *
     * @param baseSql 基础插入SQL
     * @param batchSize 批量大小
     * @param columnCount 列数量
     * @return 批量插入SQL
     */
    public String buildBatchInsertSql(String baseSql, int batchSize, int columnCount) {
        if (batchSize <= 0 || columnCount <= 0) {
            return baseSql;
        }

        // 构建单行占位符：(?, ?, ?)
        String singleRowPlaceholder = "(" +
            String.join(", ", java.util.Collections.nCopies(columnCount, "?")) + ")";

        // 构建多行占位符
        String multiRowPlaceholder = String.join(", ",
            java.util.Collections.nCopies(batchSize, singleRowPlaceholder));

        return baseSql + " " + multiRowPlaceholder;
    }

    /**
     * 替换SQL中的占位符
     *
     * @param sql SQL语句
     * @param placeholder 占位符
     * @param value 替换值
     * @return 替换后的SQL
     */
    public String replacePlaceholder(String sql, String placeholder, String value) {
        return sql.replace(placeholder, value);
    }

    /**
     * 动态构建LIKE查询条件
     *
     * @param column 列名
     * @return LIKE条件SQL
     */
    public String buildLikeCondition(String column) {
        String template = sqlCondition.getCommonCondition("likeSearch");
        if (template == null) {
            return "AND " + column + " LIKE CONCAT('%', ?, '%')";
        }
        return template.replace("${column}", column);
    }

    /**
     * 记录SQL执行日志
     *
     * @param sqlKey SQL键名
     * @param sql 执行的SQL
     * @param params 参数
     */
    public void logSqlExecution(String sqlKey, String sql, Object... params) {
        if (log.isDebugEnabled()) {
            log.debug("执行SQL [{}]: {}", sqlKey, sql);
            if (params != null && params.length > 0) {
                log.debug("SQL参数: {}", java.util.Arrays.toString(params));
            }
        }
    }
}
