package com.cos.blog.controller.viewcontroller;

import com.cos.blog.model.entity.User;
import com.cos.blog.model.social.SocialLoginSecretKey;
import com.cos.blog.service.impl.KakaoLoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// /auth/** 경로, static 이하경로 (ex. /js/**, /css/**, /image/** 등),
// / (index.jsp) 는 모든 사용자에게 허용하기 위해.

@Controller
@RequiredArgsConstructor
public class SignViewController {

    private final SocialLoginSecretKey key;

    private final KakaoLoginService socialLoginService;

    private final AuthenticationManager authenticationManager;

    @GetMapping("/auth/signinForm")
    public String getSignInForm() {
        return "/signforms/signinform";
    }

    @GetMapping("/auth/signupForm")
    public String getSignUpForm() {
        return "/signforms/signupform";
    }

    //카카오 로그인을 위한 컨트롤러
    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam(value = "code") String code)
        throws JsonProcessingException {

        User user = socialLoginService.login(code);

        //강제 로그인처리
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), key.getKakao())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }
}
