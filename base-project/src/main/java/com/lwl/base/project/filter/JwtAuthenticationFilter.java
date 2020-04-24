package com.lwl.base.project.filter;

import com.lwl.base.api.common.vo.Result;
import com.lwl.base.api.common.vo.ResultCode;
import com.lwl.base.project.config.redis.RedisConstants;
import com.lwl.base.project.config.security.SecurityConstants;
import com.lwl.base.project.entity.SysUser;
import com.lwl.base.project.service.ISysUserService;
import com.lwl.base.project.util.RedisUtils;
import com.lwl.base.project.util.ResponseUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 过滤器 登录时进行身份认证，认证成功后返回授权token
 * @author LinWenLi
 * @since 2020-04-18
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//    @Autowired
//    private ISysUserService userService;

    private final AuthenticationManager authenticationManager;

    /**构造过滤器对象*/
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        //设置要过滤的目标url
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
        this.authenticationManager = authenticationManager;
    }

    /**
     * 用户登录时获取请求中的用户名密码参数进行身份认证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 取出登录请求中的用户名密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 将用户名密码构造成UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        // 将UsernamePasswordAuthenticationToken使用认证管理器进行身份认证
        return authenticationManager.authenticate(authRequest);
    }

    /**
     * 身份认证成功则返回token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        List<String> roleNames = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        //创建token并保存到redis
        String token = createAndSaveToken(user.getUsername(), roleNames);
        //token加入响应对象header中
        response.setHeader(SecurityConstants.TOKEN_HEADER, token);
        //统一响应数据格式
        ResponseUtils.responseResult(response, Result.ok());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Result<Object> result = Result.error(ResultCode.BAD_CREDENTIALS);
        //后续看有没有加详细错误的需要再添加
        if (failed instanceof BadCredentialsException) {
            result.setMsg("账号或密码错误");
        }
        //统一响应数据格式
        ResponseUtils.responseResult(response, result);
    }

    /**
     * 创建token并保存到redis
     * @param username 用户登录名
     * @param roleNames 角色名列表
     * @return token
     */
    public String createAndSaveToken(String username, List<String> roleNames) {
//        SysUser sysUser = userService.queryByUsername(username);
        // 使用JWT_SECRET生成SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes());
        String token = Jwts.builder()
                .setHeaderParam("TYP", SecurityConstants.TOKEN_TYPE)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .setSubject(username)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setIssuedAt(new Date())
                // 角色列表放入header
//                .claim("uid", sysUser.getId())
                .claim("rol", roleNames)
                .signWith(secretKey)
                .compact();

        token = SecurityConstants.TOKEN_PREFIX + token;
        //将token存入redis，有效时间5分钟
        RedisUtils.set(String.format(RedisConstants.JWT_USERNAME, username), token, 300L, TimeUnit.SECONDS);
        return token;
    }
}
