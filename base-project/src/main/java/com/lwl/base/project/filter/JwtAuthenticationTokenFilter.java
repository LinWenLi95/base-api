package com.lwl.base.project.filter;

import com.lwl.base.project.config.security.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jwt身份验证令牌过滤器
 * @author LinWenLi
 * @date 2020-04-18
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * 1获取JWT
     * 2校验JWT是否正确
     * 3获取JWT头部的subject/role信息
     * 4生成UsernamePasswordAuthenticationToken对象
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
                // 获取请求中携带的token
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            // 使用密钥解析JWT
            Jws<Claims> claimsJws = null;
            try {
                claimsJws = Jwts.parserBuilder()
                        .setSigningKey(SecurityConstants.JWT_SECRET.getBytes())
                        .build().parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
                //将异常信息存入request对象中，在AuthenticationEntryPoint.commence方法中获取使用
                request.setAttribute(RequestDispatcher.ERROR_EXCEPTION, e);
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
                    // 将 authentication 存入 SecurityContext，方便后续获取用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }


}
