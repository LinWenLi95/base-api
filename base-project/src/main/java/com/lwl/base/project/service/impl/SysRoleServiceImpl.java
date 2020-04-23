package com.lwl.base.project.service.impl;

import com.lwl.base.api.common.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lwl.base.project.entity.SysRole;
import com.lwl.base.project.dao.SysRoleMapper;
import com.lwl.base.project.service.SysRoleService;

import java.util.List;

/**
* 系统 角色表Service类
* @author LinWenLi
* @date 2020/04/12
*/
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired(required = false)
    private SysRoleMapper<SysRole> sysRoleMapper;

    @Override
    public BaseMapper<SysRole> getMapper(){
        return sysRoleMapper;
    }

    @Override
    public Integer remove(Integer id) {
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        // 更新逻辑删除字段状态
        sysRole.setIsDel(true);
        return sysRoleMapper.update(sysRole);
    }

    @Override
    public List<SysRole> queryListByUserId(Integer userId) {
        return sysRoleMapper.queryListByUserId(userId);
    }

    @Override
    public List<SysRole> queryListByUrl(String url) {
        return sysRoleMapper.queryListByUrl(url);
    }
}