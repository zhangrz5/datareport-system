package com.company.datareport.service.sqlite.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.datareport.entity.sqlite.HadwnRestGathrTab;
import com.company.datareport.mapper.sqlite.HadwnRestGathrTabMapper;
import com.company.datareport.service.sqlite.IHadwnRestGathrTabService;
import org.springframework.stereotype.Service;

/**
 * 数据采集表 服务实现类
 */
@Service
@DS("slave1")
public class HadwnRestGathrTabServiceImpl extends ServiceImpl<HadwnRestGathrTabMapper, HadwnRestGathrTab> implements IHadwnRestGathrTabService {

}
