package com.company.datareport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 数据采集上报系统启动类
 *
 * @author qwe
 * @since 2025-01-24
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableRetry
@MapperScan("com.company.datareport.mapper")
public class DataReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataReportApplication.class, args);
        System.out.println("========================================");
        System.out.println("数据采集上报系统启动成功!");
        System.out.println("========================================");
    }
}
