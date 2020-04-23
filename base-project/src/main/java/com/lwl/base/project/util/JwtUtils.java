package com.lwl.base.project.util;

import com.lwl.base.project.config.security.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;

import javax.servlet.RequestDispatcher;

/**
 * @author LinWenLi
 * @since 2020-04-24
 */
public class JwtUtils {

    public static String getUserNameFormToken(String token) {
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
