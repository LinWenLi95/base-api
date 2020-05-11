package com.lwl.base.project.vo;

import lombok.Data;

/**
 * 菜单树节点 数据传输实体
 */
@Data
public class MenuTreeNode {

    private Integer id;
    /**父菜单id（一级菜单的父id默认为0）*/
    private Integer parentId;
    /**菜单中文名称*/
    private String name;

}
