package com.lwl.base.project.service;

import com.lwl.base.project.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 系统 菜单 服务类
 * @author LinWenLi
 * @since 2020-05-05
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 查询用户菜单
     * @param userId
     * @return
     */
    List<SysMenu> queryUserMenus(String userId);
}
