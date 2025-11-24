# 国资国企数据采集上报系统

## 项目简介

基于 Spring Boot + MyBatis-Plus 的多数据源数据采集上报系统,用于国资国企在线监管系统的数据采集、处理和上报。

## 📚 文档导航

### 基础文档
- **[快速启动指南](./QUICK_START.md)** - 5分钟快速启动项目
- **[达梦数据库配置指南](./DM_DATABASE_GUIDE.md)** - 达梦数据库详细配置说明
- **[数据库迁移总结](./DM_MIGRATION_SUMMARY.md)** - 达梦数据库迁移记录
- **[数据库对比指南](./DATABASE_COMPARISON.md)** - 三种数据库对比与选择
- **[Redis使用说明](./REDIS_USAGE.md)** - Redis的作用和使用场景
- **[升级指南](./UPGRADE_GUIDE.md)** - Spring Boot 2 升级到 3 的指南

### 国资委接口文档 🆕
- **[接口实现文档](./INTERFACE_IMPLEMENTATION.md)** - 6个接口的详细说明和使用指南
- **[实现总结](./IMPLEMENTATION_SUMMARY.md)** - 已完成和待完成功能总结
- **[接口快速开始](./QUICK_START.md)** - 接口测试和使用指南
- **[SQL查询手册](./SQL_QUERIES.sql)** - 常用SQL查询语句
- **[Postman测试集合](./postman_collection.json)** - 可导入Postman的接口测试集合

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

### 1. 任务管理
- 从监管平台获取采集任务
- 任务状态管理(待处理/处理中/已完成/失败)
- 任务执行与调度
- 任务查询与统计

### 2. 数据采集
- **多数据源支持**
  - 本地数据库采集
  - 企业系统数据库采集
  - 外部接口采集
  - Excel文件导入
- 动态SQL执行
- 数据转换与处理

### 3. 数据上报
- 单条数据上报
- 批量数据上报
- 失败重试机制
- 上报记录追踪

### 4. 定时任务
- 定时拉取任务
- 定时执行任务
- 定时上报数据
- 定时重试失败数据

### 5. 国资委数据采集交换平台接口 🆕
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
│   │   ├── constants/             # 常量定义
│   │   │   ├── BusinessConstants.java    # 业务常量
│   │   │   └── DataSourceConstants.java  # 数据源常量
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
│   │   ├── Task.java              # 任务实体
│   │   ├── TaskData.java          # 任务数据实体
│   │   └── UploadRecord.java      # 上报记录实体
│   ├── mapper/                    # Mapper接口
│   │   ├── TaskMapper.java
│   │   ├── TaskDataMapper.java
│   │   └── UploadRecordMapper.java
│   ├── service/                   # 服务接口
│   │   ├── TaskService.java
│   │   ├── DataCollectService.java
│   │   ├── DataUploadService.java
│   │   └── impl/                  # 服务实现类
│   ├── controller/                # 控制器
│   │   ├── TaskController.java
│   │   └── DataController.java
│   ├── scheduled/                 # 定时任务
│   │   └── TaskScheduler.java
│   └── DataReportApplication.java # 启动类
├── src/main/resources/
│   ├── mapper/                    # Mapper XML文件
│   ├── sql/                       # 数据库脚本
│   │   └── schema.sql            # 初始化脚本
│   ├── application.yml           # 主配置文件
│   ├── application-dev.yml       # 开发环境配置
│   └── application-prod.yml      # 生产环境配置
└── pom.xml                       # Maven配置
```

## 数据库设计

### 1. t_task (任务表)
- 存储从平台获取的采集任务信息
- 记录任务状态、执行时间、采集规则等

### 2. t_task_data (任务数据表)
- 存储采集到的业务数据
- 记录数据状态、上报状态等

### 3. t_upload_record (上报记录表)
- 存储数据上报的详细记录
- 记录请求参数、响应结果、耗时等

### 4. 国资委接口相关表 🆕
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
disql SYSDBA/SYSDBA@localhost:5236 < src/main/resources/sql/schema-dm.sql
```

或在达梦数据库管理工具(DM Manager)中执行 `src/main/resources/sql/schema-dm.sql`

#### PostgreSQL

```bash
# 创建数据库
CREATE DATABASE datareport;

# 执行初始化脚本
psql -U postgres -d datareport -f src/main/resources/sql/schema-postgresql.sql
```

#### MySQL

