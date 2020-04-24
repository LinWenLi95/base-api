package com.lwl.base.api.common.vo;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 操作结果实体
 * @author LinWenLi
 * @since 2020-01-08
 */
@Data
public class Result<T> {

    /**是否操作成功*/
    private Boolean success;
    /**操作结果码*/
    private Integer code;
    /**提示消息*/
    private String msg;
    /**返回数据*/
    private T data;

    /**Result构造方法，仅供getInstance调用*/
    private Result(ResultCode resultCode, String msg, T data) {
        this.success = resultCode == ResultCode.OK;
        this.code = resultCode.getCode();
        this.msg = StringUtils.isEmpty(msg) ? resultCode.getReasonPhrase() : msg;
        this.data = data;
    }

    /**链式方法 快捷设置响应提示信息*/
    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    /**链式方法 快捷设置响应数据*/
    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    /**获取新Result实例*/
    public static <T> Result<T> getInstance(ResultCode resultCode, String msg, T data) {
        return new Result<>(resultCode, msg, data);
    }

    /**请求处理成功*/
    public static <T> Result<T> ok(T data) {
        return Result.getInstance(ResultCode.OK, null, data);
    }

    /**请求处理成功*/
    public static <T> Result<T> ok() {
        return Result.getInstance(ResultCode.OK, null, null);
    }

    /**请求处理失败*/
    public static <T> Result<T> error() {
        return Result.getInstance(ResultCode.OPERATION_FAILED, null, null);
    }

    /**请求处理失败*/
    public static <T> Result<T> error(String msg) {
        return Result.getInstance(ResultCode.OPERATION_FAILED, msg, null);
    }

    /**请求处理失败*/
    public static <T> Result<T> error(ResultCode resultCode) {
        return Result.getInstance(resultCode, null, null);
    }

    /**请求处理失败*/
    public static <T> Result<T> error(ResultCode resultCode, String msg) {
        return Result.getInstance(resultCode, msg, null);
    }


}
