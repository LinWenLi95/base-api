package com.lwl.base.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwl.base.project.entity.SysRole;

import java.io.Serializable;
import java.util.List;

/**
 * 系统 角色表 Mapper 接口
 * @author LinWenLi
 * @since 2020-04-23
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询角色名列表
     * @param userId 用户id
     * @return roleNames列表
     */
    List<String> queryRoleNamesByUserId(Serializable userId);
}
