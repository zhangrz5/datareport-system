package com.company.datareport.service.system;

/**
 * 接口配置服务
 *
 * @author system
 * @since 2025-11-25
 */
public interface InterfaceConfigService {

    /**
     * 根据配置键获取配置值
     */
    String getConfigValue(String configKey);

    /**
     * 根据配置键获取配置值,如果不存在则返回默认值
     */
    String getConfigValue(String configKey, String defaultValue);

    /**
     * 获取前置服务器地址
     */
    String getPreposedServerHost();

    /**
     * 获取API用户名
     */
    String getApiUsername();

    /**
     * 获取API密码
     */
    String getApiPassword();

    /**
     * 获取社会信用代码
     */
    String getSocialCreditCode();

    /**
     * 获取文件上传路径
     */
    String getFileUploadPath();

    /**
     * 获取文件下载路径
     */
    String getFileDownloadPath();

    /**
     * 是否启用安徽模式
     */
    boolean isAhModeEnabled();

    /**
     * 验证用户名密码
     */
    boolean validateCredentials(String username, String password);
}
