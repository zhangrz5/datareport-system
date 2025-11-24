# 国资委数据采集交换平台接口实现文档

## 概述

本系统实现了国资委数据采集交换平台的6个核心接口,用于企业与国资委之间的数据交换。

## 数据库表结构

### 核心表

1. **t_interface_log** - 统一接口调用日志表
   - 记录所有接口调用的详细信息
   - 包含请求参数、响应结果、耗时等

2. **t_file_upload_record** - 数据报送记录表
   - 记录文件上传的详细信息
   - 包含文件MD5、解析状态等

3. **t_key_certificate** - 密钥证书表
   - 存储SM2公钥和SM4密钥
   - 支持密钥版本管理

4. **t_business_type** - 业务类型表
   - 管理业务类型信息

5. **t_template_info** - 采集模板信息表
   - 存储数据采集模板
   - 关联业务类型

6. **t_template_frequency** - 模板采集频率表
   - 定义各字段的采集频率

7. **t_supplement_task** - 补传任务表
   - 管理数据补传任务

8. **t_notice_announcement** - 通知公告表
   - 存储下发的通知公告

9. **t_remote_log_sync** - 远程日志同步表
   - 同步国资委端的日志

10. **t_interface_config** - 接口配置表
    - 存储接口相关配置信息

## 接口说明

### 1. 数据报送接口 (fileUpload)

**接口地址**: `POST /preposed-machine/api/services/fileUpload`

**请求参数**:
- file: MultipartFile - ZIP格式的报送数据文件
- APICODE: String - 接口编码 (如: SZ01)
- BUSTYPE: String - 业务类型 (如: 0026)
- FILE_NAME: String - 文件名称(含后缀)
- SOCIALCREDITCODE: String - 企业18位社会信用码
- USER: String - 用户名
- PASSWORD: String - 密码

**响应格式**:
```json
{
  "serviceFlag": "1",
  "msg": "报送文件接口:文件接收成功"
}
```

**serviceFlag说明**:
- 0: 失败
- 1: 成功
- 2: 没有权限

### 2. 密钥证书接口 (keyDownload)

**接口地址**: `GET /preposed-machine/api/services/keyDownload`

**请求参数**:
- BUSTYPE: String - 业务类型
- USER: String - 用户名
- PASSWORD: String - 密码

**响应**:
- 成功: 返回ZIP文件(包含密钥XML数据)
- 失败/无文件: 返回JSON

**文件命名规则**:
```
{社会信用代码}_KEY_0001_{版本号}_{时间戳}_{UUID}.zip
```

### 3. 数据采集目录接口 (tempDownload)

**接口地址**: `GET /preposed-machine/api/services/tempDownload`

**请求参数**:
- BUSTYPE: String - 业务类型 (默认: 0025)
- USER: String - 用户名
- PASSWORD: String - 密码

**响应**:
- 成功: 返回ZIP文件(包含模板XML和示例文件)
- 失败/无文件: 返回JSON

**文件命名规则**:
```
{社会信用代码}_TEMP_0001_{版本号}_{时间戳}_{UUID}.zip
```

### 4. 接收采集任务接口 (taskDownload)

**接口地址**: `GET /preposed-machine/api/services/taskDownload`

**请求参数**:
- USER: String - 用户名
- PASSWORD: String - 密码

**响应**:
- 成功: 返回ZIP文件(包含任务XML数据)
- 失败/无文件: 返回JSON

**特点**: 增量更新,需轮询调用直到无新文件

### 5. 接收下发数据接口 (noticeDownload)

**接口地址**: `GET /preposed-machine/api/services/noticeDownload`

**请求参数**:
- USER: String - 用户名
- PASSWORD: String - 密码
- SYSCODE: String - 系统代号(安徽模式必填)
- BUSTYPE: String - 业务类型(安徽模式必填)

**响应**:
- 成功: 返回ZIP文件(包含通知XML和附件)
- 失败/无文件: 返回JSON

**通知类型**:
- 0007: 异常反馈通知
- 0009: 普通通知

### 6. 数据日志接口 (logDownload)

**接口地址**: `GET /preposed-machine/api/services/logDownload`

**请求参数**:
- USER: String - 用户名
- PASSWORD: String - 密码
- STARTDATE: String - 开始日期 (格式: yyyyMMdd)
- ENDDATE: String - 结束日期 (格式: yyyyMMdd)
- SIDE: String - 方向标识
  - ENTERPRISE: 返回报送文件日志
  - ETIMED: 返回获取采集目录日志
