package com.lwl.base.project.controller;

import com.lwl.base.api.common.util.HttpRequestUtil;
import com.lwl.base.api.common.vo.Page;
import com.lwl.base.api.common.vo.Result;
import com.lwl.base.api.common.vo.ResultCode;
import com.lwl.base.project.dto.GetPermissionPageDTO;
import com.lwl.base.project.entity.SysPermission;
import com.lwl.base.project.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统 权限表Controller
 * @author LinWenLi
 * @date 2020/04/12
 */
@Api(value = "权限接口",tags = "权限接口")
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {

    @Autowired
    SysPermissionService sysPermissionService;


    /**http://localhost:8080/sysPermissions?current=1&limit=10&order_by=id&sort=asc
     * 查询多条数据
     * T对象的属性名（可选,所有属性都可作为条件）<br/>
     * current 当前页码（可选，必须与limit配合使用）<br/>
     * limit 取出数量（可选，可单独使用）<br/>
     * order_by 排序字段（可选，必须与sort配合使用）<br/>
     * sort 排序方式（可选，值选填：asc|desc，必须与order_by配合使用）
     * @return Result<Page<SysPermission>>
     */
    @ApiOperation(value = "查询权限列表",notes = "接口描述")
    @GetMapping("/")
    public Result<Page<SysPermission>> queryList(GetPermissionPageDTO dto,
                                                 @RequestHeader("Authortication")String token) {
        // 将请求参数集合取出
//        Map<String, Object> parameterMap = HttpRequestUtil.getParameterMap(request);
//        Page<SysPermission> page = sysPermissionService.queryPage(parameterMap, null);
//        return Result.success(page);
        return null;
    }

    /**
     * 查询单条数据
     * @param id 主键
     * @return Result<SysPermission>
     */
    @GetMapping("/{id}")
    public Result<SysPermission> queryOne(@PathVariable("id") Integer id) {
        Optional<SysPermission> t = sysPermissionService.queryById(id, null);
        return Result.success(t.get());
    }

    /**
     * 新增数据
     * @param obj 要添加的参数
     * @return Result<Object>
     */
    @PostMapping("/")
    public Result<Object> add(@RequestBody SysPermission obj) {
        Integer result = 0;
        if (obj != null) {
            result = sysPermissionService.add(obj);
        }
        return result > 0 ? Result.success() : Result.success(ResultCode.UNEXPECTED_RESULTS);
    }

    /**
     * 更新数据
     * @param obj 要更新的参数
     * @return Result<Object>
     */
    @PutMapping("/")
    public Result<Object> update(@RequestBody SysPermission obj) {
        Integer result = 0;
        if (obj != null) {
            result = sysPermissionService.edit(obj);
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
            result = sysPermissionService.remove(id);
        }
        return result == 1 ? Result.success() : Result.success(ResultCode.UNEXPECTED_RESULTS);
    }
}