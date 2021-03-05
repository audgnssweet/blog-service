package com.cos.blog.exception.exceptions;

public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException() {
        super("잘못된 게시글 번호 입력입니다");
    }
}
