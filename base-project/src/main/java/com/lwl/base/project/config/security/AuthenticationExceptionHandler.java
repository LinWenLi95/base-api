package com.lwl.base.project.config.security;

import com.alibaba.fastjson.JSON;
import com.lwl.base.api.common.exception.CustomException;
import com.lwl.base.api.common.vo.Result;
import com.lwl.base.api.common.vo.ResultCode;
import com.lwl.base.project.util.ResponseUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 身份验证异常处理
 * @author LinWenLi
 * @date 2020-04-18
 */
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //统一抛异常时的响应数据格式
        Result<Object> result = getResponseData(request, authException);
        ResponseUtils.responseResult(response, result);
    }

    /**
     * 根据异常类型返回对应响应信息 TODO 响应的异常信息提示应模糊处理
     */
    private Result<Object> getResponseData(HttpServletRequest request, AuthenticationException authException) {
        //创建默认返回的实体
        Result<Object> result = Result.failure(ResultCode.FORBIDDEN);
        //获取异常对象
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        //判断异常对象类型并设置响应信息
        if (exception instanceof ExpiredJwtException) {
            result.setMsg("jwt过期辣");
        } else if (exception instanceof UnsupportedJwtException) {
            result.setMsg("不支持的jwt");
        } else if (exception instanceof MalformedJwtException) {
            result.setMsg("jwt格式错误");
        } else if (exception instanceof SignatureException) {
            result.setMsg("jwt签名错误");
        } else if (exception instanceof IllegalArgumentException) {
            result.setMsg("jwt凭据非法");
        }
        return result;
    }


}
