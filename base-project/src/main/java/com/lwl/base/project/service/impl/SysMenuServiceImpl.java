package com.lwl.base.project.service.impl;

import com.lwl.base.project.entity.SysMenu;
import com.lwl.base.project.mapper.SysMenuMapper;
import com.lwl.base.project.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 系统 菜单 服务实现类
 * @author LinWenLi
 * @since 2020-05-05
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Override
    public List<SysMenu> queryUserMenus(String userId) {
        List<SysMenu> list = this.baseMapper.queryUserMenus(userId);
        return list == null ? Collections.emptyList() : list;
    }
}
