package com.lwl.base.project.interceptor;

import com.lwl.base.api.common.vo.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        Map<String, Object> map = getMap(ResultCode.INTERNAL_SERVER_ERROR.getCode(), ResultCode.INTERNAL_SERVER_ERROR.getReasonPhrase());
//        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
       Map<String, Object> map = new HashMap<>();
       map.put("code", ResultCode.BAD_REQUEST.getCode());
       map.put("msg", e.getMessage());
//        log.error(e.getMessage());
        e.printStackTrace();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }



    private Map<String, Object> getMap(Integer code, String msg) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }
}




