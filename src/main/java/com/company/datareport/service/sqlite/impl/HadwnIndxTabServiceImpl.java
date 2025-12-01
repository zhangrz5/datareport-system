package com.company.datareport.service.sqlite.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.company.datareport.entity.sqlite.HadwnIndxTab;
import com.company.datareport.entity.sqlite.HadwnInfoResTab;
import com.company.datareport.mapper.sqlite.HadwnIndxTabMapper;
import com.company.datareport.service.sqlite.IHadwnIndxTabService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 指标表 服务实现类
 */
@Service
@DS("slave1")
public class HadwnIndxTabServiceImpl extends ServiceImpl<HadwnIndxTabMapper, HadwnIndxTab> implements IHadwnIndxTabService {
/**
 * 根据资源ID查询硬件指标列表
 * 该方法重写了父类的接口实现，用于查询指定资源ID对应的硬件指标信息
 *
 * @param resId 资源ID，用于筛选特定的硬件指标记录
 * @return 返回符合条件的硬件指标列表，如果没有记录则返回空列表
 */
    @Override
    public List<HadwnIndxTab>  getHadwnIndxTabListByResId(String resId) {
    // 创建Lambda查询包装器，用于构建查询条件
        LambdaQueryWrapper<HadwnIndxTab> lambdaQueryWrapper= new LambdaQueryWrapper<>();
    // 设置查询条件：查询infoResId字段等于传入的resId的记录
        lambdaQueryWrapper.eq(HadwnIndxTab::getInfoResId,resId);
    // 执行查询并返回结果列表
        return this.baseMapper.selectList(lambdaQueryWrapper);
    }

}
