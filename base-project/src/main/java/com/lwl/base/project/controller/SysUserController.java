package com.lwl.base.project.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwl.base.api.common.pojo.PageCondition;
import com.lwl.base.api.common.vo.Result;
import com.lwl.base.project.dto.GetUserPageDTO;
import com.lwl.base.project.entity.SysUser;
import com.lwl.base.project.service.ISysUserService;
import com.lwl.base.project.util.JwtUtils;
import com.lwl.base.project.vo.GetUserPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 系统 用户表 前端控制器
 * @author LinWenLi
 * @since 2020-04-23
 */
@Api(value = "用户表操作")
@RestController
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return Result<SysUser>
     */
    @ApiOperation(value = "查询用户信息",notes = "查询单条数据")
    @GetMapping("/users/{id}")
    public Result<SysUser> getUser(@PathVariable("id") String userId) {
        return Result.ok(sysUserService.getById(userId));
    }

    @ApiOperation(value = "查询用户信息列表", notes = "查询多条数据")
    @GetMapping("/users")
    public Result<Page<GetUserPageVO>> getUserPage(GetUserPageDTO dto, PageCondition condition) {
        return sysUserService.getPage(dto, condition);
    }

    @ApiOperation(value = "添加用户",notes = "添加一条数据")
    @PostMapping("/users")
    public Result<Object> addUser() {
        return Result.ok(sysUserService.getMap(null));
    }

    @ApiOperation(value = "更新用户信息",notes = "更新一条数据")
    @PutMapping("/users")
    public Result<Object> editUser() {
        return Result.ok(sysUserService.getMap(null));
    }

    /**
     * 删除用户信息
     * @param userId 用户id
     * @return Result<Object>
     */
    @ApiOperation(value = "删除用户信息",notes = "删除一条数据")
    @DeleteMapping("/users/{id}")
    public Result<Object> deleteUser(@PathVariable("id") String userId) {
        return sysUserService.deleteById(userId);
    }
}