```bash
# 创建数据库
CREATE DATABASE datareport DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 执行初始化脚本
mysql -u root -p datareport < src/main/resources/sql/schema-mysql.sql
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

### 任务管理

```
POST   /api/task/fetch                # 手动拉取任务
GET    /api/task/list                 # 查询任务列表(分页)
GET    /api/task/{id}                 # 查询任务详情
GET    /api/task/pending              # 查询待处理任务
POST   /api/task/execute/{id}         # 手动执行任务
PUT    /api/task/status/{id}          # 更新任务状态
POST   /api/task                      # 创建任务
PUT    /api/task                      # 更新任务
DELETE /api/task/{id}                 # 删除任务
```

### 数据管理

```
GET    /api/data/list                 # 查询数据列表(分页)
GET    /api/data/{id}                 # 查询数据详情
GET    /api/data/task/{taskId}        # 根据任务ID查询数据
GET    /api/data/pending              # 查询待上报数据
POST   /api/data/upload/{id}          # 手动上报单条数据
POST   /api/data/upload/batch         # 手动批量上报
POST   /api/data/upload/scheduled     # 触发定时上报
POST   /api/data/upload/retry         # 重试失败上报
```

### 国资委数据采集交换平台接口 🆕

```
POST   /preposed-machine/api/services/fileUpload       # 数据报送接口
GET    /preposed-machine/api/services/keyDownload      # 密钥证书下载
GET    /preposed-machine/api/services/tempDownload     # 采集目录下载
GET    /preposed-machine/api/services/taskDownload     # 采集任务下载
GET    /preposed-machine/api/services/noticeDownload   # 通知公告下载
GET    /preposed-machine/api/services/logDownload      # 数据日志下载
```

**详细说明**: 请查看 [接口实现文档](./INTERFACE_IMPLEMENTATION.md)

## 多数据源使用

### 在Mapper上指定数据源

```java
@Mapper
@DS("master")  // 使用主数据源
public interface TaskMapper extends BaseMapper<Task> {
    // ...
}

@Mapper
@DS("slave1")  // 使用从数据源1
public interface EnterpriseDataMapper extends BaseMapper<EnterpriseData> {
    // ...
}
```

### 在Service方法上动态切换

```java
@Service
public class DataCollectServiceImpl implements DataCollectService {
    
    @DS("slave2")  // 使用从数据源2
    public List<TaskData> collectFromExternalDB(Task task) {
        // 数据采集逻辑
    }
}
```

## 定时任务说明

系统提供以下定时任务:

1. **任务拉取**: 每天凌晨1点执行,从监管平台获取新任务
2. **任务执行**: 每5分钟执行一次,处理待执行的任务
3. **数据上报**: 每天凌晨3点执行,批量上报采集的数据
4. **失败重试**: 每小时执行一次,重试上报失败的数据

所有定时任务使用 Redisson 分布式锁,避免集群环境下重复执行。

## 注意事项

1. **达梦数据库配置**: 详细配置说明请参考 [达梦数据库配置指南](./DM_DATABASE_GUIDE.md)
2. **数据源配置**: 根据实际情况配置多个数据源,注意数据库驱动的差异
3. **SQL兼容性**: 不同数据库的SQL语法可能有差异,项目提供了三种数据库的SQL脚本
4. **事务管理**: 跨数据源操作时注意事务边界
5. **性能优化**: 大批量数据采集时注意分批处理,避免内存溢出
6. **监控告警**: 生产环境建议配置日志监控和异常告警
7. **数据安全**: 敏感信息(密码、密钥)应使用加密存储


## 扩展开发

### 1. 添加新的数据源

在 `application.yml` 中添加数据源配置,然后在代码中使用 `@DS("datasource_name")` 注解。

### 2. 实现新的采集方式

在 `DataCollectService` 中添加新的采集方法,如:

```java
public List<TaskData> collectFromNewSource(Task task) {
    // 实现新的采集逻辑
}
```

### 3. 自定义上报格式

修改 `DataUploadServiceImpl` 中的 `uploadSingleData` 方法,调整请求数据格式。

## 常见问题

### 1. 多数据源连接失败

检查数据库连接配置是否正确,确认数据库驱动版本匹配。

### 2. 定时任务不执行

检查 `app.scheduler.enabled` 配置是否为 `true`,查看日志确认是否有异常。

### 3. 数据上报失败

检查监管平台API地址和认证信息是否正确,查看上报记录表的错误信息。

## 许可证

本项目仅供学习交流使用。

## 联系方式

- 作者: qwe
- 日期: 2025-01-24
