package com.company.datareport.service.sqlite;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.datareport.entity.sqlite.HadwnIndxTab;

import java.util.List;

/**
 * 指标表 服务接口
 */
public interface IHadwnIndxTabService extends IService<HadwnIndxTab> {
    public List<HadwnIndxTab> getHadwnIndxTabListByResId(String resId);

}
