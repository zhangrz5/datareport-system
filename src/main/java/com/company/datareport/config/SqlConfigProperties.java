package com.company.datareport.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * SQL配置属性类
 * 用于从YAML配置文件中加载SQL语句
 *
 * @author qwe
 * @since 2025-01-24
 */
@Data
@Component
@ConfigurationProperties(prefix = "sql")
public class SqlConfigProperties {

    /**
     * 查询SQL
     */
    private Map<String, Map<String, String>> query;

    /**
     * 插入SQL
     */
    private Map<String, Map<String, String>> insert;

    /**
     * 更新SQL
     */
    private Map<String, Map<String, String>> update;

    /**
     * 删除SQL
     */
    private Map<String, Map<String, String>> delete;

    /**
     * 统计SQL
     */
    private Map<String, Map<String, String>> statistics;

    /**
     * SQLite相关SQL
     */
    private Map<String, Map<String, String>> sqlite;

    /**
     * 业务SQL
     */
    private Map<String, String> business;

    /**
     * 获取SQL语句
     *
     * @param category SQL分类（query/insert/update/delete/statistics/sqlite/business）
     * @param module 模块名称（person/organization/equity等）
     * @param key SQL键名
     * @return SQL语句
     */
    public String getSql(String category, String module, String key) {
        Map<String, Map<String, String>> categoryMap = getCategoryMap(category);
        if (categoryMap == null) {
            throw new IllegalArgumentException("未找到SQL分类: " + category);
        }

        if ("business".equals(category)) {
            return business.get(module);
        }

        Map<String, String> moduleMap = categoryMap.get(module);
        if (moduleMap == null) {
            throw new IllegalArgumentException("未找到SQL模块: " + module);
        }

        String sql = moduleMap.get(key);
        if (sql == null) {
            throw new IllegalArgumentException("未找到SQL: " + category + "." + module + "." + key);
        }

        return sql;
    }

    /**
     * 获取分类Map
     */
    private Map<String, Map<String, String>> getCategoryMap(String category) {
        switch (category) {
            case "query":
                return query;
            case "insert":
                return insert;
            case "update":
                return update;
            case "delete":
                return delete;
            case "statistics":
                return statistics;
            case "sqlite":
                return sqlite;
            default:
                return null;
        }
    }
}
