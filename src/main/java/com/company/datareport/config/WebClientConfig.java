package com.company.datareport.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * WebClient 配置
 *
 * @author system
 * @since 2025-12-01
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create()
                // 连接超时：30秒
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                // 响应超时：5分钟（文件上传需要较长时间）
                .responseTimeout(Duration.ofMinutes(5))
                .doOnConnected(conn -> conn
                        // 读取超时：5分钟
                        .addHandlerLast(new ReadTimeoutHandler(5, TimeUnit.MINUTES))
                        // 写入超时：5分钟
                        .addHandlerLast(new WriteTimeoutHandler(5, TimeUnit.MINUTES))
                );

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        // 设置最大内存缓冲区大小（文件上传需要）
                        .maxInMemorySize(50 * 1024 * 1024)) // 50MB
                .build();
    }
}
