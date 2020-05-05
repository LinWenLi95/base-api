package com.lwl.base.project.mapper;

import com.lwl.base.project.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 系统 菜单 Mapper 接口
 * @author LinWenLi
 * @since 2020-05-05
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> queryUserMenus(String userId);
}
