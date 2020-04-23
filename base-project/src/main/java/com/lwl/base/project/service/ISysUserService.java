package com.lwl.base.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwl.base.project.entity.SysUser;

import java.util.Optional;

/**
 * 系统 用户表 服务类
 * @author LinWenLi
 * @since 2020-04-23
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过username获取用户密码
     * @param username 用户名
     * @return SysUser
     */
    Optional<SysUser> queryByUsername(String username);
}
