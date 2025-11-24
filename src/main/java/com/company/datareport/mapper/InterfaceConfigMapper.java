package com.company.datareport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.datareport.entity.InterfaceConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 接口配置Mapper
 *
 * @author system
 * @since 2025-11-25
 */
@Mapper
public interface InterfaceConfigMapper extends BaseMapper<InterfaceConfig> {

    /**
     * 根据配置键获取配置值
     */
    @Select("SELECT config_value FROM t_interface_config WHERE config_key = #{configKey} AND status = 1 AND deleted = 0")
    String getConfigValue(@Param("configKey") String configKey);
}
