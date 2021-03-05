package com.cos.blog.aop;

import com.cos.blog.exception.exceptions.ValidationIllegalArgumentException;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Aspect
@Component
public class ValidationAspect {

    @Around(value = "execution(* com.cos.blog.controller..*Controller.*(..))")
    public Object checkValidation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        final Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
//                    Map<String, String> errorMap = new HashMap<>();
                    StringBuilder errorMessage = makeErrorMessage(bindingResult);
                    throw new ValidationIllegalArgumentException(errorMessage.toString());
                }
                break;
            }
        }
        return proceedingJoinPoint.proceed();
    }

    private StringBuilder makeErrorMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            //객체 자체를 반환해줘도 되고.
//                        errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            errorMessage.append(String.format("%s ", fieldError.getDefaultMessage()));
        }
        return errorMessage;
    }
}
