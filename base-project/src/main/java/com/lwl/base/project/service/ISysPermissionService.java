package com.lwl.base.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.base.api.common.pojo.UrlRole;
import com.lwl.base.project.entity.SysPermission;

import java.util.List;

/**
 * 系统 权限表 服务类
 * @author LinWenLi
 * @since 2020-04-23
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 获取权限url及url对应的角色名
     * @return List<UrlRole>
     */
    List<UrlRole> queryPermissionUrlAndRoleName();
}
