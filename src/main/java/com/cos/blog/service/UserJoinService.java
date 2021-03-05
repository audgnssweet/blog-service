package com.cos.blog.service;

import com.cos.blog.model.dto.request.UserJoinRequestDto;

public interface UserJoinService {

    String joinUser(UserJoinRequestDto user);
}
