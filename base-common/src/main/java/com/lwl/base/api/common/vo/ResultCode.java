package com.lwl.base.api.common.vo;

import lombok.Getter;

/**
 * 操作结果码
 * @author LinWenLi
 * @since 2020-01-08
 */
@Getter
public enum ResultCode {
    /**请求成功*/
    OK(200,"请求处理成功"),
    /**操作失败*/
    OPERATION_FAILED(100,"操作失败"),

    /**没有查找到数据*/
    NO_DATA_FOUND(1,"没有查找到数据"),
    /**查询失败*/
    /**新增失败*/
    /**更新失败*/
    /**删除失败*/

    /* 客户端错误 */
    /**坏请求（如，参数错误）*/
    BAD_REQUEST(400,"坏请求"),
    /**未授权*/
    UNAUTHORIZED(401,"未授权"),
    /**被禁止访问*/
    FORBIDDEN(403,"无此资源访问权限"),
    /**请求的资源不存在*/
    NOT_FOUND(404,"请求的资源不存在"),
    /**请求的方法不允许使用*/
    METHOD_NOT_ALLOWED(405,"请求的方法不允许使用"),
    /**不支持的媒体类型*/
    UNSUPPORTED_MEDIA_TYPE(415,"不支持的媒体类型"),
    /**请求过多*/
    TOO_MANY_REQUESTS(429,"请求过多"),

    /* 服务端错误 */
    /**服务器内部错误*/
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"),
    /**暂停服务*/
    SERVICE_UNAVAILABLE(503,"暂停服务"),

    /* 自定义状态码(HttpStatus用来表示操作是否正确，ResultCode用来说明操作具体结果，如果和HttpStatus相同，ResultCode就完全没用的必要了) */
    /**请求未报错,但得到预期外的结果*/
    UNEXPECTED_RESULTS(10001,"请求未报错,但得到预期外的结果"),
    /**获取token时的账号密码错误*/
    BAD_CREDENTIALS(10002,"账号或密码错误"),
    /**jwt校验失败*/
    AUTHENTICATION_ERROR(10003,"无效的身份凭证");

    /**状态码*/
    private final Integer code;
    /**原因*/
    private final String reasonPhrase;

    ResultCode(Integer code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }
}
