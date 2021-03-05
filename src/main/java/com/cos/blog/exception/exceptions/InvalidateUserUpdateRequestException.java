package com.cos.blog.exception.exceptions;

public class InvalidateUserUpdateRequestException extends RuntimeException {

    public InvalidateUserUpdateRequestException() {
        super("허가되지 않은 요청입니다");
    }
}
