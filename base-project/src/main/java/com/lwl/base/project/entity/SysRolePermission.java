package com.lwl.base.project.entity;

import java.io.Serializable;
import lombok.Data;
import java.sql.Timestamp;

/**
* 系统 角色-权限表
* @author LinWenLi
* @date 2020/04/18
*/
@Data
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /***/
    private Integer id;
    /**角色ID*/
    private Integer roleId;
    /**权限ID*/
    private Integer permissionId;
    /**描述*/
    private String description;
    /**创建者id*/
    private Integer creatorId;
    /**创建时间*/
    private Timestamp createTime;
    /**是否已删除*/
    private Boolean isDel;
}
