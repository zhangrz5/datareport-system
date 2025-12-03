package com.company.datareport.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.company.datareport.entity.system.*;
import com.company.datareport.mapper.system.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 监控管理界面控制器
 * 展示系统运行记录数据
 *
 * @author system
 * @since 2025-12-02
 */
@Slf4j
@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private SyncRecordMapper syncRecordMapper;

    @Autowired
    private DataReportRecordMapper dataReportRecordMapper;

    @Autowired
    private FileUploadRecordMapper fileUploadRecordMapper;

    @Autowired
    private NoticeDownloadRecordMapper noticeDownloadRecordMapper;

    @Autowired
    private PushLogRecordMapper pushLogRecordMapper;

    /**
     * 监控首页
     */
    @GetMapping("/index")
    public String index() {
        return "monitor";
    }

    /**
     * 获取同步记录数据
     */
    @GetMapping("/syncRecords")
    @ResponseBody
    public Map<String, Object> getSyncRecords(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {

        Page<SyncRecord> pageInfo = new Page<>(page, limit);
        IPage<SyncRecord> result = syncRecordMapper.selectPage(pageInfo,
                new LambdaQueryWrapper<SyncRecord>()
                        .orderByDesc(SyncRecord::getSyncTime));

        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("msg", "");
        response.put("count", result.getTotal());
        response.put("data", result.getRecords());
        return response;
    }

    /**
     * 获取数据上报记录
     */
    @GetMapping("/dataReportRecords")
    @ResponseBody
    public Map<String, Object> getDataReportRecords(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {

        Page<DataReportRecord> pageInfo = new Page<>(page, limit);
        IPage<DataReportRecord> result = dataReportRecordMapper.selectPage(pageInfo,
                new LambdaQueryWrapper<DataReportRecord>()
                        .orderByDesc(DataReportRecord::getCreateTime));

        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("msg", "");
        response.put("count", result.getTotal());
        response.put("data", result.getRecords());
        return response;
    }

    /**
     * 获取文件上传记录
     */
    @GetMapping("/fileUploadRecords")
    @ResponseBody
    public Map<String, Object> getFileUploadRecords(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {

        Page<FileUploadRecord> pageInfo = new Page<>(page, limit);
        IPage<FileUploadRecord> result = fileUploadRecordMapper.selectPage(pageInfo,
                new LambdaQueryWrapper<FileUploadRecord>()
                        .orderByDesc(FileUploadRecord::getCreateTime));

        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("msg", "");
        response.put("count", result.getTotal());
        response.put("data", result.getRecords());
        return response;
    }

    /**
     * 获取通知下载记录
     */
    @GetMapping("/noticeDownloadRecords")
    @ResponseBody
    public Map<String, Object> getNoticeDownloadRecords(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {

        Page<NoticeDownloadRecord> pageInfo = new Page<>(page, limit);
        IPage<NoticeDownloadRecord> result = noticeDownloadRecordMapper.selectPage(pageInfo,
                new LambdaQueryWrapper<NoticeDownloadRecord>()
                        .orderByDesc(NoticeDownloadRecord::getCreateTime));

        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("msg", "");
        response.put("count", result.getTotal());
        response.put("data", result.getRecords());
        return response;
    }

    /**
     * 获取推送日志记录
     */
    @GetMapping("/pushLogRecords")
    @ResponseBody
    public Map<String, Object> getPushLogRecords(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {

        Page<PushLogRecord> pageInfo = new Page<>(page, limit);
        IPage<PushLogRecord> result = pushLogRecordMapper.selectPage(pageInfo,
                new LambdaQueryWrapper<PushLogRecord>()
                        .orderByDesc(PushLogRecord::getCreateTime));

        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("msg", "");
        response.put("count", result.getTotal());
        response.put("data", result.getRecords());
        return response;
    }

    /**
     * 获取统计数据
     */
    @GetMapping("/statistics")
    @ResponseBody
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // 同步记录统计
        Long syncTotal = syncRecordMapper.selectCount(null);
        Long syncSuccess = syncRecordMapper.selectCount(
                new LambdaQueryWrapper<SyncRecord>().eq(SyncRecord::getSyncStatus, 1));
        stats.put("syncTotal", syncTotal);
        stats.put("syncSuccess", syncSuccess);

        // 数据上报统计
        Long reportTotal = dataReportRecordMapper.selectCount(null);
        Long reportSuccess = dataReportRecordMapper.selectCount(
                new LambdaQueryWrapper<DataReportRecord>().eq(DataReportRecord::getIsReported, 1));
        stats.put("reportTotal", reportTotal);
        stats.put("reportSuccess", reportSuccess);

        // 文件上传统计
        Long uploadTotal = fileUploadRecordMapper.selectCount(null);
        Long uploadSuccess = fileUploadRecordMapper.selectCount(
                new LambdaQueryWrapper<FileUploadRecord>().eq(FileUploadRecord::getUploadStatus, 2));
        stats.put("uploadTotal", uploadTotal);
        stats.put("uploadSuccess", uploadSuccess);

        // 通知下载统计
        Long noticeTotal = noticeDownloadRecordMapper.selectCount(null);
        Long noticeSuccess = noticeDownloadRecordMapper.selectCount(
                new LambdaQueryWrapper<NoticeDownloadRecord>().eq(NoticeDownloadRecord::getDownloadStatus, 1));
        stats.put("noticeTotal", noticeTotal);
        stats.put("noticeSuccess", noticeSuccess);

        // 推送日志统计
        Long pushTotal = pushLogRecordMapper.selectCount(null);
        Long pushSuccess = pushLogRecordMapper.selectCount(
                new LambdaQueryWrapper<PushLogRecord>().eq(PushLogRecord::getRequestStatus, 1));
        stats.put("pushTotal", pushTotal);
        stats.put("pushSuccess", pushSuccess);

        return stats;
    }
}
