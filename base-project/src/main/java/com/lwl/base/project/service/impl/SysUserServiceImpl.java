package com.lwl.base.project.service.impl;

import com.lwl.base.api.common.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lwl.base.project.entity.pojo.SysUser;
import com.lwl.base.project.dao.SysUserMapper;
import com.lwl.base.project.service.SysUserService;
import org.springframework.util.StringUtils;

/**
* 系统 用户表Service类
* @author LinWenLi
* @date 2020/04/12
*/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired(required = false)
    private SysUserMapper<SysUser> sysUserMapper;

    @Override
    public BaseMapper<SysUser> getMapper(){
        return sysUserMapper;
    }

    @Override
    public Integer remove(Integer id) {
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        // 更新逻辑删除字段状态
        sysUser.setIsDel(true);
        return sysUserMapper.update(sysUser);
    }

    @Override
    public SysUser queryByName(String userName) {
        if (!StringUtils.isEmpty(userName)) {
            return sysUserMapper.selectByName(userName);
        }
        return null;
    }
}