package com.company.datareport.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.datareport.entity.system.DataReportRecord;
import com.company.datareport.mapper.system.DataReportRecordMapper;
import com.company.datareport.service.system.DataReportRecordService;
import org.springframework.stereotype.Service;

/**
 * @author 87795
 */
@Service
public class DataReportRecordServiceImpl extends ServiceImpl<DataReportRecordMapper, DataReportRecord> implements DataReportRecordService {
}
