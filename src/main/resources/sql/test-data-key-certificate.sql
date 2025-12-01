-- ============================================================
-- 密钥证书表测试数据 (达梦数据库版本)
-- 根据真实密钥XML文件生成的测试数据
-- ============================================================

-- 测试数据: 正在使用中的密钥 (用于测试keyDownload接口)
-- 密钥信息来源: 37f26e11-0016-44f6-9c69-1dce73bf1e3e
INSERT INTO "T_KEY_CERTIFICATE" (
    "LOG_ID",
    "KEY_ID",
    "BUSINESS_TYPE",
    "KEY_VERSION",
    "SM2_PUBLIC_KEY",
    "SM4_KEY",
    "SM2_PUBLIC_KEY_DECRYPTED",
    "SM4_KEY_DECRYPTED",
    "FILE_NAME",
    "FILE_PATH",
    "DOWNLOAD_TIME",
    "STATUS",
    "ACTIVE_TIME",
    "EXPIRE_TIME",
    "REMARK",
    "CREATE_BY",
    "UPDATE_BY"
) VALUES (
    NULL,
    '37f26e11-0016-44f6-9c69-1dce73bf1e3e',
    '0025',
    '1001',
    'ONtSg6ZcQTeSSEd++RGQEzckyJzbO4ryEtV4g8RRM6kKHd/B1WITqhyWJwTPOdjPcxIfz8sdDsi/81he5mC6qb3Pk29SXn8usd8J8rqKwiWNEgqizHhIrc4TtNGT1q+YKbskSVOHqgo2Vs452dn/++7/jOtXnwNzDeTAadegtoF4b8aru1OArbakbk/UgTlp',
    'RR8MeyuoVo+y8eVZ06aXysr6eAb4dgcqI4cDeuP3sGN4b8aru1OArbakbk/UgTlp',
    NULL,
    NULL,
    '34020000MB4C79605G.zip',
    NULL,
    NULL,
    0,
    NULL,
    TO_DATE('2026-11-21 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
    '测试密钥-业务类型0025-版本1001-发布于2025-11-21',
    'system',
    'system'
);

COMMIT;

-- ============================================================
-- 字段说明:
-- ============================================================
-- KEY_ID: 37f26e11-0016-44f6-9c69-1dce73bf1e3e (从XML的KI_ID获取)
-- BUSINESS_TYPE: 0025 (从XML的BUSTYPE获取)
-- KEY_VERSION: 1001 (从XML的KI_VERSION获取)
-- SM2_PUBLIC_KEY: 从XML的KI_PUBLIC_KEY获取 (加密存储)
-- SM4_KEY: 从XML的KI_SM4_KEY获取 (加密存储)
-- SM2_PUBLIC_KEY_DECRYPTED: NULL (解密后由应用程序填充)
-- SM4_KEY_DECRYPTED: NULL (解密后由应用程序填充)
-- FILE_NAME: 34020000MB4C79605G.zip (密钥文件名)
-- STATUS: 0 (未启用,待下载)
-- DOWNLOAD_TIME: NULL (未下载)
-- EXPIRE_TIME: 2026-11-21 (密钥发布日期+1年有效期)
