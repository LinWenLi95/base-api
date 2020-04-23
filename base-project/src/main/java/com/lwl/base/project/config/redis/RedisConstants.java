package com.lwl.base.project.config.redis;

/**
 * redis key 常量类，所有rediskey全部加到这个类下，写好作用注释
 * @author linwenli
 * @since 2020-04-14
 */
public class RedisConstants {

    /**Jwt jwt:username:{username}*/
    public static final String JWT_USERNAME = "jwt:username:%s";
    /**url和method组成的key，用于存放url对应的角色名*/
    public static final String URL_METHOD = "url:%s:method:%s";
    /**存放含路径变量{xx}的url列表，用于将url匹配成通配符*的url字符串*/
    public static final String URL_MATCHERS_KEY = "url:mathers:keys";

}
