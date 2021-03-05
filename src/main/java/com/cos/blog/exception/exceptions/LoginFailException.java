package com.cos.blog.exception.exceptions;

public class LoginFailException extends RuntimeException {

    public LoginFailException() {
        super("없는 유저이거나, 틀린 비밀번호입니다");
    }
}
