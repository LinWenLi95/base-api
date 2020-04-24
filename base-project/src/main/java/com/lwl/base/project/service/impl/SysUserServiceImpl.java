package com.lwl.base.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.base.api.common.pojo.PageCondition;
import com.lwl.base.api.common.pojo.SortEnum;
import com.lwl.base.api.common.vo.Result;
import com.lwl.base.project.dto.GetUserPageDTO;
import com.lwl.base.project.mapper.SysUserMapper;
import com.lwl.base.project.entity.SysUser;
import com.lwl.base.project.service.ISysUserService;
import com.lwl.base.project.util.JwtUtils;
import com.lwl.base.project.vo.GetUserPageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * 系统 用户表 服务实现类
 * @author LinWenLi
 * @since 2020-04-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser queryByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return this.getOne(queryWrapper);
    }

    @Override
    public Result<Object> deleteById(String userId) {
        //判断是不是自身账号
        if (userId.equals(JwtUtils.getUserId())) {
            return Result.error("不能删除自身账号");
        }
        //已配置逻辑删除字段，删除为逻辑删除
        boolean result = this.removeById(userId);
        return result ? Result.ok() : Result.error();
    }

    @Override
    public Result<Page<GetUserPageVO>> getUserPage(GetUserPageDTO dto, PageCondition condition) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(dto, sysUser);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>(sysUser);
        //排序
        if (!StringUtils.isEmpty(condition.getOrderBy())) {
            queryWrapper.orderByAsc(condition.getSort() == SortEnum.ASC, condition.getOrderBy());
            queryWrapper.orderByDesc(condition.getSort() == SortEnum.DESC, condition.getOrderBy());
        }
        //分页
        Page<GetUserPageVO> page = new Page<>();
        if (condition.getCurrent() != null) {
            page.setCurrent(condition.getCurrent());
        }
        if (condition.getLimit() != null) {
            page.setSize(condition.getLimit());
        }
//        this.page(page, queryWrapper);https://www.cnblogs.com/mozq/p/11755375.html
        return Result.ok();
    }
}
