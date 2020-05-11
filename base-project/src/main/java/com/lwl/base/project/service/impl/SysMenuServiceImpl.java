package com.lwl.base.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lwl.base.api.common.vo.Result;
import com.lwl.base.project.dto.PostMenusDTO;
import com.lwl.base.project.dto.PutMenusDTO;
import com.lwl.base.project.entity.SysMenu;
import com.lwl.base.project.mapper.SysMenuMapper;
import com.lwl.base.project.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.base.project.util.JwtUtils;
import com.lwl.base.project.vo.GetMenuVO;
import com.lwl.base.project.vo.MenuRouterVO;
import com.lwl.base.project.vo.MenuTree;
import com.lwl.base.project.vo.MenuTreeNode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 系统 菜单 服务实现类
 * @author LinWenLi
 * @since 2020-05-05
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Override
    public List<SysMenu> queryUserMenus(String userId) {
        List<SysMenu> list = this.baseMapper.queryUserMenus(userId);
        return list == null ? Collections.emptyList() : list;
    }

    /**
     * 菜单列表转菜单路由树
     * @param treeNodes
     * @return
     */
    @Override
    public List<MenuRouterVO> getRouterTrees(List<SysMenu> treeNodes) {
        //菜单根节点为0
        return findRouterChild(0, treeNodes);
    }

    @Override
    public List<MenuTree> getMenuTrees() {
        List<SysMenu> sysMenus = this.list();
        // 转数据格式
        return findTreeChild(0, sysMenus);
    }

    /**
     * 获取指定菜单路由节点的子节点列表
     * @param parentNodeId 父节点id
     * @param nodes        节点列表
     * @return 子节点列表
     */
    public List<MenuRouterVO> findRouterChild(Integer parentNodeId, List<SysMenu> nodes) {
        List<MenuRouterVO> child = new ArrayList<>();
        // 迭代递归
        for (SysMenu menu : nodes) {
            if (menu.getParentId().equals(parentNodeId)) {
                // 迭代器必须在递归之前执行remove，这样在当次递归时传入的nodes数量才会-1，如果不执行remove，最终循环次数固定
                MenuRouterVO tree = new MenuRouterVO();
                tree.setId(menu.getId());
                tree.setPid(menu.getParentId());
                tree.setPath(menu.getUrl());
                tree.setComponent(menu.getUrl());
                tree.setName(menu.getName());
                tree.setMeta(menu.getName(), menu.getIcon());
                tree.setChildren(findRouterChild(menu.getId(), nodes));
                child.add(tree);
            }
        }
        return child;
    }

    /**
     * 获取指定菜单树节点的子节点列表
     *
     * @param parentNodeId 父节点id
     * @param nodes        节点列表
     * @return 子节点列表
     */
    public List<MenuTree> findTreeChild(Integer parentNodeId, List<SysMenu> nodes) {
        List<MenuTree> child = new ArrayList<>();
        // 迭代递归
        for (SysMenu treeNode : nodes) {
            if (treeNode.getParentId().equals(parentNodeId)) {
                // 迭代器必须在递归之前执行remove，这样在当次递归时传入的nodes数量才会-1，如果不执行remove，最终循环次数固定
                MenuTree tree = new MenuTree();
                BeanUtils.copyProperties(treeNode, tree);
                tree.setChildren(findTreeChild(treeNode.getId(), nodes));
                child.add(tree);
            }
        }
        return child;
    }

    @Override
    public Boolean putMenus(PutMenusDTO dto) {
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(dto, menu);
        menu.setUpdateTime(LocalDateTime.now());
        menu.setUpdaterId(JwtUtils.getUserId());
        return this.updateById(menu);
    }

    @Override
    public GetMenuVO getSysMenuById(String id) {
        SysMenu menu = this.getById(id);
        Assert.notNull(menu,"未查询到菜单信息");
        GetMenuVO vo = new GetMenuVO();
        BeanUtils.copyProperties(menu, vo);
        return vo;
    }

    @Override
    public Boolean postMenus(PostMenusDTO dto) {
        SysMenu menu = new SysMenu();
        BeanUtils.copyProperties(dto, menu);
        menu.setIsDel(false);
        menu.setCreatorId(JwtUtils.getUserId());
        menu.setCreateTime(LocalDateTime.now());
        return this.save(menu);
    }
}
