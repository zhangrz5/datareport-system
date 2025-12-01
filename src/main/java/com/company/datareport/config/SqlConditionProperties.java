package com.company.datareport.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * SQL条件配置属性类
 * 用于加载动态SQL条件片段
 *
 * @author qwe
 * @since 2025-01-24
 */
@Data
@Component
@ConfigurationProperties(prefix = "sql-conditions")
public class SqlConditionProperties {

    /**
     * 通用条件
     */
    private Map<String, String> common;

    /**
     * 排序条件
     */
    private Map<String, String> orderBy;

    /**
     * 分页条件
     */
    private Map<String, String> pagination;

    /**
     * 获取通用条件
     */
    public String getCommonCondition(String key) {
        return common.get(key);
    }

    /**
     * 获取排序条件
     */
    public String getOrderBy(String key) {
        return orderBy.get(key);
    }

    /**
     * 获取分页条件
     */
    public String getPagination(String key) {
        return pagination.get(key);
    }
}
