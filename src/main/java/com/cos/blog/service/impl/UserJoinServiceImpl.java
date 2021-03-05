package com.cos.blog.service.impl;

import com.cos.blog.domain.Social;
import com.cos.blog.domain.UserRole;
import com.cos.blog.exception.exceptions.UserAlreadyExistException;
import com.cos.blog.model.dto.request.UserJoinRequestDto;
import com.cos.blog.model.entity.User;
import com.cos.blog.model.repository.UserRepository;
import com.cos.blog.service.UserJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserJoinServiceImpl implements UserJoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public String joinUser(UserJoinRequestDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserAlreadyExistException();
        }
        String hashedPassword = encoder.encode(userDto.getPassword());
        userDto.setPassword(hashedPassword);

        User user = new User(userDto);
        if(user.getSocial() == null) {
            user.setSocial(Social.BLOG);
        }
        user.setRole(UserRole.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }
}
