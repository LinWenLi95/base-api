package com.lwl.base.project.controller;


import com.lwl.base.api.common.vo.Result;
import com.lwl.base.project.entity.SysUser;
import com.lwl.base.project.service.ISysUserService;
import com.lwl.base.project.util.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 系统 用户表 前端控制器
 * @author LinWenLi
 * @since 2020-04-23
 */
@RestController
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation(value = "查询用户信息",notes = "查询单条数据")
    @GetMapping("/users/{id}")
    public Result<Object> getUser(@PathVariable("id") String userId) {
        return Result.ok(sysUserService.getById(userId));
    }

    @ApiOperation(value = "查询用户信息列表",notes = "查询多条数据")
    @GetMapping("/users")
    public Result<Object> getUsers() {
        return Result.ok(sysUserService.getMap(null));
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

    @ApiOperation(value = "删除用户信息",notes = "删除一条数据")
    @DeleteMapping("/users")
    public Result<Object> removeUser(@RequestBody String userId,
                                     @RequestHeader String token) {
        Object username = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userId.equals(JwtUtils.getUserNameFormToken(token))) {
            return Result.error();
        }
        boolean result = sysUserService.removeById(userId);
        return result ? Result.ok() : Result.error();
    }

}
