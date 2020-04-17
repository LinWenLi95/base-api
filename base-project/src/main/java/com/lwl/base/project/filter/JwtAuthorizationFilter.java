package com.lwl.base.project.filter;

import com.lwl.base.api.common.vo.ResultCode;
import com.lwl.base.project.config.SecurityConstants;
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
import org.springframework.web.filter.OncePerRequestFilter;

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
 * JWT校验 授权
 * @author LinWenLi
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取请求中携带的token
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            Jws<Claims> claimsJws = null;
            try {
                // 使用密钥解析JWT
                claimsJws = Jwts.parserBuilder()
                        .setSigningKey(SecurityConstants.JWT_SECRET.getBytes())
                        .build().parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
            }  catch (ExpiredJwtException e) {
                // 在这里处理token过期
                e.printStackTrace();
            }
            if (claimsJws != null) {
                // 获取用户名及授权列表
                Claims body = claimsJws.getBody();
                String username = body.getSubject();
                List<SimpleGrantedAuthority> authorities = ((List<?>) body.get("rol")).stream()
                        .map(authority -> new SimpleGrantedAuthority((String) authority))
                        .collect(Collectors.toList());
                if (!StringUtils.isEmpty(username)) {
                    // 组成授权令牌对象
                    Authentication authentication =  new UsernamePasswordAuthenticationToken(username, null, authorities);
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

}
