package com.lwl.base.project.interceptor;

import com.lwl.base.api.common.vo.Result;
import com.lwl.base.api.common.vo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller的异常处理
 * @author LinWenLi
 * @since 2020-04-21 23:20
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**最终的异常处理*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.error(ResultCode.INTERNAL_SERVER_ERROR));
    }

    /**接口上注解了@RequestBody，而请求body中没有任何数据时抛出此异常*/
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error(ResultCode.BAD_REQUEST));
    }

    /**参数校验不通过时抛出此异常*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = null;
        if (e.getBindingResult().getFieldError() != null) {
            msg = e.getBindingResult().getFieldError().getDefaultMessage();
        }
        Result<Object> result = Result.error(ResultCode.BAD_REQUEST, msg);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}




