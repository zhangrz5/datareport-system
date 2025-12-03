package com.company.datareport.scheduled;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ZipUtil;
import com.company.datareport.entity.sqlite.HadwnRestGathrTab;
import com.company.datareport.service.client.NoticeDownloadService;
import com.company.datareport.service.client.PlatformFileUploadService;
import com.company.datareport.service.client.PushLogQueryService;
import com.company.datareport.service.data.DataQueryService;
import com.company.datareport.service.data.DataReportService;
import com.company.datareport.service.sqlite.IHadwnRestGathrTabService;
import com.company.datareport.util.sm4.SM4FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 数据清理定时任务示例
 *
 * 功能说明:
 * 1. 定时清理过期的日志数据
 * 2. 清理临时文件
 * 3. 统计系统运行状态
 *
 * @author System
 * @date 2025-11-27
 */
@Slf4j
@Component
public class DataScheduledTask {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FILE_Date_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    @Autowired
    private DataQueryService dataQueryService;
    @Autowired
    private DataReportService dataReportService;
    @Autowired
    private IHadwnRestGathrTabService hadwnRestGathrTabService;
    @Autowired
    private PlatformFileUploadService platformFileUploadService;
    @Autowired
    private NoticeDownloadService noticeDownloadService;
    @Autowired
    private PushLogQueryService pushLogQueryService;

    public static List<String> tableList= List.of("SASAORGANIZATIONS","SASAOWNERSHIP","SASAEQUITYPART","SASAPERSONNEL","SASALAYERSTR");


    @Value("${app.sqlite.file-path}")
    private String sqliteFilePath;

    @Value("${app.upload.temp-path}")
    private String uploadTempPath;

    @Value("${app.upload.upload-path}")
    private String uploadPath;

    @Value("${app.corporate.code}")
    private String corporateCode;

    @Value("${app.platform.sm2-key}")
    private String sm2Key;

    @Value("${app.upload.report-config-path}")
    private String reportConfigPath;





    /**
     * 数据统计任务
     * 每天早上4点执行,同时初始化时过10秒执行一遍
     */
    @Scheduled(initialDelayString = "${app.scheduler.initial-delay:10000}", fixedDelay = Long.MAX_VALUE)
    @Scheduled(cron = "${app.scheduler.data-upload-cron:0 0 4 * * ?}")
    public void dailyDataStatistics() {
        String currentTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        log.info("========== 开始执行每日数据统计上报任务任务 - {} ==========", currentTime);

        try {
            LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
            log.info("统计日期: {}", yesterday.toLocalDate());
            //清除历史上报数据
            dataReportService.clearData();
            int totalCount= 0;
            //-----------------遍历5张表，获取和封装入库待上报数据---------------
            for (String table : tableList){
                log.info("开始获取{}表数据",table);
                Timestamp now=Timestamp.valueOf(LocalDateTime.now());
                //获取上次获取数据截止时间
                Timestamp lastTime=dataReportService.getLastTime(table);
                //获取截止到当前的数据
                List<HadwnRestGathrTab> hadwnRestGathrTabList=dataReportService.getAddData(table,lastTime,now);
                log.info("获取{}表数据{}条",table,hadwnRestGathrTabList.size());
                //保存数据
                hadwnRestGathrTabService.saveBatch(hadwnRestGathrTabList);
                log.info("保存{}表数据{}条",table,hadwnRestGathrTabList.size());
                //更新截止时间
                dataReportService.updateLastTime(table,now,hadwnRestGathrTabList.size());
                totalCount+=hadwnRestGathrTabList.size();
            }
            log.info("========== 新增待上报数据统计成功，共新增{}条数据 ==========", totalCount);
            //插入每日统计详情表
            dataReportService.insertDataReportRecord(totalCount);
            //无新增数据，不需上报，程序结束
            if (0==totalCount){
                log.info("========== 没有新增数据，无需上报,本次上报任务结束 ==========");
                log.info("========== 获取昨日数据推送日志 ==========");
                pushLogQueryService.queryLog(DateUtil.format(DateUtil.yesterday(), "yyyyMMdd"),DateUtil.format(DateUtil.date(), "yyyyMMdd"));
                return;
            }
            //--------------复制并压缩db文件，生成上报zip包------------
            String fileTime = LocalDateTime.now().format(FILE_Date_FORMATTER);
            String fileRuleName=corporateCode+"_0026_1001_"+fileTime+"_"+IdUtil.simpleUUID();
            String tmpDbName=fileRuleName+".db";
            //复制db文件
            String uploadDb=uploadTempPath+ File.separator+tmpDbName;
            String upLoadDbZip=uploadTempPath+ File.separator+fileRuleName+".zip";
            FileUtil.copy(sqliteFilePath, uploadDb, true);
            //封装上报数据,压缩，加密
            ZipUtil.zip(uploadDb,upLoadDbZip);
            SM4FileUtils.encrptReportZip(upLoadDbZip,sm2Key);
            //密钥、配置文件、数据新打包成最终zip包
            String finalZipName=uploadPath+ File.separator+fileRuleName+".zip";
            ZipUtil.zip(FileUtil.file(finalZipName),false,
                    //加密zip包
                    FileUtil.file(upLoadDbZip),
                    //密钥文件
                    FileUtil.file(uploadTempPath+ File.separator+fileRuleName+".key"),
                    //配置文件
                    FileUtil.file(reportConfigPath));
            log.info("========== 生成上报zip包，路径为{} ==========",finalZipName);

            //-----------------调用上报接口，上报数据---------------
            platformFileUploadService.uploadFile(finalZipName);

            //-----------------调用反馈接口，获取反馈---------------
            log.info("========== 开始异步轮询获取反馈结果，不影响主线程 ==========");
            noticeDownloadService.downloadAllNoticesAsync();

            //-----------------调用反馈日志接口，获取反馈日志---------------
            log.info("========== 获取昨日数据推送日志 ==========");
            pushLogQueryService.queryLog(DateUtil.format(DateUtil.yesterday(), "yyyyMMdd"),DateUtil.format(DateUtil.date(), "yyyyMMdd"));


            log.info("========== 每日数据统计任务执行完成 ==========");

        } catch (Exception e) {
            log.error("执行每日数据统计任务失败", e);
        }
    }


}
