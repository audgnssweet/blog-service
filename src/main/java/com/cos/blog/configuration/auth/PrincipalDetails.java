package com.cos.blog.configuration.auth;

import com.cos.blog.model.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//스프링 시큐리티는 로그인시 시큐리티만의 세션에 UserDetails를 구현한 객체를 저장함.
//함수들은 정보와 기능에 관련된 것들
@Getter
public class PrincipalDetails implements UserDetails {

    private final User user;  //composition

    public PrincipalDetails(User user) {
        this.user = user;
    }

    //어떤 권한을 가졌나요?
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //add에 함수를 넣고싶은데 자바는 함수 못넣고 객체 넣어야하니까 객체를 생성해서 넣어주는 방법.
//        authorities.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return "ROLE_" + user.getRole();
//            }
//        });
        authorities.add(() -> {
            return "ROLE_" + user.getRole();    //spring security의 role naming 전략. 따라야 함.
        });
        //원래 권한이 여러개이면 반복문을 돌려야 함.
        return authorities;
    }

    // 세션에서 객체에게 요청시 실제 반환되는 password
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되지 않았나요? True : 만료되지 않았음.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있지 않나요? true : 잠겨있지 않음.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았나요? true : 만료되지 않았음.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 사용 가능한가요? true : 사용 가능.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
