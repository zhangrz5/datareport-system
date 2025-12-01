package com.company.datareport.service.data;

import com.company.datareport.entity.sqlite.HadwnIndxTab;
import com.company.datareport.entity.sqlite.HadwnRestGathrTab;
import com.company.datareport.service.sqlite.IHadwnIndxTabService;
import com.company.datareport.util.SqlHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataReportService {
    private final DataQueryService dataQueryService;
    private final SqlHelper sqlHelper;
    private final IHadwnIndxTabService hadwnIndxTabService;

    public static List<String> tableList= List.of("SASAORGANIZATIONS","SASAOWNERSHIP","SASAEQUITYPART","SASAPERSONNEL","SASALAYERSTR");
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Value("${app.corporate.name}")
    private String corporateName;
    @Value("${app.corporate.code}")
    private String corporateCode;


/**
 * 获取指定表的最后同步时间
 * @param tableName 需要查询的表名
 * @return 返回该表的最后同步时间，类型为Timestamp
 */
    public Timestamp getLastTime(String tableName){
        // 从SQL配置中获取查询最后时间的SQL语句
        String sql = sqlHelper.getSql("query", "dataTask", "getLastTime");
        // 执行SQL查询并获取结果
        Map<String, Object> result=dataQueryService.executeQueryOne(sql,tableName);
        // 从结果中获取SYNC_TIME字段的值并返回
        return (Timestamp) result.get("SYNC_TIME");
    }

    public int updateLastTime(String tableName,Timestamp lastTime,int size){
        String sql = sqlHelper.getSql("update", "dataTask", "updateLastTime");
        return dataQueryService.executeUpdate(sql,tableName,lastTime,size);
    }

    public List<HadwnRestGathrTab> getAddData(String tableName,Timestamp lastTime,Timestamp nowTime){
        String sql = sqlHelper.getSql("query", "dataTask", tableName);
        List<Map<String, Object>> list=dataQueryService.executeQuery(sql,lastTime,nowTime);
        List<HadwnRestGathrTab> hadwnRestGathrTabList=transformData(list,tableName);
        return hadwnRestGathrTabList;
    }


    private List<HadwnRestGathrTab>  transformData(List<Map<String, Object>> list,String tableName) {
        List<HadwnRestGathrTab> hadwnRestGathrTabList=new ArrayList<>();
        switch (tableName) {
            case "SASAORGANIZATIONS":
                list.forEach(item -> {
                    //主表的主键是
                    String primaryKeyValue = (String) item.get("ID");
                    Timestamp createdTime = (Timestamp) item.get("TIMESTAMP_CREATEDON");
                    Timestamp lastChangedTime = (Timestamp) item.get("TIMESTAMP_LASTCHANGEDON");
                    //取DATASTATE的状态判断是原有、新增、删除等
                    String status= (String) item.get("DATASTATE");
                    //获取结果数据对应的字段信息
                    List<HadwnIndxTab> hadwnIndxTabList=hadwnIndxTabService.getHadwnIndxTabListByResId("d1b3b72a-1da4-42b2-b2b4-3e509612c11c");
                    //多多次遍历，将list转map
                    Map<String, HadwnIndxTab> hadwnIndxTabMap = hadwnIndxTabList.stream()
                            .filter(hadwnIndxTab -> hadwnIndxTab.getIndxCode() != null)  // 过滤掉 null
                            .collect(Collectors.toMap(
                                    hadwnIndxTab -> hadwnIndxTab.getIndxCode().toUpperCase(),  // 字段全部转大写
                                    hadwnIndxTab -> hadwnIndxTab
                            ));
                    // 定义要排除上报的数据
                    Set<String> excludedKeys = new HashSet<>(Arrays.asList("TIMESTAMP_CREATEDON", "TIMESTAMP_LASTCHANGEDON", "DATASTATE"));
                    item.entrySet().stream().filter(entry->!excludedKeys.contains(entry.getKey())).forEach(entry -> {
                        HadwnRestGathrTab hadwnRestGathrTab= new HadwnRestGathrTab();
                        hadwnRestGathrTab.setCorpCd(corporateCode);
                        hadwnRestGathrTab.setCorpNm(corporateName);
                        hadwnRestGathrTab.setInfoResId("d1b3b72a-1da4-42b2-b2b4-3e509612c11c");

                        String indexCode=entry.getKey();
                        hadwnRestGathrTab.setIndxId(hadwnIndxTabMap.get(indexCode).getIndxId());
                        hadwnRestGathrTab.setIndxNm(hadwnIndxTabMap.get(indexCode).getIndxNm());
                        hadwnRestGathrTab.setDataContt(entry.getValue().toString());
                        hadwnRestGathrTab.setLinFlag(primaryKeyValue);
                        if(StringUtils.isEmpty(status)){
                            hadwnRestGathrTab.setDataFlag(0);
                            log.error("{}表状态字段为空",tableName);
                        }
                        hadwnRestGathrTab.setDataFlag(Integer.parseInt(status));
                        hadwnRestGathrTab.setSetupTm(formatter.format(createdTime.toLocalDateTime()));
                        hadwnRestGathrTab.setUpdTm(formatter.format(lastChangedTime.toLocalDateTime()));
                        hadwnRestGathrTabList.add(hadwnRestGathrTab);
                    });
                });
                return hadwnRestGathrTabList;
            case "SASAOWNERSHIP":
                list.forEach(item -> {
                    //主表的主键是
                    String primaryKeyValue = (String) item.get("ID");
                    Timestamp createdTime = (Timestamp) item.get("TIMESTAMP_CREATEDON");
                    Timestamp lastChangedTime = (Timestamp) item.get("TIMESTAMP_LASTCHANGEDON");
                    //取DATASTATE的状态判断是原有、新增、删除等
                    String status= (String) item.get("DATASTATE");
                    //获取结果数据对应的字段信息
                    List<HadwnIndxTab> hadwnIndxTabList=hadwnIndxTabService.getHadwnIndxTabListByResId("c10f37c5-e26d-4d59-bf89-f324d7f202b3");
                    //多多次遍历，将list转map
                    Map<String, HadwnIndxTab> hadwnIndxTabMap = hadwnIndxTabList.stream()
                            .filter(hadwnIndxTab -> hadwnIndxTab.getIndxCode() != null)  // 过滤掉 null
                            .collect(Collectors.toMap(
                                    hadwnIndxTab -> hadwnIndxTab.getIndxCode().toUpperCase(),  // 字段全部转大写
                                    hadwnIndxTab -> hadwnIndxTab
                            ));
                    // 定义要排除上报的数据
                    Set<String> excludedKeys = new HashSet<>(Arrays.asList("TIMESTAMP_CREATEDON", "TIMESTAMP_LASTCHANGEDON", "DATASTATE"));
                    item.entrySet().stream().filter(entry->!excludedKeys.contains(entry.getKey())).forEach(entry -> {
                        HadwnRestGathrTab hadwnRestGathrTab= new HadwnRestGathrTab();
                        hadwnRestGathrTab.setCorpCd(corporateCode);
                        hadwnRestGathrTab.setCorpNm(corporateName);
                        hadwnRestGathrTab.setInfoResId("c10f37c5-e26d-4d59-bf89-f324d7f202b3");
                        String indexCode=entry.getKey();
                        hadwnRestGathrTab.setIndxId(hadwnIndxTabMap.get(indexCode).getIndxId());
                        hadwnRestGathrTab.setIndxNm(hadwnIndxTabMap.get(indexCode).getIndxNm());
                        hadwnRestGathrTab.setDataContt(entry.getValue().toString());
                        hadwnRestGathrTab.setLinFlag(primaryKeyValue);
                        if(StringUtils.isEmpty(status)){
                            hadwnRestGathrTab.setDataFlag(0);
                            log.error("{}表状态字段为空",tableName);
                        }
                        hadwnRestGathrTab.setDataFlag(Integer.parseInt(status));
                        hadwnRestGathrTab.setSetupTm(formatter.format(createdTime.toLocalDateTime()));
                        hadwnRestGathrTab.setUpdTm(formatter.format(lastChangedTime.toLocalDateTime()));
                        hadwnRestGathrTabList.add(hadwnRestGathrTab);
                    });
                });
                return hadwnRestGathrTabList;
            case "SASAEQUITYPART":
                list.forEach(item -> {
                    //主表的主键是
                    String primaryKeyValue = (String) item.get("ID");
                    Timestamp createdTime = (Timestamp) item.get("TIMESTAMP_CREATEDON");
                    Timestamp lastChangedTime = (Timestamp) item.get("TIMESTAMP_LASTCHANGEDON");
                    //取DATASTATE的状态判断是原有、新增、删除等
                    String status= (String) item.get("DATASTATE");
                    //获取结果数据对应的字段信息
                    List<HadwnIndxTab> hadwnIndxTabList=hadwnIndxTabService.getHadwnIndxTabListByResId("6b0bec46-3105-4af8-9e58-e8fd273c2073");
                    //多多次遍历，将list转map
                    Map<String, HadwnIndxTab> hadwnIndxTabMap = hadwnIndxTabList.stream()
                            .filter(hadwnIndxTab -> hadwnIndxTab.getIndxCode() != null)  // 过滤掉 null
                            .collect(Collectors.toMap(
                                    hadwnIndxTab -> hadwnIndxTab.getIndxCode().toUpperCase(),  // 字段全部转大写
                                    hadwnIndxTab -> hadwnIndxTab
                            ));
                    // 定义要排除上报的数据
                    Set<String> excludedKeys = new HashSet<>(Arrays.asList("TIMESTAMP_CREATEDON", "TIMESTAMP_LASTCHANGEDON", "DATASTATE"));
                    item.entrySet().stream().filter(entry->!excludedKeys.contains(entry.getKey())).forEach(entry -> {
                        HadwnRestGathrTab hadwnRestGathrTab= new HadwnRestGathrTab();
                        hadwnRestGathrTab.setCorpCd(corporateCode);
                        hadwnRestGathrTab.setCorpNm(corporateName);
                        hadwnRestGathrTab.setInfoResId("6b0bec46-3105-4af8-9e58-e8fd273c2073");
                        String indexCode=entry.getKey();
                        hadwnRestGathrTab.setIndxId(hadwnIndxTabMap.get(indexCode).getIndxId());
                        hadwnRestGathrTab.setIndxNm(hadwnIndxTabMap.get(indexCode).getIndxNm());
                        hadwnRestGathrTab.setDataContt(entry.getValue().toString());
                        hadwnRestGathrTab.setLinFlag(primaryKeyValue);
                        if(StringUtils.isEmpty(status)){
                            hadwnRestGathrTab.setDataFlag(0);
                            log.error("{}表状态字段为空",tableName);
                        }
                        hadwnRestGathrTab.setDataFlag(Integer.parseInt(status));
                        hadwnRestGathrTab.setSetupTm(formatter.format(createdTime.toLocalDateTime()));
                        hadwnRestGathrTab.setUpdTm(formatter.format(lastChangedTime.toLocalDateTime()));
                        hadwnRestGathrTabList.add(hadwnRestGathrTab);
                    });
                });
                return hadwnRestGathrTabList;
            case "SASAPERSONNEL":
                list.forEach(item -> {
                    //主表的主键是
                    String primaryKeyValue = (String) item.get("ID");
                    Timestamp createdTime = (Timestamp) item.get("TIMESTAMP_CREATEDON");
                    Timestamp lastChangedTime = (Timestamp) item.get("TIMESTAMP_LASTCHANGEDON");
                    //取DATASTATE的状态判断是原有、新增、删除等
                    String status= (String) item.get("DATASTATE");
                    //获取结果数据对应的字段信息
                    List<HadwnIndxTab> hadwnIndxTabList=hadwnIndxTabService.getHadwnIndxTabListByResId("16c100b2-c1a9-4e8a-94fc-433831175432");
                    //多多次遍历，将list转map
                    Map<String, HadwnIndxTab> hadwnIndxTabMap = hadwnIndxTabList.stream()
                            .filter(hadwnIndxTab -> hadwnIndxTab.getIndxCode() != null)  // 过滤掉 null
                            .collect(Collectors.toMap(
                                    hadwnIndxTab -> hadwnIndxTab.getIndxCode().toUpperCase(),  // 字段全部转大写
                                    hadwnIndxTab -> hadwnIndxTab
                            ));
                    // 定义要排除上报的数据
                    Set<String> excludedKeys = new HashSet<>(Arrays.asList("TIMESTAMP_CREATEDON", "TIMESTAMP_LASTCHANGEDON", "DATASTATE"));
                    item.entrySet().stream().filter(entry->!excludedKeys.contains(entry.getKey())).forEach(entry -> {
                        HadwnRestGathrTab hadwnRestGathrTab= new HadwnRestGathrTab();
                        hadwnRestGathrTab.setCorpCd(corporateCode);
                        hadwnRestGathrTab.setCorpNm(corporateName);
                        hadwnRestGathrTab.setInfoResId("16c100b2-c1a9-4e8a-94fc-433831175432");
                        String indexCode=entry.getKey();
                        hadwnRestGathrTab.setIndxId(hadwnIndxTabMap.get(indexCode).getIndxId());
                        hadwnRestGathrTab.setIndxNm(hadwnIndxTabMap.get(indexCode).getIndxNm());
                        hadwnRestGathrTab.setDataContt(entry.getValue().toString());
                        hadwnRestGathrTab.setLinFlag(primaryKeyValue);
                        if(StringUtils.isEmpty(status)){
                            hadwnRestGathrTab.setDataFlag(0);
                            log.error("{}表状态字段为空",tableName);
                        }
                        hadwnRestGathrTab.setDataFlag(Integer.parseInt(status));
                        hadwnRestGathrTab.setSetupTm(formatter.format(createdTime.toLocalDateTime()));
                        hadwnRestGathrTab.setUpdTm(formatter.format(lastChangedTime.toLocalDateTime()));
                        hadwnRestGathrTabList.add(hadwnRestGathrTab);
                    });
                });
                return hadwnRestGathrTabList;
            case "SASALAYERSTR":
                //注意这个表没有更新状态字段，所有状态默认都是新增
                list.forEach(item -> {
                    //主表的主键是
                    String primaryKeyValue = (String) item.get("ID");
                    Timestamp createdTime = (Timestamp) item.get("TIMESTAMP_CREATEDON");
                    Timestamp lastChangedTime = (Timestamp) item.get("TIMESTAMP_LASTCHANGEDON");
                    //取DATASTATE的状态判断是原有、新增、删除等，该表无状态字段
                    //String status= (String) item.get("DATASTATE");
                    //获取结果数据对应的字段信息
                    List<HadwnIndxTab> hadwnIndxTabList=hadwnIndxTabService.getHadwnIndxTabListByResId("26abe0e2-dac5-43f8-a0d1-51b26cec219e");
                    //多多次遍历，将list转map
                    Map<String, HadwnIndxTab> hadwnIndxTabMap = hadwnIndxTabList.stream()
                            .filter(hadwnIndxTab -> hadwnIndxTab.getIndxCode() != null)  // 过滤掉 null
                            .collect(Collectors.toMap(
                                    hadwnIndxTab -> hadwnIndxTab.getIndxCode().toUpperCase(),  // 字段全部转大写
                                    hadwnIndxTab -> hadwnIndxTab
                            ));
                    // 定义要排除上报的数据
                    Set<String> excludedKeys = new HashSet<>(Arrays.asList("TIMESTAMP_CREATEDON", "TIMESTAMP_LASTCHANGEDON", "DATASTATE"));
                    item.entrySet().stream().filter(entry->!excludedKeys.contains(entry.getKey())).forEach(entry -> {
                        HadwnRestGathrTab hadwnRestGathrTab= new HadwnRestGathrTab();
                        hadwnRestGathrTab.setCorpCd(corporateCode);
                        hadwnRestGathrTab.setCorpNm(corporateName);
                        hadwnRestGathrTab.setInfoResId("26abe0e2-dac5-43f8-a0d1-51b26cec219e");
                        String indexCode=entry.getKey();
                        hadwnRestGathrTab.setIndxId(hadwnIndxTabMap.get(indexCode).getIndxId());
                        hadwnRestGathrTab.setIndxNm(hadwnIndxTabMap.get(indexCode).getIndxNm());
                        hadwnRestGathrTab.setDataContt(entry.getValue().toString());
                        hadwnRestGathrTab.setLinFlag(primaryKeyValue);
                        hadwnRestGathrTab.setDataFlag(0);
                        //源数据中无状态字段，所有默认新增
                        hadwnRestGathrTab.setDataFlag(1);
                        hadwnRestGathrTab.setSetupTm(formatter.format(createdTime.toLocalDateTime()));
                        hadwnRestGathrTab.setUpdTm(formatter.format(lastChangedTime.toLocalDateTime()));
                        hadwnRestGathrTabList.add(hadwnRestGathrTab);
                    });
                });
                return hadwnRestGathrTabList;
            default:
                return null;

        }
    }



}
