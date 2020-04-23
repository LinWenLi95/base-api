package com.lwl.base.project.controller;

import com.lwl.base.api.common.util.HttpRequestUtil;
import com.lwl.base.api.common.vo.Page;
import com.lwl.base.api.common.vo.Result;
import com.lwl.base.api.common.vo.ResultCode;
import com.lwl.base.project.entity.SysRolePermission;
import com.lwl.base.project.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统 角色-权限表Controller
 * @author LinWenLi
 * @date 2020/04/18
 */
@RestController
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController {

    @Autowired
    SysRolePermissionService sysRolePermissionService;


    /**http://localhost:8080/sysRolePermissions?current=1&limit=10&order_by=id&sort=asc
     * 查询多条数据
     * @param request 请求对象，以下请求参数：<br/>
     * T对象的属性名（可选,所有属性都可作为条件）<br/>
     * current 当前页码（可选，必须与limit配合使用）<br/>
     * limit 取出数量（可选，可单独使用）<br/>
     * order_by 排序字段（可选，必须与sort配合使用）<br/>
     * sort 排序方式（可选，值选填：asc|desc，必须与order_by配合使用）
     * @return Result<Page<SysRolePermission>>
     */
    @GetMapping("/")
    public Result<Page<SysRolePermission>> queryList(HttpServletRequest request) {
        // 将请求参数集合取出
        Map<String, Object> parameterMap = HttpRequestUtil.getParameterMap(request);
        Page<SysRolePermission> page = sysRolePermissionService.queryPage(parameterMap, null);
        return Result.success(page);
    }

    /**
     * 查询单条数据
     * @param id 主键
     * @return Result<SysRolePermission>
     */
    @GetMapping("/{id}")
    public Result<SysRolePermission> queryOne(@PathVariable("id") Integer id) {
        Optional<SysRolePermission> t = sysRolePermissionService.queryById(id, null);
        return Result.success(t.get());
    }

    /**
     * 新增数据
     * @param obj 要添加的参数
     * @return Result<Object>
     */
    @PostMapping("/")
    public Result<Object> add(@RequestBody SysRolePermission obj) {
        Integer result = 0;
        if (obj != null) {
            result = sysRolePermissionService.add(obj);
        }
        return result > 0 ? Result.success() : Result.success(ResultCode.UNEXPECTED_RESULTS);
    }

    /**
     * 更新数据
     * @param obj 要更新的参数
     * @return Result<Object>
     */
    @PutMapping("/")
    public Result<Object> update(@RequestBody SysRolePermission obj) {
        Integer result = 0;
        if (obj != null) {
            result = sysRolePermissionService.edit(obj);
        }
        return result == 1 ? Result.success() : Result.success(ResultCode.UNEXPECTED_RESULTS);
    }

    /**
     * 删除数据
     * @param id 主键
     * @return Result<Object>
     */
    @DeleteMapping("/{id}")
    public Result<Object> del(@PathVariable("id") Integer id) {
        Integer result = 0;
        if (id != null) {
            result = sysRolePermissionService.remove(id);
        }
        return result == 1 ? Result.success() : Result.success(ResultCode.UNEXPECTED_RESULTS);
    }
}