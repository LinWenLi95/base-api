package com.lwl.base.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.base.project.mapper.SysUserMapper;
import com.lwl.base.project.entity.SysUser;
import com.lwl.base.project.service.ISysUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 系统 用户表 服务实现类
 * @author LinWenLi
 * @since 2020-04-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public Optional<SysUser> queryByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Optional.ofNullable(this.getOne(queryWrapper));
    }
}
