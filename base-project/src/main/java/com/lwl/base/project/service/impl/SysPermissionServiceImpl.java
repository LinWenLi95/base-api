package com.lwl.base.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.base.api.common.pojo.UrlRole;
import com.lwl.base.project.mapper.SysPermissionMapper;
import com.lwl.base.project.entity.SysPermission;
import com.lwl.base.project.service.ISysPermissionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 系统 权限表 服务实现类
 * @author LinWenLi
 * @since 2020-04-23
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Override
    public List<UrlRole> queryPermissionUrlAndRoleName() {
        List<UrlRole> urlRoles = this.baseMapper.queryPermissionUrlAndRoleName();
        if (urlRoles == null) {
            return Collections.emptyList();
        }
        return urlRoles;
    }
}
