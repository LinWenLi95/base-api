package com.lwl.base.project.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwl.base.project.dto.GetUserPageDTO;
import com.lwl.base.project.entity.SysUser;
import com.lwl.base.project.vo.GetUserPageVO;
import org.apache.ibatis.annotations.Param;

/**
 * 系统 用户表 Mapper 接口
 * @author LinWenLi
 * @since 2020-04-23
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    Page<GetUserPageVO> page(Page<GetUserPageVO> page, @Param(Constants.WRAPPER) QueryWrapper<SysUser> queryWrapper);

}
