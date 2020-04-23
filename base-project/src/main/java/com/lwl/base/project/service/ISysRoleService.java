package com.lwl.base.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.base.project.entity.SysRole;

import java.util.List;

/**
 * 系统 角色表 服务类
 * @author LinWenLi
 * @since 2020-04-23
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 查询角色名列表
     * @param userId 用户id
     * @return roleNames列表
     */
    List<String> queryRoleNamesByUserId(Integer userId);
}
