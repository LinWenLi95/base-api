package com.lwl.base.project.service.impl;

import com.lwl.base.api.common.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lwl.base.project.entity.pojo.SysRolePermission;
import com.lwl.base.project.dao.SysRolePermissionMapper;
import com.lwl.base.project.service.SysRolePermissionService;

/**
* 系统 角色-权限表Service类
* @author LinWenLi
* @date 2020/04/18
*/
@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

    @Autowired(required = false)
    private SysRolePermissionMapper<SysRolePermission> sysRolePermissionMapper;

    @Override
    public BaseMapper<SysRolePermission> getMapper(){
        return sysRolePermissionMapper;
    }

    @Override
    public Integer remove(Integer id) {
        SysRolePermission sysRolePermission = new SysRolePermission();
        sysRolePermission.setId(id);
        // 更新逻辑删除字段状态
        sysRolePermission.setIsDel(true);
        return sysRolePermissionMapper.update(sysRolePermission);
    }
}