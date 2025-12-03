# 国资国企数据采集上报系统

## 项目简介

基于 Spring Boot + MyBatis-Plus 的多数据源数据采集上报系统,用于国资国企在线监管系统的数据采集、处理和上报。

## 📚 文档导航



## 技术栈


- **核心框架**: Spring Boot 3.3.5
- **JDK 版本**: JDK 17
- **数据库**: 达梦数据库(DM) / PostgreSQL / MySQL (多数据源支持)
- **ORM框架**: MyBatis-Plus 3.5.9
- **多数据源**: Dynamic-Datasource 4.3.1
- **连接池**: Druid 1.2.23
- **缓存**: Redis + Redisson 3.36.0
- **工具类**: Hutool 5.8.25
- **JSON处理**: FastJSON2 2.0.43
- **Excel处理**: Apache POI 5.2.5
- **日志**: Logback
- **其他**: Lombok, Spring Retry


## 主要功能

### 国资委数据采集交换平台接口
- **数据报送接口** - 向前置服务器上传加密ZIP文件
- **密钥证书接口** - 下载SM2/SM4密钥文件
- **数据采集目录接口** - 下载采集模板和示例文件
- **接收采集任务接口** - 接收补传任务
- **接收下发数据接口** - 接收通知公告
- **数据日志接口** - 同步远程日志记录
- 统一日志记录和配置管理
- 支持安徽模式和标准模式

## 项目结构

```
datareport-system
├── src/main/java/com/company/datareport/
│   ├── common/                     # 公共模块
│   │   ├── dto/                   # 数据传输对象
│   │   │   ├── InterfaceResponse.java    # 接口统一响应
│   │   │   └── LogDownloadDTO.java       # 日志下载DTO
│   │   ├── exception/             # 异常处理
│   │   │   ├── BusinessException.java    # 业务异常
│   │   │   └── GlobalExceptionHandler.java  # 全局异常处理器
│   │   └── result/                # 响应结果
│   │       └── Result.java        # 统一响应结果类
│   ├── config/                    # 配置类
│   │   ├── MybatisPlusConfig.java        # MyBatis-Plus配置
│   │   ├── MyMetaObjectHandler.java      # 字段自动填充
│   │   ├── RedisConfig.java              # Redis配置
│   │   └── RestTemplateConfig.java       # RestTemplate配置
│   ├── entity/                    # 实体类
│   │   ├── InterfaceLog.java             # 接口日志实体
│   │   ├── FileUploadRecord.java         # 文件上传记录
│   │   ├── KeyCertificate.java           # 密钥证书
│   │   ├── TemplateInfo.java             # 模板信息
│   │   ├── SupplementTask.java           # 补传任务
│   │   ├── NoticeAnnouncement.java       # 通知公告
│   │   ├── RemoteLogSync.java            # 远程日志同步
│   │   ├── BusinessType.java             # 业务类型
│   │   └── InterfaceConfig.java          # 接口配置
│   ├── mapper/                    # Mapper接口
│   │   ├── InterfaceLogMapper.java
│   │   ├── FileUploadRecordMapper.java
│   │   ├── KeyCertificateMapper.java
│   │   ├── TemplateInfoMapper.java
│   │   ├── SupplementTaskMapper.java
│   │   ├── NoticeAnnouncementMapper.java
│   │   └── RemoteLogSyncMapper.java
│   ├── service/                   # 服务接口
│   │   ├── InterfaceConfigService.java
│   │   ├── InterfaceLogService.java
│   │   └── impl/                  # 服务实现类
│   ├── controller/                # 控制器
│   │   └── PreposedInterfaceController.java  # 国资委接口控制器
│   └── DataReportApplication.java # 启动类
├── src/main/resources/
│   ├── sql/                       # 数据库脚本
│   │   ├── schema-interface.sql          # MySQL初始化脚本
│   │   └── schema-interface-dm.sql       # 达梦数据库初始化脚本
│   ├── application.yml           # 主配置文件
│   ├── application-dev.yml       # 开发环境配置
│   └── application-prod.yml      # 生产环境配置
└── pom.xml                       # Maven配置
```

## 数据库设计

### 国资委接口相关表
- **t_interface_log** - 统一接口调用日志表
- **t_file_upload_record** - 数据报送记录表
- **t_key_certificate** - 密钥证书表
- **t_business_type** - 业务类型表
- **t_template_info** - 采集模板信息表
- **t_template_frequency** - 模板采集频率表
- **t_supplement_task** - 补传任务表
- **t_notice_announcement** - 通知公告表
- **t_remote_log_sync** - 远程日志同步表
- **t_interface_config** - 接口配置表

## 配置说明

### 1. 数据源配置

支持多数据源,在 `application.yml` 中配置:

