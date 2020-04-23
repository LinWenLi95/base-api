package com.lwl.base.project.dao;

import com.lwl.base.api.common.base.BaseMapper;
import com.lwl.base.project.entity.SysPermission;
import com.lwl.base.project.entity.UrlRole;

import java.util.List;

/**
* 系统 权限表数据库操作接口类
* @author LinWenLi
* @date 2020/04/12
*/
public interface SysPermissionMapper<T extends SysPermission> extends BaseMapper<T> {
    /**
     * 获取用户权限信息列表
     * @param userId 用户id
     * @return List<SysPermission>
     */
    List<SysPermission> queryByUserId(Integer userId);

    List<UrlRole> queryPermissionUrlAndRoleName();
}