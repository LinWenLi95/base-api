package com.lwl.base.project.dao;

import com.lwl.base.api.common.base.BaseMapper;
import com.lwl.base.project.entity.SysUser;

/**
* 系统 用户表数据库操作接口类
* @author LinWenLi
* @date 2020/04/12
*/
public interface SysUserMapper<T extends SysUser> extends BaseMapper<T> {
    /**
     * 通过用户名查询实体
     * @param userName 用户名
     * @return SysUser
     */
    SysUser selectByName(String userName);
}