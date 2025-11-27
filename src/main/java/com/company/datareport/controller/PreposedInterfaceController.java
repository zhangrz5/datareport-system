package com.company.datareport.controller;

import com.company.datareport.common.dto.InterfaceResponse;
import com.company.datareport.common.dto.LogDownloadDTO;
import org.springframework.beans.BeanUtils;
import com.company.datareport.entity.*;
import com.company.datareport.mapper.*;
import com.company.datareport.service.InterfaceConfigService;
import com.company.datareport.service.InterfaceLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    private InterfaceConfigService configService;

    @Autowired
    private InterfaceLogService logService;

    @Autowired
    private FileUploadRecordMapper fileUploadRecordMapper;

    @Autowired
    private KeyCertificateMapper keyCertificateMapper;

    @Autowired
    private TemplateInfoMapper templateInfoMapper;

    @Autowired
    private SupplementTaskMapper supplementTaskMapper;

    @Autowired
    private NoticeAnnouncementMapper noticeAnnouncementMapper;

    @Autowired
    private InterfaceLogMapper interfaceLogMapper;

    @Autowired
    private RemoteLogSyncMapper remoteLogSyncMapper;

    /**
     * 接口1: 数据报送接口
     * POST /preposed-machine/api/services/fileUpload
     */
    @PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InterfaceResponse> fileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("APICODE") String apiCode,
            @RequestParam("BUSTYPE") String busType,
            @RequestParam("FILE_NAME") String fileName,
            @RequestParam("SOCIALCREDITCODE") String socialCreditCode,
            @RequestParam("USER") String user,
            @RequestParam("PASSWORD") String password) {

        // 创建日志
        InterfaceLog interfaceLog = logService.createLog("FILE_UPLOAD", busType);
        interfaceLog.setInterfaceUrl("/preposed-machine/api/services/fileUpload");
        interfaceLog.setRequestMethod("POST");
        interfaceLog.setFileName(fileName);
        interfaceLog.setSocialCreditCode(socialCreditCode);
        interfaceLog.setBusinessType(busType);
        logService.logInterfaceCall(interfaceLog);

        try {
            // 验证用户名密码
            if (!configService.validateCredentials(user, password)) {
                interfaceLog.setServiceFlag("2");
                interfaceLog.setResponseMsg("报送文件接口:用户名或密码错误");
                interfaceLog.setStatus(3);
                logService.updateLog(interfaceLog);
                return ResponseEntity.ok(InterfaceResponse.noPermission("报送文件接口:用户名或密码错误"));
            }

            // 验证文件
            if (file.isEmpty()) {
                interfaceLog.setServiceFlag("0");
                interfaceLog.setResponseMsg("报送文件接口:文件为空");
                interfaceLog.setStatus(3);
                logService.updateLog(interfaceLog);
                return ResponseEntity.ok(InterfaceResponse.fail("报送文件接口:文件为空"));
            }

            // 保存文件
            String uploadPath = configService.getFileUploadPath();
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            String savedFileName = generateFileName(socialCreditCode, busType, fileName);
            Path filePath = uploadDir.resolve(savedFileName);
            file.transferTo(filePath.toFile());

            // 记录上传信息
            FileUploadRecord record = new FileUploadRecord();
            record.setLogId(interfaceLog.getLogId());
            record.setApiCode(apiCode);
            record.setBusinessType(busType);
            record.setFileName(fileName);
            record.setFileSize(file.getSize());
            record.setFilePath(filePath.toString());
            record.setSocialCreditCode(socialCreditCode);
            record.setUploadStatus(2); // 成功
            record.setServiceFlag("1");
            record.setResponseMsg("文件接收成功");
            fileUploadRecordMapper.insert(record);

            // 更新日志
            interfaceLog.setServiceFlag("1");
            interfaceLog.setResponseMsg("报送文件接口:文件接收成功");
            interfaceLog.setFilePath(filePath.toString());
            interfaceLog.setFileSize(file.getSize());
            interfaceLog.setStatus(2);
            logService.updateLog(interfaceLog);

            return ResponseEntity.ok(InterfaceResponse.success("报送文件接口:文件接收成功"));

        } catch (IOException e) {
            log.error("文件上传失败", e);
            interfaceLog.setServiceFlag("0");
            interfaceLog.setResponseMsg("报送文件接口:文件保存失败 - " + e.getMessage());
            interfaceLog.setErrorMsg(e.getMessage());
            interfaceLog.setStatus(3);
            logService.updateLog(interfaceLog);
            return ResponseEntity.ok(InterfaceResponse.fail("报送文件接口:文件保存失败"));
        }
    }

    /**
     * 接口2: 密钥证书接口
     * GET /preposed-machine/api/services/keyDownload
     */
    @GetMapping("/keyDownload")
    public ResponseEntity<?> keyDownload(
            @RequestParam("BUSTYPE") String busType,
            @RequestParam("USER") String user,
            @RequestParam("PASSWORD") String password) {

        // 创建日志
        InterfaceLog interfaceLog = logService.createLog("KEY_DOWNLOAD", busType);
        interfaceLog.setInterfaceUrl("/preposed-machine/api/services/keyDownload");
        interfaceLog.setRequestMethod("GET");
        interfaceLog.setBusinessType(busType);
        logService.logInterfaceCall(interfaceLog);

        try {
            // 验证用户名密码
            if (!validateCredentials(user, password, interfaceLog)) {
                return ResponseEntity.ok(InterfaceResponse.noPermission("下载接口:用户名或密码错误"));
            }

            // 查找最新的未下载的密钥文件
            KeyCertificate keyCertificate = keyCertificateMapper.selectOne(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<KeyCertificate>()
                    .eq(busType != null, KeyCertificate::getBusinessType, busType)
                    .eq(KeyCertificate::getStatus, 1) // 已启用
                    .isNull(KeyCertificate::getDownloadTime) // 未下载
                    .orderByDesc(KeyCertificate::getCreateTime)
                    .last("LIMIT 1")
            );

            if (keyCertificate == null) {
                return handleNoNewFile(interfaceLog);
            }

            // 检查文件是否存在
            File keyFile = new File(keyCertificate.getFilePath());
            if (!checkFileExists(keyFile, interfaceLog)) {
                return ResponseEntity.ok(InterfaceResponse.fail("下载接口:文件不存在"));
            }

            // 更新下载时间
            keyCertificate.setDownloadTime(LocalDateTime.now());
            keyCertificateMapper.updateById(keyCertificate);

            // 更新日志
            updateDownloadSuccessLog(interfaceLog, keyCertificate.getFileName(),
                keyCertificate.getFilePath(), keyFile.length());

            // 返回文件
            return createFileDownloadResponse(keyFile);

        } catch (Exception e) {
            return handleDownloadException(e, interfaceLog, "密钥下载");
        }
    }

    /**
     * 接口3: 数据采集目录接口
     * GET /preposed-machine/api/services/tempDownload
     */
    @GetMapping("/tempDownload")
    public ResponseEntity<?> tempDownload(
            @RequestParam(value = "BUSTYPE", defaultValue = "0025") String busType,
            @RequestParam("USER") String user,
            @RequestParam("PASSWORD") String password) {

        // 创建日志
        InterfaceLog interfaceLog = logService.createLog("TEMP_DOWNLOAD", busType);
        interfaceLog.setInterfaceUrl("/preposed-machine/api/services/tempDownload");
        interfaceLog.setRequestMethod("GET");
        interfaceLog.setBusinessType(busType);
        logService.logInterfaceCall(interfaceLog);

        try {
            // 验证用户名密码
            if (!validateCredentials(user, password, interfaceLog)) {
                return ResponseEntity.ok(InterfaceResponse.noPermission("下载接口:用户名或密码错误"));
            }

            // 查找最新的未下载的模板文件
            TemplateInfo templateInfo = templateInfoMapper.selectOne(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<TemplateInfo>()
                    .eq(busType != null, TemplateInfo::getBusinessCode, busType)
                    .eq(TemplateInfo::getStatus, 1) // 已启用
                    .isNull(TemplateInfo::getDownloadTime) // 未下载
                    .orderByDesc(TemplateInfo::getCreateTime)
                    .last("LIMIT 1")
            );

            if (templateInfo == null) {
                return handleNoNewFile(interfaceLog);
            }

            // 检查文件是否存在
            File templateFile = new File(templateInfo.getFilePath());
            if (!checkFileExists(templateFile, interfaceLog)) {
                return ResponseEntity.ok(InterfaceResponse.fail("下载接口:文件不存在"));
            }

            // 更新下载时间
            templateInfo.setDownloadTime(LocalDateTime.now());
            templateInfoMapper.updateById(templateInfo);

            // 更新日志
            updateDownloadSuccessLog(interfaceLog, templateInfo.getFileName(),
                templateInfo.getFilePath(), templateFile.length());

            // 返回文件
            return createFileDownloadResponse(templateFile);

        } catch (Exception e) {
            return handleDownloadException(e, interfaceLog, "模板下载");
        }
    }

    /**
     * 接口4: 接收采集任务接口
     * GET /preposed-machine/api/services/taskDownload
     */
    @GetMapping("/taskDownload")
    public ResponseEntity<?> taskDownload(
            @RequestParam("USER") String user,
            @RequestParam("PASSWORD") String password) {

        // 创建日志
        InterfaceLog interfaceLog = logService.createLog("TASK_DOWNLOAD", null);
        interfaceLog.setInterfaceUrl("/preposed-machine/api/services/taskDownload");
        interfaceLog.setRequestMethod("GET");
        logService.logInterfaceCall(interfaceLog);

        try {
            // 验证用户名密码
            if (!validateCredentials(user, password, interfaceLog)) {
                return ResponseEntity.ok(InterfaceResponse.noPermission("下载接口:用户名或密码错误"));
            }

            // 查找待下载的任务文件(状态为待执行且未下载)
            SupplementTask supplementTask = supplementTaskMapper.selectOne(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<SupplementTask>()
                    .eq(SupplementTask::getTaskStatus, 0) // 待执行
                    .isNull(SupplementTask::getDownloadTime) // 未下载
                    .orderByDesc(SupplementTask::getCreateTime)
                    .last("LIMIT 1")
            );

            if (supplementTask == null) {
                return handleNoNewFile(interfaceLog);
            }

            // 检查文件是否存在
            File taskFile = new File(supplementTask.getFilePath());
            if (!checkFileExists(taskFile, interfaceLog)) {
                return ResponseEntity.ok(InterfaceResponse.fail("下载接口:文件不存在"));
            }

            // 更新下载时间并修改任务状态为执行中
            supplementTask.setDownloadTime(LocalDateTime.now());
            supplementTask.setTaskStatus(1); // 执行中
            supplementTaskMapper.updateById(supplementTask);

            // 更新日志
            interfaceLog.setBusinessType(supplementTask.getBusinessType());
            updateDownloadSuccessLog(interfaceLog, supplementTask.getFileName(),
                supplementTask.getFilePath(), taskFile.length());

            // 返回文件
            return createFileDownloadResponse(taskFile);

        } catch (Exception e) {
            return handleDownloadException(e, interfaceLog, "任务下载");
        }
    }

    /**
     * 接口5: 接收下发数据接口
     * GET /preposed-machine/api/services/noticeDownload
     */
    @GetMapping("/noticeDownload")
    public ResponseEntity<?> noticeDownload(
            @RequestParam("USER") String user,
            @RequestParam("PASSWORD") String password,
            @RequestParam(value = "SYSCODE", required = false) String sysCode,
            @RequestParam(value = "BUSTYPE", required = false) String busType) {

        // 创建日志
        InterfaceLog interfaceLog = logService.createLog("NOTICE_DOWNLOAD", busType);
        interfaceLog.setInterfaceUrl("/preposed-machine/api/services/noticeDownload");
        interfaceLog.setRequestMethod("GET");
        interfaceLog.setBusinessType(busType);
        logService.logInterfaceCall(interfaceLog);

        try {
            // 验证用户名密码
            if (!validateCredentials(user, password, interfaceLog)) {
                return ResponseEntity.ok(InterfaceResponse.noPermission("下载接口:用户名或密码错误"));
            }

            // 安徽模式额外校验
            if (configService.isAhModeEnabled()) {
                if (sysCode == null || busType == null) {
                    interfaceLog.setServiceFlag("0");
                    interfaceLog.setResponseMsg("下载接口:安徽模式下SYSCODE和BUSTYPE参数必填");
                    interfaceLog.setStatus(3);
                    logService.updateLog(interfaceLog);
                    return ResponseEntity.ok(InterfaceResponse.fail("下载接口:参数错误"));
                }
            }

            // 查找待下载的通知文件(未读状态)
            com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<NoticeAnnouncement> queryWrapper =
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<NoticeAnnouncement>()
                    .eq(NoticeAnnouncement::getReadStatus, 0) // 未读
                    .isNull(NoticeAnnouncement::getDownloadTime); // 未下载

            // 如果指定了权限编码，则按权限编码筛选
            if (sysCode != null) {
                queryWrapper.eq(NoticeAnnouncement::getPermissionCode, sysCode);
            }

            // 按重要程度和创建时间排序
            queryWrapper.orderByDesc(NoticeAnnouncement::getImportance)
                       .orderByDesc(NoticeAnnouncement::getPublishTime)
                       .last("LIMIT 1");

            NoticeAnnouncement noticeAnnouncement = noticeAnnouncementMapper.selectOne(queryWrapper);

            if (noticeAnnouncement == null) {
                return handleNoNewFile(interfaceLog);
            }

            // 检查文件是否存在
            File noticeFile = new File(noticeAnnouncement.getFilePath());
            if (!checkFileExists(noticeFile, interfaceLog)) {
                return ResponseEntity.ok(InterfaceResponse.fail("下载接口:文件不存在"));
            }

            // 更新下载时间和已读状态
            noticeAnnouncement.setDownloadTime(LocalDateTime.now());
            noticeAnnouncement.setReadStatus(1); // 已读
            noticeAnnouncement.setReadTime(LocalDateTime.now());
            noticeAnnouncementMapper.updateById(noticeAnnouncement);

            // 更新日志
            updateDownloadSuccessLog(interfaceLog, noticeAnnouncement.getFileName(),
                noticeAnnouncement.getFilePath(), noticeFile.length());

            // 返回文件
            return createFileDownloadResponse(noticeFile);

        } catch (Exception e) {
            return handleDownloadException(e, interfaceLog, "通知下载");
        }
    }

    /**
     * 接口6: 数据日志接口
     * GET /preposed-machine/api/services/logDownload
     */
    @GetMapping("/logDownload")
    public ResponseEntity<InterfaceResponse> logDownload(
            @RequestParam("USER") String user,
            @RequestParam("PASSWORD") String password,
            @RequestParam(value = "STARTDATE", required = false) String startDate,
            @RequestParam(value = "ENDDATE", required = false) String endDate,
            @RequestParam(value = "SIDE", required = false) String side,
            @RequestParam(value = "SYSCODE", required = false) String sysCode,
            @RequestParam(value = "BUSTYPE", required = false) String busType) {

        // 创建日志
        InterfaceLog interfaceLog = logService.createLog("LOG_DOWNLOAD", busType);
        interfaceLog.setInterfaceUrl("/preposed-machine/api/services/logDownload");
        interfaceLog.setRequestMethod("GET");
        interfaceLog.setBusinessType(busType);
        logService.logInterfaceCall(interfaceLog);

        try {
            // 验证用户名密码
            if (!validateCredentials(user, password, interfaceLog)) {
                return ResponseEntity.ok(InterfaceResponse.noPermission("日志下载接口:用户名或密码错误"));
            }

            // 构建远程日志查询条件
            com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<RemoteLogSync> queryWrapper =
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<>();

            // 时间范围筛选 (日期格式: yyyyMMdd)
            if (startDate != null && !startDate.isEmpty()) {
                try {
                    LocalDateTime startDateTime = LocalDateTime.parse(startDate + "000000",
                        DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                    queryWrapper.ge(RemoteLogSync::getPushDate, startDateTime);
                } catch (Exception e) {
                    log.warn("开始日期格式错误: {}, 应为yyyyMMdd格式", startDate);
                }
            }

            if (endDate != null && !endDate.isEmpty()) {
                try {
                    LocalDateTime endDateTime = LocalDateTime.parse(endDate + "235959",
                        DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                    queryWrapper.le(RemoteLogSync::getPushDate, endDateTime);
                } catch (Exception e) {
                    log.warn("结束日期格式错误: {}, 应为yyyyMMdd格式", endDate);
                }
            }

            // SIDE参数筛选模块编码
            if (side != null && !side.isEmpty()) {
                if ("ENTERPRISE".equalsIgnoreCase(side)) {
                    // 返回报送文件日志
                    queryWrapper.eq(RemoteLogSync::getModuleCode, "DATAPUSHLOG");
                } else if ("ETIMED".equalsIgnoreCase(side)) {
                    // 返回获取采集目录日志
                    queryWrapper.eq(RemoteLogSync::getModuleCode, "TEMP");
                }
            }

            // 业务编码筛选
            if (busType != null && !busType.isEmpty()) {
                queryWrapper.eq(RemoteLogSync::getBusinessCode, busType);
            }

            // 按推送时间倒序排列
            queryWrapper.orderByDesc(RemoteLogSync::getPushDate);

            // 限制查询数量，避免数据量过大
            queryWrapper.last("LIMIT 1000");

            // 查询远程日志列表
            List<RemoteLogSync> logs = remoteLogSyncMapper.selectList(queryWrapper);

            // 转换为DTO
            List<LogDownloadDTO> logList = logs.stream().map(log -> {
                LogDownloadDTO dto = new LogDownloadDTO();
                dto.setElId(log.getElId());
                dto.setBusinessCode(log.getBusinessCode());
                dto.setFileName(log.getFileName());
                dto.setPushDate(log.getPushDate());
                dto.setGrabDate(log.getGrabDate());
                dto.setRepairMark(log.getRepairMark());
                dto.setElCreatedate(log.getElCreatedate());
                return dto;
            }).collect(Collectors.toList());

            // 更新日志
            interfaceLog.setServiceFlag("3");
            interfaceLog.setResponseMsg("日志下载接口:获取到对应时间的日志,共" + logList.size() + "条");
            interfaceLog.setStatus(2);
            logService.updateLog(interfaceLog);

            return ResponseEntity.ok(InterfaceResponse.withLogList(
                "日志下载接口:获取到对应时间的日志,共" + logList.size() + "条", logList));

        } catch (Exception e) {
            log.error("日志下载失败", e);
            interfaceLog.setServiceFlag("0");
            interfaceLog.setResponseMsg("日志下载接口:程序处理异常 - " + e.getMessage());
            interfaceLog.setErrorMsg(e.getMessage());
            interfaceLog.setStatus(3);
            logService.updateLog(interfaceLog);
            return ResponseEntity.ok(InterfaceResponse.fail("日志下载接口:程序处理异常"));
        }
    }

    /**
     * 验证用户凭证
     */
    private boolean validateCredentials(String user, String password, InterfaceLog interfaceLog) {
        if (!configService.validateCredentials(user, password)) {
            interfaceLog.setServiceFlag("2");
            interfaceLog.setResponseMsg("下载接口:用户名或密码错误");
            interfaceLog.setStatus(3);
            logService.updateLog(interfaceLog);
            return false;
        }
        return true;
    }

    /**
     * 检查文件是否存在
     */
    private boolean checkFileExists(File file, InterfaceLog interfaceLog) {
        if (!file.exists()) {
            interfaceLog.setServiceFlag("0");
            interfaceLog.setResponseMsg("下载接口:文件不存在");
            interfaceLog.setStatus(3);
            logService.updateLog(interfaceLog);
            return false;
        }
        return true;
    }

    /**
     * 更新下载成功日志
     */
    private void updateDownloadSuccessLog(InterfaceLog interfaceLog, String fileName, String filePath, long fileSize) {
        interfaceLog.setServiceFlag("1");
        interfaceLog.setResponseMsg("下载接口:文件下载成功");
        interfaceLog.setFileName(fileName);
        interfaceLog.setFilePath(filePath);
        interfaceLog.setFileSize(fileSize);
        interfaceLog.setStatus(2);
        logService.updateLog(interfaceLog);
    }

    /**
     * 处理无新文件情况
     */
    private ResponseEntity<InterfaceResponse> handleNoNewFile(InterfaceLog interfaceLog) {
        interfaceLog.setServiceFlag("1");
        interfaceLog.setResponseMsg("下载接口:没有新文件");
        interfaceLog.setStatus(2);
        logService.updateLog(interfaceLog);
        return ResponseEntity.ok(InterfaceResponse.noNewFile("下载接口:没有新文件"));
    }

    /**
     * 处理下载异常
     */
    private ResponseEntity<?> handleDownloadException(Exception e, InterfaceLog interfaceLog, String logPrefix) {
        log.error(logPrefix + "失败", e);
        interfaceLog.setServiceFlag("0");
        interfaceLog.setResponseMsg("下载接口:程序处理异常 - " + e.getMessage());
        interfaceLog.setErrorMsg(e.getMessage());
        interfaceLog.setStatus(3);
        logService.updateLog(interfaceLog);
        return ResponseEntity.ok(InterfaceResponse.fail("下载接口:程序处理异常"));
    }

    /**
     * 生成文件名
     */
    private String generateFileName(String socialCreditCode, String busType, String originalFileName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String uuid = UUID.randomUUID().toString().replace("-", "");

        // 获取文件扩展名
        String extension = "";
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex > 0) {
            extension = originalFileName.substring(dotIndex);
        }

        return String.format("%s_%s_%s_%s%s", socialCreditCode, busType, timestamp, uuid, extension);
    }

    /**
     * 创建文件下载响应
     */
    private ResponseEntity<Resource> createFileDownloadResponse(File file) {
        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getName() + "\"")
                .contentType(MediaType.parseMediaType("text/plain;charset=utf-8"))
                .body(resource);
    }
}
