package com.company.datareport.scheduled;

import com.company.datareport.entity.sqlite.HadwnRestGathrTab;
import com.company.datareport.service.data.DataQueryService;
import com.company.datareport.service.data.DataReportService;
import com.company.datareport.service.sqlite.IHadwnRestGathrTabService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    @Autowired
    private DataQueryService dataQueryService;
    @Autowired
    private DataReportService dataReportService;
    @Autowired
    private IHadwnRestGathrTabService hadwnRestGathrTabService;
    public static List<String> tableList= List.of("SASAORGANIZATIONS","SASAOWNERSHIP","SASAEQUITYPART","SASAPERSONNEL","SASALAYERSTR");






    /**
     * 数据统计任务
     * 每天早上4点执行
     */
    @PostConstruct
    @Scheduled(cron = "${app.scheduler.data-upload-cron}")
    public void dailyDataStatistics() {
        String currentTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        log.info("========== 开始执行每日数据统计上报任务任务 - {} ==========", currentTime);

        try {
            LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
            log.info("统计日期: {}", yesterday.toLocalDate());

            int organizationCount=0;
            int ownershipCount=0;
            int equityPartCount=0;
            int personnelCount=0;
            int layerStrCount=0;
            tableList.forEach(table->{
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

            });
            //插入执行日志

            //封装上报数据

            //上报数据

            //获取上报反馈
            // TODO: 实现数据统计逻辑
            // 例如：
            // 1. 统计昨日数据上报量
            // 2. 统计接口调用次数
            // 3. 统计成功率
            // 4. 生成统计报表

            log.info("========== 每日数据统计任务执行完成 ==========");
        } catch (Exception e) {
            log.error("执行每日数据统计任务失败", e);
        }
    }


}
