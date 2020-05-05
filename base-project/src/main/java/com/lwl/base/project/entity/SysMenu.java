package com.lwl.base.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统 菜单
 * @author LinWenLi
 * @since 2020-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父菜单id（一级菜单的父id默认为0）
     */
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
     * 是否已删除
     */
    private Boolean isDel;

    /**
     * 备注
     */
    private String description;

    /**
     * 创建者id
     */
    private Integer creatorId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者id
     */
    private Integer updaterId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