```yaml
spring:
  datasource:
    dynamic:
      primary: master  # 默认数据源
      datasource:
        master:        # 主数据源(达梦数据库)
          driver-class-name: dm.jdbc.driver.DmDriver
          url: jdbc:dm://localhost:5236/datareport
          username: SYSDBA
          password: SYSDBA
        slave1:        # 从数据源1(MySQL)
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/enterprise_db
          username: root
          password: root
        slave2:        # 从数据源2(PostgreSQL)
          driver-class-name: org.postgresql.Driver
          url: jdbc:postgresql://localhost:5432/external_db
          username: postgres
          password: postgres
```

### 2. 监管平台配置

```yaml
app:
  platform:
    task-list-url: http://platform.example.com/api/tasks  # 获取任务API
    upload-url: http://platform.example.com/api/upload    # 上报数据API
    enterprise-code: ENTERPRISE_001                        # 企业编码
    auth-key: your-auth-key                               # 认证密钥
```

### 3. 定时任务配置

```yaml
app:
  scheduler:
    enabled: true                              # 是否启用定时任务
    task-fetch-cron: 0 0 1 * * ?              # 任务拉取(每天1点)
    data-upload-cron: 0 0 3 * * ?             # 数据上报(每天3点)
```

## 🔄 升级说明

**重要提示**: 本项目已从 Spring Boot 2.7.18 + JDK 8 升级到 Spring Boot 3.3.5 + JDK 17。

如果您是从旧版本升级，请务必查看 [UPGRADE_GUIDE.md](./UPGRADE_GUIDE.md) 了解详细的升级步骤和注意事项。

主要变更：
- JDK 版本从 1.8 升级到 17（必需）
- Spring Boot 从 2.7.18 升级到 3.3.5
- javax.* 包迁移到 jakarta.*
- 依赖版本全面更新以兼容 Spring Boot 3

## 快速开始

### 1. 环境要求

- JDK 17+
- Maven 3.6+
- 达梦数据库 DM8+ (主数据源) / PostgreSQL 12+ / MySQL 8.0+ (从数据源)
- Redis 5.0+

### 2. 数据库初始化

#### 达梦数据库(推荐)

```bash
# 创建数据库
CREATE DATABASE datareport;

# 使用 disql 命令行工具执行初始化脚本
disql SYSDBA/SYSDBA@localhost:5236 < src/main/resources/sql/schema-interface-dm.sql
```

或在达梦数据库管理工具(DM Manager)中执行 `src/main/resources/sql/schema-interface-dm.sql`

#### MySQL

```bash
# 创建数据库
CREATE DATABASE datareport DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 执行初始化脚本
mysql -u root -p datareport < src/main/resources/sql/schema-interface.sql
```

### 3. 修改配置

编辑 `src/main/resources/application-dev.yml`,修改数据库和Redis连接信息。

### 4. 启动项目

```bash
# 开发环境
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# 或打包后运行
mvn clean package
java -jar target/datareport-system-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev
```

### 5. 访问接口

启动成功后,访问: http://localhost:8080/api

## API接口

### 国资委数据采集交换平台接口

```
POST   /preposed-machine/api/services/fileUpload       # 数据报送接口
GET    /preposed-machine/api/services/keyDownload      # 密钥证书下载
GET    /preposed-machine/api/services/tempDownload     # 采集目录下载
GET    /preposed-machine/api/services/taskDownload     # 采集任务下载
GET    /preposed-machine/api/services/noticeDownload   # 通知公告下载
GET    /preposed-machine/api/services/logDownload      # 数据日志下载
```

**详细说明**: 请查看 [接口实现文档](./INTERFACE_IMPLEMENTATION.md)

## 注意事项

1. **达梦数据库配置**: 详细配置说明请参考 [达梦数据库配置指南](./DM_DATABASE_GUIDE.md)
2. **接口认证**: 所有接口都需要USER和PASSWORD参数进行身份验证
3. **文件格式**: 数据报送接口要求ZIP格式的加密文件
4. **日志管理**: 所有接口调用都会记录到t_interface_log表
5. **安徽模式**: 通知下载接口支持安徽模式，需要额外的SYSCODE和BUSTYPE参数
6. **监控告警**: 生产环境建议配置日志监控和异常告警
7. **数据安全**: 敏感信息(密码、密钥)应使用加密存储

## 常见问题

### 1. 数据库连接失败

检查数据库连接配置是否正确,确认数据库驱动版本匹配。

### 2. 接口认证失败

检查 `t_interface_config` 表中的用户名密码配置是否正确。

### 3. 文件下载返回空

检查对应的数据表(t_key_certificate、t_template_info等)是否有未下载的记录。

## 许可证

本项目仅供学习交流使用。

## 联系方式

- 作者: qwe
- 日期: 2025-01-24
