package com.health.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JasyptConfig {
    @Value("${jasypt.password.key}")
    private String passwordKey;

    @Bean(name ="jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(passwordKey);            // 암호화 키
        config.setAlgorithm("PBEWithMD5AndDES");    // 암호화 알고리즘
        config.setKeyObtentionIterations("1000");   // 반복 해싱 수
        config.setPoolSize("1");                    // 인스턴스 풀
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스(랜덤)
        config.setStringOutputType("base64");       // 인코딩
        encryptor.setConfig(config);
        return encryptor;
    }
}
