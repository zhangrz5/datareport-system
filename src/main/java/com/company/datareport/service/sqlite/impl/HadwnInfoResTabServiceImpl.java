package com.company.datareport.service.sqlite.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.datareport.entity.sqlite.HadwnInfoResTab;
import com.company.datareport.mapper.sqlite.HadwnInfoResTabMapper;
import com.company.datareport.service.sqlite.IHadwnInfoResTabService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 信息资源表 服务实现类
 */
@Service
@DS("slave1")
public class HadwnInfoResTabServiceImpl extends ServiceImpl<HadwnInfoResTabMapper, HadwnInfoResTab> implements IHadwnInfoResTabService {

}
