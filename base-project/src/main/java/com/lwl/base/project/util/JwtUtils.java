package com.lwl.base.project.util;

import com.lwl.base.project.config.redis.RedisConstants;
import com.lwl.base.project.config.security.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

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
    public static Integer getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.notNull(principal,"获取认证主体失败");
        String token = RedisUtils.getString(String.format(RedisConstants.JWT_USERNAME, principal));
        Assert.notNull(principal,"获取认证主体失败");
        return getBodyValue(token, "uid", Integer.class);
    }

    /**
     * 获取主要认证信息，用于获取username
     */
    public static String getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Assert.notNull(principal,"获取认证主体失败");
        return String.valueOf(principal);
    }

    /**
     * @param token
     * @param bodyKey
     * @return
     */
    public static <T> T getBodyValue(String token, String bodyKey, Class<T> clazz) {
        Jws<Claims> jwsClaims = getJwsClaimsByToken(token);
        // 获取用户名及授权列表
        Claims body = jwsClaims.getBody();
        return body.get(bodyKey, clazz);
    }

    public static Jws<Claims> getJwsClaimsByToken(String token) {
        // 使用密钥解析JWT
        Jws<Claims> jwsClaims;
        try {
            jwsClaims = Jwts.parserBuilder()
                    .setSigningKey(SecurityConstants.JWT_SECRET.getBytes())
                    .build().parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw e;
        }
        Assert.notNull(jwsClaims, "JWT为空");
        return jwsClaims;
    }


}
