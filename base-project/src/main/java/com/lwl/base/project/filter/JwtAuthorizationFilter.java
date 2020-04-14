package com.lwl.base.project.filter;

import com.lwl.base.api.common.vo.ResultCode;
import com.lwl.base.project.config.SecurityConstants;
import com.lwl.base.project.filter.strategy.ResponseExpiredJWTStrategy;
import com.lwl.base.project.filter.strategy.impl.CustomerResponseExpiredJWTStrategy;
import com.lwl.base.project.filter.strategy.impl.DefaultResponseExpiredJWTStrategy;
import com.lwl.base.project.filter.util.FilterUtils;
import com.sun.org.apache.regexp.internal.RE;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * token校验
 * @author LinWenLi
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = null;
        try {
            authentication = getAuthentication(request);
        } catch (ExpiredJwtException e) {
            // 在这里处理token过期
            e.printStackTrace();
        }
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    /**
     * 获取请求对象中的验证令牌对象
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws ExpiredJwtException{
        // 获取请求中携带的token
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            // 使用密钥解析token
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SecurityConstants.JWT_SECRET.getBytes())
                    .build().parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
            // 获取用户名及授权列表
            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            List<SimpleGrantedAuthority> authorities = ((List<?>) body.get("rol")).stream()
                    .map(authority -> new SimpleGrantedAuthority((String) authority))
                    .collect(Collectors.toList());
            // 组成授权令牌对象
            if (!StringUtils.isEmpty(username)) {
                return new UsernamePasswordAuthenticationToken(username, null, authorities);
            }
        }
        return null;
    }
}
