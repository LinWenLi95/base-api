package com.lwl.base.project.service;

import com.lwl.base.api.common.vo.Result;
import com.lwl.base.project.dto.PostMenusDTO;
import com.lwl.base.project.dto.PutMenusDTO;
import com.lwl.base.project.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.base.project.vo.GetMenuVO;
import com.lwl.base.project.vo.MenuRouterVO;
import com.lwl.base.project.vo.MenuTree;

import java.util.List;

/**
 * 系统 菜单 服务类
 * @author LinWenLi
 * @since 2020-05-05
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 查询用户菜单
     * @param userId 用户id
     * @return List<SysMenu>
     */
    List<SysMenu> queryUserMenus(String userId);

    /**
     * 列表转路由菜单树
     * @param treeNodes 菜单信息列表
     * @return List<MenuRouterVO>
     */
    List<MenuRouterVO> getRouterTrees(List<SysMenu> treeNodes);

    /**
     * 获取菜单树
     * @return List<MenuTree>
     */
    List<MenuTree> getMenuTrees();

    /**
     * 更新菜单节点信息
     * @param dto 入参
     * @return Result<Object>
     */
    Boolean putMenus(PutMenusDTO dto);

    GetMenuVO getSysMenuById(String id);

    Boolean postMenus(PostMenusDTO dto);
}
