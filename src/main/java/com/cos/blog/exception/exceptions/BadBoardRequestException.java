package com.cos.blog.exception.exceptions;

public class BadBoardRequestException extends RuntimeException {

    public BadBoardRequestException() {
        super("당신의 게시글이 아닙니다");
    }
}
