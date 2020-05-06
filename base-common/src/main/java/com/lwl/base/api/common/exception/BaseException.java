package com.lwl.base.api.common.exception;

/**
 * @author LinWenLi
 * @since 2020-05-06
 */
public class BaseException extends RuntimeException{

    public BaseException() {
        super();
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message) {

    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {

    }
}
