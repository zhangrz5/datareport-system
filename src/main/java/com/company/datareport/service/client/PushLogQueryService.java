package com.company.datareport.service.client;


import com.company.datareport.entity.system.PushLogRecord;
import com.company.datareport.service.system.PushLogRecordService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class PushLogQueryService {

    @Value("${app.platform.log-url}")
    private String serverUrl;

    @Value("${app.platform.username}")
    private String defaultUsername;

    @Value("${app.platform.password}")
    private String defaultPassword;

    @Autowired
    private WebClient webClient;

    @Autowired
    private PushLogRecordService pushLogRecordService;

    private static final String SIDE = "ENTERPRISE";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 查询报送日志
     */
    public QueryResult queryLog(String startDate, String endDate) {
        LocalDateTime requestTime = LocalDateTime.now();
        long startTime = System.currentTimeMillis();

        String url = serverUrl;

        // 构建请求参数
        String requestParams = String.format("STARTDATE=%s&ENDDATE=%s&USER=%s&PASSWORD=%s&SIDE=%s",
                startDate, endDate, defaultUsername, defaultPassword, SIDE);

        // 创建记录
        PushLogRecord record = pushLogRecordService.createRecord(url, requestParams, startDate, endDate, requestTime);

        QueryResult result = new QueryResult();
        result.setRecordId(record.getId());
        result.setStartDate(startDate);
        result.setEndDate(endDate);

        String finalUrl = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("STARTDATE", startDate)
                .queryParam("ENDDATE", endDate)
                .queryParam("USER", defaultUsername)
                .queryParam("PASSWORD", defaultPassword)
                .queryParam("SIDE", SIDE)
                .build()
                .toUriString();

        log.info("查询报送日志: startDate={}, endDate={},请求地址是{}", startDate, endDate,finalUrl);

        try {
            String responseBody = webClient.post()
                    .uri(finalUrl)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(Duration.ofMinutes(5));

            LocalDateTime responseTime = LocalDateTime.now();
            long costTime = System.currentTimeMillis() - startTime;

            log.info("查询完成, 耗时: {} ms", costTime);

            result.setSuccess(true);
            result.setResponseJson(responseBody);
            result.setCostTime(costTime);

            pushLogRecordService.updateSuccess(record.getId(), responseBody, responseTime, costTime);

            return result;

        } catch (Exception e) {
            LocalDateTime responseTime = LocalDateTime.now();
            long costTime = System.currentTimeMillis() - startTime;

            log.error("查询异常, 耗时: {} ms", costTime, e);

            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
            result.setCostTime(costTime);

            pushLogRecordService.updateFailed(record.getId(), e.getMessage(), responseTime, costTime);

            return result;
        }
    }

    /**
     * 查询今天的日志
     */
    public QueryResult queryTodayLog() {
        String today = LocalDate.now().format(DATE_FORMATTER);
        return queryLog(today, today);
    }

    /**
     * 查询昨天的日志
     */
    public QueryResult queryYesterdayLog() {
        String yesterday = LocalDate.now().minusDays(1).format(DATE_FORMATTER);
        return queryLog(yesterday, yesterday);
    }

    @Data
    public static class QueryResult {
        private Long recordId;
        private String startDate;
        private String endDate;
        private boolean success;
        private String responseJson;
        private String errorMsg;
        private Long costTime;
    }
}