package com.cos.blog.exception;

import com.cos.blog.common.Response;
import com.cos.blog.exception.exceptions.BadBoardRequestException;
import com.cos.blog.exception.exceptions.BoardNotFoundException;
import com.cos.blog.exception.exceptions.InvalidateUserUpdateRequestException;
import com.cos.blog.exception.exceptions.LoginFailException;
import com.cos.blog.exception.exceptions.UserAlreadyExistException;
import com.cos.blog.exception.exceptions.ValidationIllegalArgumentException;
import com.cos.blog.exception.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<String> handleUserNotFoundException(UserNotFoundException e) {
        return new Response<>(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Response<String> handleUserAlreadyExistException(UserAlreadyExistException e) {
        return new Response<>(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
    }

    @ExceptionHandler(LoginFailException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Response<String> handleLoginFailException(LoginFailException e) {
        return new Response<>(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
    }

    @ExceptionHandler(BadBoardRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<String> handleBadBoardRequestException(BadBoardRequestException e) {
        return new Response<>(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response<String> handleBoardNotFoundException(BoardNotFoundException e) {
        return new Response<>(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(InvalidateUserUpdateRequestException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Response<String> handleInvalidateUserUpdateRequestException(
        InvalidateUserUpdateRequestException e) {
        return new Response<>(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
    }

    @ExceptionHandler(ValidationIllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<String> handleValidationIllegalArgumentException(
        ValidationIllegalArgumentException e) {
        return new Response<>(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
