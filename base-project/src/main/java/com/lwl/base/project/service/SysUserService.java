package com.lwl.base.project.service;

import com.lwl.base.api.common.base.BaseService;
import com.lwl.base.project.entity.pojo.SysUser;

/**
* 系统 用户表Service接口
* @author LinWenLi
* @date 2020/04/12
*/
public interface SysUserService extends BaseService<SysUser>{
    /**
     * 通过用户名查询实体（身份认证时使用）
     * @param userName 用户名
     * @return SysUser
     */
    SysUser queryByName(String userName);
}