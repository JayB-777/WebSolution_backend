package com.websolution.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtConfig {
    private String secret; // 실제 프로덕션에서는 환경 변수나 외부 설정으로 관리
    private long accessTokenExpiration; // 1시간
    private long refreshTokenExpiration; // 24시간
}
