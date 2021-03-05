package com.cos.blog.model.social;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "secret.key")
@Data
public class SocialLoginSecretKey {

    private String kakao;

}
