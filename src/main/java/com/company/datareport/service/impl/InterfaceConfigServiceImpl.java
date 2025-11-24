package com.company.datareport.service.impl;

import com.company.datareport.mapper.InterfaceConfigMapper;
import com.company.datareport.service.InterfaceConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 接口配置服务实现
 *
 * @author system
 * @since 2025-11-25
 */
@Slf4j
@Service
public class InterfaceConfigServiceImpl implements InterfaceConfigService {

    @Autowired
    private InterfaceConfigMapper configMapper;

    @Override
    @Cacheable(value = "interfaceConfig", key = "#configKey")
    public String getConfigValue(String configKey) {
        return configMapper.getConfigValue(configKey);
    }

    @Override
    public String getConfigValue(String configKey, String defaultValue) {
        String value = getConfigValue(configKey);
        return StringUtils.hasText(value) ? value : defaultValue;
    }

    @Override
    public String getPreposedServerHost() {
        return getConfigValue("preposed.server.host", "http://localhost:8080");
    }

    @Override
    public String getApiUsername() {
        return getConfigValue("preposed.api.username", "admin");
    }

    @Override
    public String getApiPassword() {
        return getConfigValue("preposed.api.password", "admin123");
    }

    @Override
    public String getSocialCreditCode() {
        return getConfigValue("preposed.social.credit.code", "");
    }

    @Override
    public String getFileUploadPath() {
        return getConfigValue("preposed.file.upload.path", "/data/upload");
    }

    @Override
    public String getFileDownloadPath() {
        return getConfigValue("preposed.file.download.path", "/data/download");
    }

    @Override
    public boolean isAhModeEnabled() {
        String status = getConfigValue("preposed.ah.status", "0");
        return "1".equals(status);
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        String configUsername = getApiUsername();
        String configPassword = getApiPassword();

        return configUsername.equals(username) && configPassword.equals(password);
    }
}
