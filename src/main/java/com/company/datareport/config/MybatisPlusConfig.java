package com.company.datareport.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 配置
 *
 * @author qwe
 * @since 2025-01-24
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * MyBatis-Plus 拦截器配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 分页插件(使用达梦数据库)
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.DM);
        // 设置请求的页面大于最大页后操作,true调回到首页,false继续请求,默认false
        paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量,默认500条,-1不受限制
        paginationInterceptor.setMaxLimit(500L);

        interceptor.addInnerInterceptor(paginationInterceptor);

        return interceptor;
    }
}
