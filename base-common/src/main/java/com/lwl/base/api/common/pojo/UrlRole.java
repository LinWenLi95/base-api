package com.lwl.base.api.common.pojo;

import lombok.Data;

/**
 * 存储权限url及url对应的角色信息
 * @author LinWenLi
 * @date 2020-04-19
 */
@Data
public class UrlRole {

    /**权限id*/
    private String permissionId;
    /**访问资源url*/
    private String url;
    /**请求方法 GET|POST|PUT|DELETE*/
    private String method;
    /**角色id*/
    private String roleId;
    /**角色名*/
    private String roleName;
}
