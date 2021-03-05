package com.cos.blog.configuration.auth;

import com.cos.blog.model.entity.User;
import com.cos.blog.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //로그인 요청을 가로챌 때 username, password를 가로채는데
    //password부분 처리는 알아서 한다.
    //username이 db에 있는지만 확인해주면 된다.
    //로그인은 이 함수를 통해서 하게된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User foundUser = userRepository.findFirstByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("해당 유저는 없습니다."));
        return new PrincipalDetails(foundUser); //시큐리티 세션에 저장된다.
    }
}
