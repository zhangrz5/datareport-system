package com.company.datareport.util;

import com.company.datareport.util.sm4.SM4FileUtils;
import com.company.datareport.util.sm4.SM4Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SM4UtilsEcbTest {


    @Test
    @Order(1)
    @DisplayName("1. SM4 ECB 模式 - 解密已知密文")
    void testSm4DecryptKnownCiphertext() {
        log.info("\n========== 测试：解密已知密文 ==========");

        SM4Utils sm4 = new SM4Utils();
        sm4.setSecretKey("11320114745362524W");
        sm4.setHexString(false);

        // 这是你之前提到的密文
        String knownCiphertext = "RR8MeyuoVo+y8eVZ06aXysr6eAb4dgcqI4cDeuP3sGN4b8aru1OArbakbk/UgTlp";

        log.info("已知密文: {}", knownCiphertext);

        // 解密
        String decrypted = sm4.decryptData_ECB(knownCiphertext);
        assertNotNull(decrypted, "解密结果不应为空");
        log.info("解密结果: {}", decrypted);

        log.info("✓ 已知密文解密测试完成！");
    }


    @Test
    @Order(2)
    @DisplayName("1. SM4 ECB 模式 - 解密文件")
    void testSm4DecryptFilr() throws Exception{
        log.info("\n========== 测试：解密文件 ==========");
        byte[] fileBytes = SM4FileUtils.getBytes("E:\\project\\国企上报\\11320100748204097P_TEMP_0025_1001_20251121104734_ee429519bace4c129d65530fe830a819\\file_03637c26b4aa46d3827032ea7a7d8f6b.zip");
        SM4Utils sm4Utils = new SM4Utils();
        sm4Utils.setSecretKey("5b2ee70a2cd34d109ba1fb0a03bd6971");
        sm4Utils.setHexString(false);
        byte[] resultByte=sm4Utils.decryptData_ECB(fileBytes);
        SM4FileUtils.saveBinaryFile("E:\\project\\国企上报\\11320100748204097P_TEMP_0025_1001_20251121104734_ee429519bace4c129d65530fe830a819\\1.zip",resultByte);
        log.info("✓ 解密文件测试完成！");
    }


}
