package com.cos.blog.configuration;

import com.cos.blog.configuration.auth.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//세트임.
@Configuration      //bean 등록
@EnableWebSecurity  //filter로서 동작할 수 있도록 하는 것.
@EnableGlobalMethodSecurity(prePostEnabled = true)  //권한 및 인증을 미리 체크하겠다는 뜻.
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailsService principalDetailsService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean  //password해시 bean등록.
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    //    시큐리티가 대신 로그인하는데, 패스워드가 무엇으로 해시가 되어서 회원가입이 되었는지 알아야
    //    같은 해쉬로 암호화가 되어서 디비에 있는 해쉬값과 비교를 할 수 있음. 알려줘야함.
    //패스워드를 알아서 비교해달라는 것.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(principalDetailsService)       //실제 로그인을 진행하는 service 객체를 넣어줘야함.
            .passwordEncoder(encoder());    //어떤 인코더를 사용하는지 (위에 빈 주입해준 것)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http                        //http에 대해
            .csrf().disable()       //csrf 토큰을 비활성화 한다 (배포전 구축 및 테스트에 한해서)
            .authorizeRequests()    //요청이 들어오면
            .antMatchers("/auth/**", "/js/**", "/css/**", "/image/**",
                "/test/**")    // /auth이하, js ,css image는
            .permitAll()            //누구에게나 허용한다.
            .anyRequest()           //이게 아닌 다른 모든 요청은
            .authenticated()        //인증이 되어야해.
            .and()                  //그리고
            .formLogin()            //로그인 관련 설정 할건데
            .loginPage("/auth/signinForm") //권한이 필요한 페이지에 요청하면? 로그인 페이지 여기니까 자동으로 이동해줘.
            .loginProcessingUrl("/auth/signinProc") //로그인 이 주소로 오면 security 니가 대신 가로채서 로그인 해줘.
            .defaultSuccessUrl("/");  //로그인 성공시 여기로 이동시켜줘.
        //.failureUrl("/fail");     //실패시 여기로 이동.
    }
    //csrf와 xss 공격과 대응방법?
    //xss - 자바스크립트 공격. - 네이버 lucy 필터.
    //csrf - 허용되지않은 링크? - csrf 필터링. spring security에서는 자동으로 csrf필터링해줌.
    //ajax요청을 하기 때문에 csrf 토큰을 보내지 않고, 자동으로 걸러지기 때문에 설정해준 것.

}