- SYSCODE: String - 系统代号(安徽模式)
- BUSTYPE: String - 业务类型(安徽模式)

**响应格式**:
```json
{
  "serviceFlag": "3",
  "msg": "日志下载接口:获取到对应时间的日志",
  "logList": [
    {
      "el_id": "日志主键ID",
      "business_code": "业务编码",
      "file_name": "报送文件名称",
      "push_date": "推送时间",
      "grab_date": "抓取时间",
      "repair_mark": "补录标识",
      "el_createdate": "日志生成时间"
    }
  ]
}
```

## 配置说明

### 接口配置项

在 `t_interface_config` 表中配置以下参数:

| 配置键 | 说明 | 默认值 |
|--------|------|--------|
| preposed.server.host | 前置服务器地址 | http://localhost:8080 |
| preposed.server.port | 前置服务器端口 | 8080 |
| preposed.api.username | 接口调用用户名 | admin |
| preposed.api.password | 接口调用密码 | admin123 |
| preposed.social.credit.code | 企业社会信用代码 | 91110000000000000X |
| preposed.ah.status | 安徽模式状态 | 0 (0-禁用, 1-启用) |
| preposed.file.upload.path | 文件上传路径 | /data/upload |
| preposed.file.download.path | 文件下载路径 | /data/download |
| preposed.retry.max.count | 最大重试次数 | 3 |
| preposed.retry.interval | 重试间隔(毫秒) | 60000 |

## 数据库初始化

### MySQL数据库

```bash
# 执行MySQL版本的SQL脚本
mysql -u root -p datareport < src/main/resources/sql/schema-interface.sql
```

### 达梦数据库

```bash
# 执行达梦数据库版本的SQL脚本
# 使用DM管理工具或命令行执行
disql SYSDBA/SYSDBA@localhost:5236 < src/main/resources/sql/schema-interface-dm.sql
```

## 使用示例

### 1. 上传数据文件

```bash
curl -X POST "http://localhost:8080/preposed-machine/api/services/fileUpload" \
  -F "file=@data.zip" \
  -F "APICODE=SZ01" \
  -F "BUSTYPE=0026" \
  -F "FILE_NAME=data.zip" \
  -F "SOCIALCREDITCODE=91110000000000000X" \
  -F "USER=admin" \
  -F "PASSWORD=admin123"
```

### 2. 下载密钥文件

```bash
curl -X GET "http://localhost:8080/preposed-machine/api/services/keyDownload?BUSTYPE=0001&USER=admin&PASSWORD=admin123" \
  -o key.zip
```

### 3. 下载采集目录

```bash
curl -X GET "http://localhost:8080/preposed-machine/api/services/tempDownload?BUSTYPE=0025&USER=admin&PASSWORD=admin123" \
  -o template.zip
```

### 4. 获取日志

```bash
curl -X GET "http://localhost:8080/preposed-machine/api/services/logDownload?USER=admin&PASSWORD=admin123&STARTDATE=20250101&ENDDATE=20250131&SIDE=ENTERPRISE"
```

## 安全说明

1. **认证机制**: 所有接口都需要用户名和密码验证
2. **密钥加密**: SM2公钥和SM4密钥使用社会信用代码加密存储
3. **文件安全**: 上传的文件需要是加密的ZIP格式
4. **日志记录**: 所有接口调用都会记录详细日志

## 开发计划

### 待实现功能

1. **文件下载逻辑**
   - 密钥文件查找和下载
   - 模板文件查找和下载
   - 任务文件查找和下载
   - 通知文件查找和下载

2. **XML解析**
   - 密钥XML解析和入库
   - 模板XML解析和入库
   - 任务XML解析和入库
   - 通知XML解析和入库

3. **定时任务**
   - 定时轮询下载新文件
   - 定时同步日志
   - 定时清理过期文件

4. **加密解密**
   - SM2加密解密实现
   - SM4加密解密实现
   - 文件加密解密

5. **文件管理**
   - 文件版本管理
   - 文件清理策略
   - 文件完整性校验

## 注意事项

1. 确保文件上传和下载路径有足够的磁盘空间
2. 定期清理过期的文件和日志
3. 密钥文件只保留最新版本
4. 时间区间查询不可超过1个自然月
5. 安徽模式下需要额外的参数校验

## 技术栈

- Spring Boot 2.x
- MyBatis-Plus
- MySQL / 达梦数据库
- Lombok
- Spring Cache

## 联系方式

如有问题,请联系开发团队。
