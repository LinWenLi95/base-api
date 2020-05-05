package com.lwl.base.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.base.api.common.base.BaseServiceUtils;
import com.lwl.base.api.common.pojo.PageCondition;
import com.lwl.base.api.common.pojo.SortEnum;
import com.lwl.base.api.common.util.StringUtil;
import com.lwl.base.api.common.vo.Result;
import com.lwl.base.project.dto.GetUserPageDTO;
import com.lwl.base.project.entity.SysMenu;
import com.lwl.base.project.mapper.SysUserMapper;
import com.lwl.base.project.entity.SysUser;
import com.lwl.base.project.service.ISysMenuService;
import com.lwl.base.project.service.ISysRoleService;
import com.lwl.base.project.service.ISysUserService;
import com.lwl.base.project.util.JwtUtils;
import com.lwl.base.project.util.RedisUtils;
import com.lwl.base.project.vo.GetUserPageVO;
import com.lwl.base.project.vo.GetUsersInfoVO;
import com.lwl.base.project.vo.MenuRouterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


/**
 * 系统 用户表 服务实现类
 * @author LinWenLi
 * @since 2020-04-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public SysUser queryByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return this.getOne(queryWrapper);
    }

    @Override
    public Result<Object> deleteById(String userId) {
        //判断是不是自身账号
        if (userId.equals(JwtUtils.getUserId())) {
            return Result.error("不能删除自身账号");
        }
        //已配置逻辑删除字段，删除为逻辑删除
        boolean result = this.removeById(userId);
        return result ? Result.ok() : Result.error();
    }

    @Override
    public Result<Page<GetUserPageVO>> getPage(GetUserPageDTO dto, PageCondition condition) {
        Page<GetUserPageVO> pageResult = BaseServiceUtils.getPage(dto, condition, (page, queryWrapper) -> this.baseMapper.getPage(page, queryWrapper));
        return Result.ok(pageResult);
    }

    @Override
    public GetUsersInfoVO getLoginUserInfo() {
        //从token中取出用户id
        String userId = JwtUtils.getUserId();
        Assert.notNull(userId,"未获取到用户id，请先登录");
        //从redis中取
        GetUsersInfoVO vo = null;//RedisUtils.getT("存放指定用户的用户信息的key");
        if (vo == null) {
            //查询用户数据
            SysUser user = this.queryByUsername(userId);
//        SysUser user = this.getById(userId);
            Assert.notNull(user,"未获取到用户信息");
            //查询用户角色
            List<String> roles = sysRoleService.queryRoleNamesByUserId(user.getId());
            //查询菜单路由
            List<SysMenu> list = sysMenuService.queryUserMenus(userId);
            List<MenuRouterVO> menus = getRouterTrees(list);
            //组成响应数据
            vo = new GetUsersInfoVO(user.getNickName(), user.getAvatar(), roles, menus);
            // 存入redis
//            RedisUtils.set("存放指定用户的用户信息的key", vo);
        }
        return vo;
    }

    /**
     * 列表转树
     * @param treeNodes
     * @return
     */
    public List<MenuRouterVO> getRouterTrees(List<SysMenu> treeNodes) {
        //菜单根节点为0
        return findChild(0, treeNodes);
    }

    /**
     * 获取指定节点的子节点列表
     * @param parentNodeId 父节点id
     * @param nodes        节点列表
     * @return 子节点列表
     */
    public List<MenuRouterVO> findChild(Integer parentNodeId, List<SysMenu> nodes) {
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
                tree.setChildren(findChild(menu.getId(), nodes));
                child.add(tree);
            }
        }
        return child;
    }
}
