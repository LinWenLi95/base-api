package com.lwl.base.project.config.redis;

/**
 * redis key 常量类，所有rediskey全部加到这个类下，写好作用注释
 * @author linwenli
 * @date 2020/04/14
 */
public class RedisConstants {

    /**权限 permission:{permissionId}:{url}:{method}*/
    public static final String PERMISSION = "permission:id:%s:%s:%s";
    /**角色-权限 role_permission:{roleId}:{permissionId}*/
    public static final String ROLE_PERMISSION = "role_permission:%s:%s";
    /**角色 role:{roleId}:{roleName}*/
    public static final String ROLE = "role:%s:%s";
    /**Jwt jwt:{username}*/
    public static final String JWT_USERNAME = "jwt:%s";

    public static final String URL_METHOD = "url:%s:method:%s";

}
