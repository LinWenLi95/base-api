package com.lwl.base.project.util;

import com.lwl.base.project.config.security.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.RequestDispatcher;

/**
 * 认证信息内容获取工具类
 * @author LinWenLi
 * @since 2020-04-24
 */
public class JwtUtils {

    /**
     * 获取当前请求的认证用户id
     * @return String
     */
    public static String getUserId() {
        return String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    /**
     * 获取主要认证信息，用于获取用户id
     */
    public static Object getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getSubjectFormToken(String token) {
        // 使用密钥解析JWT
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = Jwts.parserBuilder()
                    .setSigningKey(SecurityConstants.JWT_SECRET.getBytes())
                    .build().parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            return null;
        }
        if (claimsJws == null) {
            return null;
        }
        // 获取用户名及授权列表
        Claims body = claimsJws.getBody();
        return body.getSubject();
    }
}
