package com.lwl.base.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.base.project.mapper.SysRoleMapper;
import com.lwl.base.project.entity.SysRole;
import com.lwl.base.project.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 系统 角色表 服务实现类
 * @author LinWenLi
 * @since 2020-04-23
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<String> queryRoleNamesByUserId(Serializable userId) {
        List<String> roleNames = this.baseMapper.queryRoleNamesByUserId(userId);
        if (roleNames == null) {
            return Collections.emptyList();
        }
        return roleNames;
    }
}
