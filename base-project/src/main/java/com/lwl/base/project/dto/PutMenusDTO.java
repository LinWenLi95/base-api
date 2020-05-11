package com.lwl.base.project.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * SysMenu的子集
 * @author LinWenLi
 * @since 2020-05-08
 */
@Data
public class PutMenusDTO {

    @NotNull(message = "id不能为空")
    private Integer id;

    /**
     * 父菜单id（一级菜单的父id默认为0）
     */
    @NotNull(message = "parentId不能为空")
    private Integer parentId;

    /**
     * 菜单中文名称
     */
    private String name;

    /**
     * 菜单英文名称
     */
    private String enname;

    /**
     * url地址(用于区别菜单路由及加载页面组件)
     */
    private String url;

    /**
     * 菜单图标样式
     */
    private String icon;

    /**
     * 状态 0禁用,1启动
     */
    private Boolean state;

    /**
     * 备注
     */
    private String description;
}
