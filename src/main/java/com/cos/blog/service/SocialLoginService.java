package com.cos.blog.service;

import com.cos.blog.model.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SocialLoginService {

    User login(String code) throws JsonProcessingException;
}
