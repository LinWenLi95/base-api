package com.lwl.base.project.service.impl;

import com.lwl.base.api.common.base.BaseMapper;
import com.lwl.base.project.entity.UrlRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lwl.base.project.entity.SysPermission;
import com.lwl.base.project.dao.SysPermissionMapper;
import com.lwl.base.project.service.SysPermissionService;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
* 系统 权限表Service类
* @author LinWenLi
* @date 2020/04/12
*/
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired(required = false)
    private SysPermissionMapper<SysPermission> sysPermissionMapper;

    @Override
    public BaseMapper<SysPermission> getMapper(){
        return sysPermissionMapper;
    }

    @Override
    public Integer remove(Integer id) {
        SysPermission sysPermission = new SysPermission();
        sysPermission.setId(id);
        // 更新逻辑删除字段状态
        sysPermission.setIsDel(true);
        return sysPermissionMapper.update(sysPermission);
    }

    @Override
    public List<SysPermission> queryByUserId(@NotNull Integer userId) {
        if (userId != null) {
            return sysPermissionMapper.queryByUserId(userId);
        }
        return Collections.emptyList();
    }

    @Override
    public List<UrlRole> queryPermissionUrlAndRoleName() {
        return sysPermissionMapper.queryPermissionUrlAndRoleName();
    }
}