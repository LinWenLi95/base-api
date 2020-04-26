package com.lwl.base.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwl.base.api.common.pojo.PageCondition;
import com.lwl.base.api.common.pojo.SortEnum;
import com.lwl.base.api.common.util.StringUtil;
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

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;


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
    public Result<Page<GetUserPageVO>> getPage(GetUserPageDTO dto, PageCondition condition) {
        Page<GetUserPageVO> pageResult = getPage(dto, condition, (page, queryWrapper) -> this.baseMapper.getPage(page, queryWrapper));
        return Result.ok(pageResult);
    }

    /**
     * 分页列表
     * @param dto 过滤条件
     * @param condition 分页条件
     * @param function 函数
     * @param <T> 泛型
     * @param <R> 泛型
     * @return Page<R>
     */
    public <T, R> Page<R> getPage(T dto, PageCondition condition, BiFunction<Page<R>, QueryWrapper<T>, Page<R>> function) {
        QueryWrapper<T> queryWrapper = Wrappers.query();
        //条件
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(dto));
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (entry.getValue() != null) {
                queryWrapper.eq(StringUtil.camelCaseToUnderscore(entry.getKey()), entry.getValue());
            }
        }
        //排序
        if (!StringUtils.isEmpty(condition.getOrderBy())) {
            queryWrapper.orderByAsc(condition.getSort() == SortEnum.ASC, condition.getOrderBy());
            queryWrapper.orderByDesc(condition.getSort() == SortEnum.DESC, condition.getOrderBy());
        }
        //分页
        Page<R> page = new Page<>();
        if (condition.getLimit() != null) {
            page.setSize(condition.getLimit());
            if (condition.getCurrent() != null) {
                page.setCurrent(condition.getCurrent());
            }
        }
        return function.apply(page, queryWrapper);
    }
}
