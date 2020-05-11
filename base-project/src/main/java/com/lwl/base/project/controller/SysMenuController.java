package com.lwl.base.project.controller;


import com.lwl.base.api.common.vo.Result;
import com.lwl.base.project.dto.PostMenusDTO;
import com.lwl.base.project.dto.PutMenusDTO;
import com.lwl.base.project.entity.SysMenu;
import com.lwl.base.project.service.ISysMenuService;
import com.lwl.base.project.vo.GetMenuVO;
import com.lwl.base.project.vo.MenuTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 系统 菜单 前端控制器
 * @author LinWenLi
 * @since 2020-05-05
 */
@Api(value = "系统 菜单")
@RestController
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 获取菜单树列表
     */
    @ApiOperation(value = "获取菜单树列表")
    @GetMapping("/menus/trees")
    public Result<List<MenuTree>> getMenusTree() {
        return Result.ok(sysMenuService.getMenuTrees());
    }

    /**
     * 获取单个菜单信息
     * @param id 菜单id
     */
    @ApiOperation(value = "获取单个菜单信息")
    @GetMapping("/menus/{id}")
    public Result<GetMenuVO> getSysMenu(@PathVariable("id") String id) {
        return Result.ok(sysMenuService.getSysMenuById(id));
    }

    /**
     * 更新菜单节点信息
     * @param dto 入参
     */
    @ApiOperation(value = "更新菜单节点信息")
    @PutMapping("/menus")
    public Result<Object> putMenus(@Valid @RequestBody PutMenusDTO dto) {
        boolean result = sysMenuService.putMenus(dto);
        return result ? Result.ok() : Result.error();
    }

    /**
     * 新增菜单节点信息
     * @param dto 入参
     */
    @ApiOperation(value = "新增菜单节点信息")
    @PostMapping("/menus")
    public Result<Object> postMenus(@Valid @RequestBody PostMenusDTO dto) {
        boolean result = sysMenuService.postMenus(dto);
        return result ? Result.ok() : Result.error();
    }

    /**
     * 新增菜单节点信息
     * @param id 菜单id
     */
    @ApiOperation(value = "新增菜单节点信息")
    @DeleteMapping("/menus/{id}")
    public Result<Object> deleteMenus(@PathVariable("id") String id) {
        boolean result = sysMenuService.removeById(id);
        return result ? Result.ok() : Result.error();
    }
}
