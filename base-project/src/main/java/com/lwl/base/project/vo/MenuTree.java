package com.lwl.base.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单树对象 el-tree标签的数据结构
 * @author lwl
 */
@Data
@ApiModel(description = "菜单树对象 el-tree标签的数据结构")
public class MenuTree {

    @ApiModelProperty(value = "菜单id",example = "1")
    private Integer id;
    /**父菜单id（一级菜单的父id默认为0）*/
    @ApiModelProperty(value = "父菜单id（一级菜单的父id默认为0）",example = "0")
    private Integer parentId;
    /**菜单名称*/
    @ApiModelProperty(value = "菜单名称",example = "系统设置")
    private String name;
    @ApiModelProperty(value = "子菜单树列表")
    private List<MenuTree> children;

}
