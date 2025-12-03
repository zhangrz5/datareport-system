package com.company.datareport.service.client;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.company.datareport.entity.system.NoticeDownloadRecord;
import com.company.datareport.service.system.NoticeDownloadRecordService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class NoticeDownloadService {

    @Value("${app.platform.download-url}")
    private String serverUrl;

    @Value("${app.platform.username}")
    private String defaultUsername;

    @Value("${app.platform.password}")
    private String defaultPassword;

    @Value("${app.upload.download-path}")
    private String savePath;

    @Value("${app.upload.download-try}")
    private int MAX_RETRY;

    @Value("${app.upload.download-delay}")
    private int delay;

    @Autowired
    private WebClient webClient;

    @Autowired
    private NoticeDownloadRecordService noticeDownloadRecordService;


    @Async
    public void downloadAllNoticesAsync() {
        List<NoticeDownloadResult> results = downloadAllNotices();
        NoticeDownloadResult result=results.get(results.size()-1);
        if(result.isHasFile()){
            log.error("轮询下载到了zip包，未编写后续处理方法，根据实际返回编写");
        }else {
            log.warn("本次轮询了{}次仍未获取到zip包，改日获取",MAX_RETRY);
        }
    }

    /**
     * 轮询下载所有通知公告文件 (使用 Flux 响应式流)
     */
    public List<NoticeDownloadResult> downloadAllNotices() {
        AtomicInteger count = new AtomicInteger(0);
        List<NoticeDownloadResult> results = new ArrayList<>();

        log.info("开始轮询下载通知公告文件");

        NoticeDownloadResult result = Flux.defer(() ->
                        Mono.fromCallable(this::downloadOnce)
                                .subscribeOn(Schedulers.boundedElastic())  // 在支持阻塞的线程池中执行
                                .doOnNext(r -> {
                                    int current = count.incrementAndGet();
                                    results.add(r);
                                    log.info("第 {} 次轮询, hasFile: {}", current, r.isHasFile());
                                })
                                // 每次轮询间隔 60 秒
                                .delayElement(Duration.ofSeconds(delay))
                )
                .repeat(MAX_RETRY - 1)
                // 改为 takeUntil：条件为 true 时停止（包含该元素）
                .takeUntil(NoticeDownloadResult::isHasFile)
                // 获取最后一个结果
                .last()
                .block();

        int finalCount = count.get();
        if (result != null && result.isHasFile()) {
            log.info("轮询成功，共轮询 {} 次，获取到文件: {}", finalCount, result.getFileName());
        } else {
            log.warn("轮询结束，共轮询 {} 次，未获取到文件", finalCount);
        }
        return results;
    }

    /**
     * 单次下载
     */
    public NoticeDownloadResult downloadOnce() {

        LocalDateTime requestTime = LocalDateTime.now();
        long startTime = System.currentTimeMillis();

        // 创建下载记录
        NoticeDownloadRecord record = noticeDownloadRecordService.createRecord(serverUrl, requestTime);
        NoticeDownloadResult result = new NoticeDownloadResult();
        result.setRecordId(record.getId());

        log.debug("请求下载通知公告: {}", serverUrl);

        try {
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("USER", defaultUsername);
            requestBody.put("PASSWORD", defaultPassword);
            ClientResponse response = webClient.post()
                    .uri(serverUrl)
                    //先按照json来，不行再说
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .exchangeToMono(Mono::just)
                    .block(Duration.ofMinutes(5));

            LocalDateTime responseTime = LocalDateTime.now();
            long costTime = System.currentTimeMillis() - startTime;

            if (response == null) {
                throw new RuntimeException("响应为空");
            }

            // 获取 Content-Type
            MediaType contentType = response.headers().contentType().orElse(MediaType.APPLICATION_JSON);
            String contentTypeStr = contentType.toString();
            result.setContentType(contentTypeStr);

            // 判断是否为 JSON 响应（无文件或异常）
            if (contentType.includes(MediaType.APPLICATION_JSON)) {
                String body = response.bodyToMono(String.class).block();
                log.info("收到 JSON 响应: {}", body);

                result.setHasFile(false);
                result.setSuccess(false);
                result.setResponseMsg(body);

                // 解析 JSON
                JSONObject json = JSONUtil.parseObj(body);
                String serviceFlag = json.getStr("serviceFlag");
                String msg = json.getStr("msg");

                if ("1".equals(serviceFlag) || StrUtil.contains(msg, "无文件")) {
                    noticeDownloadRecordService.updateNoFile(record.getId(), body, contentTypeStr, responseTime, costTime);
                } else {
                    noticeDownloadRecordService.updateFailed(record.getId(), serviceFlag, msg, null, contentTypeStr, responseTime, costTime);
                }

                return result;
            }

            // 下载文件
            result.setHasFile(true);

            // 从响应头获取文件名
            String contentDisposition = response.headers().asHttpHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION);
            String fileName = extractFileName(contentDisposition);
            if (StrUtil.isBlank(fileName)) {
                fileName = "notice_" + System.currentTimeMillis() + ".zip";
            }

            // 确保保存目录存在
            FileUtil.mkdir(savePath);

            // 保存文件
            String filePath = savePath + File.separator + fileName;
            byte[] fileBytes = response.bodyToMono(byte[].class).block();

            if (fileBytes == null || fileBytes.length == 0) {
                throw new RuntimeException("文件内容为空");
            }

            FileUtil.writeBytes(fileBytes, filePath);
            long fileSize = fileBytes.length;

            log.info("文件下载成功: {}, 大小: {} 字节, 耗时: {} ms", fileName, fileSize, costTime);

            // 更新记录
            noticeDownloadRecordService.updateSuccess(record.getId(), fileName, filePath, fileSize, contentTypeStr, responseTime, costTime);

            result.setSuccess(true);
            result.setFileName(fileName);
            result.setFilePath(filePath);
            result.setFileSize(fileSize);
            result.setCostTime(costTime);

            return result;

        } catch (Exception e) {
            LocalDateTime responseTime = LocalDateTime.now();
            long costTime = System.currentTimeMillis() - startTime;

            log.error("下载通知公告异常, 耗时: {} ms", costTime, e);

            result.setHasFile(false);
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
            result.setCostTime(costTime);

            noticeDownloadRecordService.updateFailed(record.getId(), null, null, e.getMessage(), null, responseTime, costTime);

            return result;
        }
    }



    /**
     * 从 Content-Disposition 提取文件名
     */
    private String extractFileName(String contentDisposition) {
        if (StrUtil.isBlank(contentDisposition)) {
            return null;
        }

        String[] parts = contentDisposition.split(";");
        for (String part : parts) {
            part = part.trim();
            if (part.toLowerCase().startsWith("filename=")) {
                String fileName = part.substring(9);
                fileName = fileName.replace("\"", "").replace("'", "");
                return fileName;
            }
        }
        return null;
    }

    /**
     * 下载结果
     */
    @Data
    public static class NoticeDownloadResult {
        private Long recordId;
        private boolean hasFile;
        private boolean success;
        private String fileName;
        private String filePath;
        private Long fileSize;
        private String contentType;
        private String responseMsg;
        private String errorMsg;
        private Long costTime;
    }
}
