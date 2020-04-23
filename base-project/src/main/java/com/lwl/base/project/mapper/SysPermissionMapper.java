package com.lwl.base.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwl.base.api.common.pojo.UrlRole;
import com.lwl.base.project.entity.SysPermission;

import java.util.List;

/**
 * 系统 权限表 Mapper 接口
 * @author LinWenLi
 * @since 2020-04-23
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 获取权限url及url对应的角色名
     * @return List<UrlRole>
     */
    List<UrlRole> queryPermissionUrlAndRoleName();
}
