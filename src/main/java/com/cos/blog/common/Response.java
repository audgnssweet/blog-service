package com.cos.blog.common;

import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response<T> {

    private final LocalDateTime localDateTime;
    private final Integer status;
    private final T data;

    public Response(HttpStatus status, T data) {
        this.localDateTime = LocalDateTime.now();
        this.status = status.value();
        this.data = data;
    }

}
