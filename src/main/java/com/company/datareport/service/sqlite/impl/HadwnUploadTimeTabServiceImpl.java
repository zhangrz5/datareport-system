package com.company.datareport.service.sqlite.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.datareport.entity.sqlite.HadwnUploadTimeTab;
import com.company.datareport.mapper.sqlite.HadwnUploadTimeTabMapper;
import com.company.datareport.service.sqlite.IHadwnUploadTimeTabService;
import org.springframework.stereotype.Service;

/**
 * 上传时间表 服务实现类
 */
@Service
@DS("slave1")
public class HadwnUploadTimeTabServiceImpl extends ServiceImpl<HadwnUploadTimeTabMapper, HadwnUploadTimeTab> implements IHadwnUploadTimeTabService {

}
