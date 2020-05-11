package com.lwl.base.project.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 * @author LinWenLi
 * @since 2020-04-24
 */
@Component
public class RedisUtils {

    private static RedisTemplate<String, Object> redisTemplate;

    /**1秒*/
    public static final Long ONE_SECOND = 1L;
    /*30秒*/
    public static final Long HALF_MINUTE = 30L;
    /*1分钟*/
    public static final Long ONE_MINUTE = 60L;
    /*30分钟*/
    public static final Long HALF_HOUR = 1800L;
    /*1小时*/
    public static final Long ONE_HOUR = 3600L;
    /*1天*/
    public static final Long ONE_DAY = 86400L;
    /*30天*/
    public static final Long ONE_MONTH = 2592000L;

    @Resource
    private void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    /**
     * 若key存在则覆盖value
     */
    public static Boolean set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return redisTemplate.hasKey(key);
    }
    /**
     * 若key存在则覆盖value(设置有效时间)
     */
    public static Boolean set(String key, Object value, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
        return redisTemplate.hasKey(key);
    }

    /**
     * 若key不存在则设置
     */
    public static Boolean setIfAbsent(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 若key不存在则设置(设置有效时间)
     */
    public static Boolean setIfAbsent(String key, Object value, Long time, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    /**
     * 获取value（Object）
     */
    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取value（泛型）
     */
    public static <T> T getT(String key) {
        Object data = redisTemplate.opsForValue().get(key);
        return data == null ? null : (T)data;
    }

    /**
     * 获取value（String）
     */
    public static String getString(String key) {
        Object data = redisTemplate.opsForValue().get(key);
        return data == null ? null : String.valueOf(data);
    }

    /**
     * 获取value（Double）
     */
    public static Double getDouble(String key) {
        String data = getString(key);
        return data == null ? null : Double.parseDouble(data);
    }

    /**
     * 获取value（Long）
     */
    public static Long getLong(String key) {
        String data = getString(key);
        return data == null ? null : Long.parseLong(data);
    }

    /**
     * 获取指定key的剩余过期时间（单位：秒）
     */
    public static Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取匹配指定前缀的key集合(建议使用scan进行key查找)
     * @param pattern key前缀，格式：prefix + *
     */
    public static Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除指定key
     */
    public static Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     */
    public static Long delete(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 删除匹配指定前缀的key
     * @param pattern key前缀，格式：prefix + *
     */
    public static Long deleteByPattern(String pattern) {
        Set<String> keys = RedisUtils.keys(pattern);
        if (keys != null && keys.size() > 0) {
            return redisTemplate.delete(keys);
        }
        return 0L;
    }

    public static void hset(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public static Object hget(String key,String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public static String hgetString(String key,Object hashKey) {
        Object data = redisTemplate.opsForHash().get(key, hashKey);
        return data == null ? null : String.valueOf(data);
    }

    public static Long zset(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    public static Set<Object> zget(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public static Set<String> zgetString(String key) {
        Set<Object> zget = zget(key);
        Set<String> roleNameSet = new HashSet<>();
        for (Object o : zget) {
            roleNameSet.add((String)o);
        }
        return roleNameSet;
    }

    public static Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
