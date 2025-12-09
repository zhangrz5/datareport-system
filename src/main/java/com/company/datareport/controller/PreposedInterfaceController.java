package com.company.datareport.controller;

import com.company.datareport.common.dto.InterfaceResponse;
import com.company.datareport.entity.system.FileUploadRecord;
import com.company.datareport.entity.system.InterfaceLog;
import com.company.datareport.mapper.system.FileUploadRecordMapper;
import com.company.datareport.mapper.system.KeyCertificateMapper;
import com.company.datareport.service.system.InterfaceConfigService;
import com.company.datareport.service.system.InterfaceLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 国资委数据采集交换平台接口控制器
 * 实现6个接口:
 * 1. 数据报送接口 (fileUpload)
 * 2. 密钥证书接口 (keyDownload)
 * 3. 数据采集目录接口 (tempDownload)
 * 4. 接收采集任务接口 (taskDownload)
 * 5. 接收下发数据接口 (noticeDownload)
 * 6. 数据日志接口 (logDownload)
 *
 * @author system
 * @since 2025-11-25
 */
@Slf4j
@RestController
@RequestMapping("/preposed-machine/api/services")
public class PreposedInterfaceController {

//    @Autowired
//    private InterfaceConfigService configService;
//
//    @Autowired
//    private InterfaceLogService logService;
//
//    @Autowired
//    private FileUploadRecordMapper fileUploadRecordMapper;
//
//    @Autowired
//    private KeyCertificateMapper keyCertificateMapper;
//
//    /**
//     * 接口1: 数据报送接口
//     * POST /preposed-machine/api/services/fileUpload
//     */
//    @PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<InterfaceResponse> fileUpload(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("APICODE") String apiCode,
//            @RequestParam("BUSTYPE") String busType,
//            @RequestParam("FILE_NAME") String fileName,
//            @RequestParam("SOCIALCREDITCODE") String socialCreditCode,
//            @RequestParam("USER") String user,
//            @RequestParam("PASSWORD") String password) {
//
//        // 创建日志
//        InterfaceLog interfaceLog = logService.createLog("FILE_UPLOAD", busType);
//        interfaceLog.setInterfaceUrl("/preposed-machine/api/services/fileUpload");
//        interfaceLog.setRequestMethod("POST");
//        interfaceLog.setFileName(fileName);
//        interfaceLog.setSocialCreditCode(socialCreditCode);
//        interfaceLog.setBusinessType(busType);
//        logService.logInterfaceCall(interfaceLog);
//
//        try {
//            // 验证用户名密码
//            if (!configService.validateCredentials(user, password)) {
//                interfaceLog.setServiceFlag("2");
//                interfaceLog.setResponseMsg("报送文件接口:用户名或密码错误");
//                interfaceLog.setStatus(3);
//                logService.updateLog(interfaceLog);
//                return ResponseEntity.ok(InterfaceResponse.noPermission("报送文件接口:用户名或密码错误"));
//            }
//
//            // 验证文件
//            if (file.isEmpty()) {
//                interfaceLog.setServiceFlag("0");
//                interfaceLog.setResponseMsg("报送文件接口:文件为空");
//                interfaceLog.setStatus(3);
//                logService.updateLog(interfaceLog);
//                return ResponseEntity.ok(InterfaceResponse.fail("报送文件接口:文件为空"));
//            }
//
//            // 保存文件
//            String uploadPath = configService.getFileUploadPath();
//            java.nio.file.Path uploadDir = java.nio.file.Paths.get(uploadPath);
//            if (!java.nio.file.Files.exists(uploadDir)) {
//                java.nio.file.Files.createDirectories(uploadDir);
//            }
//
//            String savedFileName = generateFileName(socialCreditCode, busType, fileName);
//            java.nio.file.Path filePath = uploadDir.resolve(savedFileName);
//            file.transferTo(filePath.toFile());
//
//            // 记录上传信息
//            FileUploadRecord record = new FileUploadRecord();
//            record.setLogId(interfaceLog.getLogId());
//            record.setApiCode(apiCode);
//            record.setBusinessType(busType);
//            record.setFileName(fileName);
//            record.setFileSize(file.getSize());
//            record.setFilePath(filePath.toString());
//            record.setSocialCreditCode(socialCreditCode);
//            record.setUploadStatus(2); // 成功
//            record.setServiceFlag("1");
//            record.setResponseMsg("文件接收成功");
//            fileUploadRecordMapper.insert(record);
//
//            // 更新日志
//            interfaceLog.setServiceFlag("1");
//            interfaceLog.setResponseMsg("报送文件接口:文件接收成功");
//            interfaceLog.setFilePath(filePath.toString());
//            interfaceLog.setFileSize(file.getSize());
//            interfaceLog.setStatus(2);
//            logService.updateLog(interfaceLog);
//
//            return ResponseEntity.ok(InterfaceResponse.success("报送文件接口:文件接收成功"));
//
//        } catch (java.io.IOException e) {
//            log.error("文件上传失败", e);
//            interfaceLog.setServiceFlag("0");
//            interfaceLog.setResponseMsg("报送文件接口:文件保存失败 - " + e.getMessage());
//            interfaceLog.setErrorMsg(e.getMessage());
//            interfaceLog.setStatus(3);
//            logService.updateLog(interfaceLog);
//            return ResponseEntity.ok(InterfaceResponse.fail("报送文件接口:文件保存失败"));
//        }
//    }
//
//    /**
//     * 生成文件名
//     */
//    private String generateFileName(String socialCreditCode, String busType, String originalFileName) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        String timestamp = LocalDateTime.now().format(formatter);
//        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
//
//        // 获取文件扩展名
//        String extension = "";
//        int dotIndex = originalFileName.lastIndexOf(".");
//        if (dotIndex > 0) {
//            extension = originalFileName.substring(dotIndex);
//        }
//
//        return String.format("%s_%s_%s_%s%s", socialCreditCode, busType, timestamp, uuid, extension);
//    }
//
//
//    /**
//     * 验证用户凭证
//     */
//    private boolean validateCredentials(String user, String password, InterfaceLog interfaceLog) {
//        if (!configService.validateCredentials(user, password)) {
//            interfaceLog.setServiceFlag("2");
//            interfaceLog.setResponseMsg("下载接口:用户名或密码错误");
//            interfaceLog.setStatus(3);
//            logService.updateLog(interfaceLog);
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 检查文件是否存在
//     */
//    private boolean checkFileExists(File file, InterfaceLog interfaceLog) {
//        if (!file.exists()) {
//            interfaceLog.setServiceFlag("0");
//            interfaceLog.setResponseMsg("下载接口:文件不存在");
//            interfaceLog.setStatus(3);
//            logService.updateLog(interfaceLog);
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 更新下载成功日志
//     */
//    private void updateDownloadSuccessLog(InterfaceLog interfaceLog, String fileName, String filePath, long fileSize) {
//        interfaceLog.setServiceFlag("1");
//        interfaceLog.setResponseMsg("下载接口:文件下载成功");
//        interfaceLog.setFileName(fileName);
//        interfaceLog.setFilePath(filePath);
//        interfaceLog.setFileSize(fileSize);
//        interfaceLog.setStatus(2);
//        logService.updateLog(interfaceLog);
//    }
//
//    /**
//     * 处理无新文件情况
//     */
//    private ResponseEntity<InterfaceResponse> handleNoNewFile(InterfaceLog interfaceLog) {
//        interfaceLog.setServiceFlag("1");
//        interfaceLog.setResponseMsg("下载接口:没有新文件");
//        interfaceLog.setStatus(2);
//        logService.updateLog(interfaceLog);
//        return ResponseEntity.ok(InterfaceResponse.noNewFile("下载接口:没有新文件"));
//    }
//
//    /**
//     * 处理下载异常
//     */
//    private ResponseEntity<?> handleDownloadException(Exception e, InterfaceLog interfaceLog, String logPrefix) {
//        log.error(logPrefix + "失败", e);
//        interfaceLog.setServiceFlag("0");
//        interfaceLog.setResponseMsg("下载接口:程序处理异常 - " + e.getMessage());
//        interfaceLog.setErrorMsg(e.getMessage());
//        interfaceLog.setStatus(3);
//        logService.updateLog(interfaceLog);
//        return ResponseEntity.ok(InterfaceResponse.fail("下载接口:程序处理异常"));
//    }
//
//    /**
//     * 创建文件下载响应
//     */
//    private ResponseEntity<Resource> createFileDownloadResponse(File file) {
//        Resource resource = new FileSystemResource(file);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getName() + "\"")
//                .contentType(MediaType.parseMediaType("text/plain;charset=utf-8"))
//                .body(resource);
//    }
}
