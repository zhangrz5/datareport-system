# 国资国企数据采集上报系统

## 项目简介

基于 Spring Boot + MyBatis-Plus 的多数据源数据采集上报系统,用于国资国企在线监管系统的数据采集、处理和上报。

## 技术栈

- **核心框架**: Spring Boot 2.7.18
- **数据库**: PostgreSQL / MySQL (多数据源支持)
- **ORM框架**: MyBatis-Plus 3.5.5
- **多数据源**: Dynamic-Datasource 4.2.0
- **连接池**: Druid 1.2.20
- **缓存**: Redis + Redisson
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

## 配置说明

### 1. 数据源配置

支持多数据源,在 `application.yml` 中配置:

```yaml
spring:
  datasource:
    dynamic:
      primary: master  # 默认数据源
      datasource:
        master:        # 主数据源(本地业务库)
          driver-class-name: org.postgresql.Driver
          url: jdbc:postgresql://localhost:5432/datareport
          username: postgres
          password: postgres
        slave1:        # 从数据源1(企业数据库)
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/enterprise_db
          username: root
          password: root
        slave2:        # 从数据源2(外部系统)
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

## 快速开始

### 1. 环境要求

- JDK 1.8+
- Maven 3.6+
- PostgreSQL 12+ / MySQL 8.0+
- Redis 5.0+

### 2. 数据库初始化

```bash
# 创建数据库
CREATE DATABASE datareport;

# 执行初始化脚本
psql -U postgres -d datareport -f src/main/resources/sql/schema.sql
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

1. **数据源配置**: 根据实际情况配置多个数据源,注意数据库驱动的差异
2. **SQL兼容性**: 不同数据库的SQL语法可能有差异,需要适配
3. **事务管理**: 跨数据源操作时注意事务边界
4. **性能优化**: 大批量数据采集时注意分批处理,避免内存溢出
5. **监控告警**: 生产环境建议配置日志监控和异常告警
6. **数据安全**: 敏感信息(密码、密钥)应使用加密存储

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
