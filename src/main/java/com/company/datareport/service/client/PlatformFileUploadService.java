package com.company.datareport.service.client;

import com.company.datareport.common.dto.InterfaceResponse;
import com.company.datareport.entity.system.FileUploadRecord;
import com.company.datareport.mapper.system.FileUploadRecordMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.time.Duration;
import java.util.UUID;

/**
 * 平台文件上传客户端服务
 * 用于调用国资委数据采集交换平台的文件上传接口
 *
 * @author system
 * @since 2025-12-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlatformFileUploadService {

    private final WebClient webClient;
    private final FileUploadRecordMapper fileUploadRecordMapper;

    @Value("${app.platform.upload-url}")
    private String uploadUrl;

    @Value("${app.platform.username}")
    private String defaultUsername;

    @Value("${app.platform.password}")
    private String defaultPassword;

    @Value("${app.corporate.code}")
    private String corporateCode;

    /**
     * 上传文件到平台
     *
     * @param file 要上传的文件
     * @param apiCode API编码
     * @param busType 业务类型
     * @param user 用户名
     * @param password 密码
     * @return 接口响应
     */
    public InterfaceResponse uploadFile(File file, String apiCode, String busType, String user, String password) {
        // 生成日志ID
        String logId = UUID.randomUUID().toString().replace("-", "");

        // 创建上传记录
        FileUploadRecord record = new FileUploadRecord();
        record.setLogId(logId);
        record.setApiCode(apiCode);
        record.setBusinessType(busType);
        record.setFileName(file.getName());
        record.setFileSize(file.length());
        record.setFilePath(file.getAbsolutePath());
        record.setSocialCreditCode(corporateCode);
        record.setUploadStatus(1); // 处理中
        record.setRetryCount(0);

        try {
            log.info("开始上传文件到平台: {}, 业务类型: {}, 日志ID: {}", file.getName(), busType, logId);

            // 保存初始记录
            fileUploadRecordMapper.insert(record);

            // 构建 Multipart 请求体
            MultipartBodyBuilder builder = new MultipartBodyBuilder();
            builder.part("file", new FileSystemResource(file));
            builder.part("APICODE", apiCode);
            builder.part("BUSTYPE", busType);
            builder.part("FILE_NAME", file.getName());
            builder.part("SOCIALCREDITCODE", corporateCode);
            builder.part("USER", user);
            builder.part("PASSWORD", password);

            // 使用 WebClient 发送请求
            Mono<InterfaceResponse> responseMono = webClient.post()
                    .uri(uploadUrl)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(builder.build()))
                    .retrieve()
                    .bodyToMono(InterfaceResponse.class)
                    .timeout(Duration.ofSeconds(60));

            // 阻塞获取响应（同步调用）
            InterfaceResponse result = responseMono.block();

            // 更新上传记录
            if (result != null && "1".equals(result.getServiceFlag())) {
                log.info("文件上传成功: {}, 日志ID: {}", file.getName(), logId);
                record.setUploadStatus(2); // 成功
                record.setServiceFlag("1");
                record.setResponseMsg(result.getMsg());
            } else {
                log.warn("文件上传失败: {}, 响应: {}, 日志ID: {}", file.getName(), result, logId);
                record.setUploadStatus(3); // 失败
                record.setServiceFlag(result != null ? result.getServiceFlag() : "0");
                record.setResponseMsg(result != null ? result.getMsg() : "未收到响应");
            }

            // 更新记录
            fileUploadRecordMapper.updateById(record);

            return result;

        } catch (Exception e) {
            log.error("文件上传异常: {}, 日志ID: {}", file.getName(), logId, e);

            // 更新记录为失败状态
            record.setUploadStatus(3); // 失败
            record.setServiceFlag("0");
            record.setResponseMsg("文件上传异常");
            record.setErrorMsg(e.getMessage());
            fileUploadRecordMapper.updateById(record);

            return InterfaceResponse.fail("文件上传异常: " + e.getMessage());
        }
    }

    /**
     * 上传文件到平台（使用配置的认证信息）
     *
     * @param file 要上传的文件
     * @param apiCode API编码
     * @param busType 业务类型
     * @return 接口响应
     */
    public InterfaceResponse uploadFile(File file, String apiCode, String busType) {
        // 从配置中获取用户名密码（需要在配置文件中配置）
        return uploadFile(file, apiCode, busType, getDefaultUser(), getDefaultPassword());
    }

    /**
     * 上传文件到平台（简化版，只需要文件路径和业务类型）
     *
     * @param filePath 文件路径
     * @return 接口响应
     */
    public InterfaceResponse uploadFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            log.error("文件不存在: {}", filePath);
            return InterfaceResponse.fail("文件不存在: " + filePath);
        }

        // 使用默认的 APICODE
        return uploadFile(file, "SZ01", "0026");
    }

    /**
     * 获取默认用户名（从配置文件读取）
     */
    private String getDefaultUser() {
        return defaultUsername;
    }

    /**
     * 获取默认密码（从配置文件读取）
     */
    private String getDefaultPassword() {
        return defaultPassword;
    }
}
