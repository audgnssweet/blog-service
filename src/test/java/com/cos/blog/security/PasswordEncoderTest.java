package com.cos.blog.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
@RunWith(SpringRunner.class) : 해당 어노테이션을 사용하면 JUnit의 러너를 사용하는게 아니라 지정된 SpringRunner 클래스를 사용한다.
@EnableConfigurationProperties : Configuration으로 사용하는 클래스를 빈으로 등록할 수 있게 해줌.
 */
@SpringBootTest
public class PasswordEncoderTest {

    private final PasswordEncoder encoder;

    @Autowired
    public PasswordEncoderTest(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Test
    void encoding() {
        System.out.println(encoder.encode("12345"));
    }
}
