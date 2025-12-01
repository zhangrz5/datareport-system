package com.company.datareport.service.sqlite.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.datareport.entity.sqlite.HadwnTempIdTab;
import com.company.datareport.mapper.sqlite.HadwnTempIdTabMapper;
import com.company.datareport.service.sqlite.IHadwnTempIdTabService;
import org.springframework.stereotype.Service;

/**
 * 模板ID表 服务实现类
 */
@Service
@DS("slave1")
public class HadwnTempIdTabServiceImpl extends ServiceImpl<HadwnTempIdTabMapper, HadwnTempIdTab> implements IHadwnTempIdTabService {

}
