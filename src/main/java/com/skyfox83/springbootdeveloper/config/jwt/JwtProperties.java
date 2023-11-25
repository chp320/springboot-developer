package com.skyfox83.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 설정 파일(application.yml)에 설정한 isser, secret_key 를 변수로 사용하기 위한 클래스
 */
@Getter
@Setter
@Component
@ConfigurationProperties("jwt")     // 자바 클래스에 프로퍼티 값을 가져와서 사용하는 어노테이션
public class JwtProperties {
    private String issuer;
    private String secretKey;
}
