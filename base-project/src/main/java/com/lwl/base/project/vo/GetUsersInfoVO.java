package com.lwl.base.project.vo;

import lombok.Data;

import java.util.List;

/**
 * @author LinWenLi
 * @since 2020-05-05
 */
@Data
public class GetUsersInfoVO {

    /**用户显示名称（昵称）*/
    private String name;
    /**用户角色英文名*/
    private List<String> roles;
    /**用户头像*/
    private String avatar;
    private List<MenuRouterVO> menus;

    public GetUsersInfoVO(String name, String avatar, List<String> roles,List<MenuRouterVO> menus) {
        this.name = name;
        this.avatar = avatar;
        this.roles = roles;
        this.menus = menus;
    }
}
