package com.lwl.base.api.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwl.base.api.common.pojo.PageCondition;
import com.lwl.base.api.common.pojo.SortEnum;
import com.lwl.base.api.common.util.StringUtil;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * 通用service工具类
 * @author LinWenLi
 * @since 2020-04-24
 */
public class BaseServiceUtils {

    /**
     * 分页列表
     * 根据 entity 条件查询记录（可传入非entity对象作为查询条件并响应非entity的Page内容）
     * @param dto 过滤条件
     * @param pageCondition 分页条件
     * @param function 函数
     * @param <T> 泛型
     * @param <R> 泛型
     * @return Page<R>
     */
    public static <T, R> Page<R> getPage(T dto, PageCondition pageCondition, BiFunction<Page<R>, QueryWrapper<T>, Page<R>> function) {
        QueryWrapper<T> queryWrapper = Wrappers.query();
        //条件
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(dto));
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (entry.getValue() != null) {
                queryWrapper.eq(StringUtil.camelCaseToUnderscore(entry.getKey()), entry.getValue());
            }
        }
        //排序
        if (!StringUtils.isEmpty(pageCondition.getOrderBy())) {
            queryWrapper.orderByAsc(pageCondition.getSort() == SortEnum.ASC, pageCondition.getOrderBy());
            queryWrapper.orderByDesc(pageCondition.getSort() == SortEnum.DESC, pageCondition.getOrderBy());
        }
        //分页
        Page<R> page = new Page<>();
        if (pageCondition.getLimit() != null) {
            page.setSize(pageCondition.getLimit());
            if (pageCondition.getCurrent() != null) {
                page.setCurrent(pageCondition.getCurrent());
            }
        }
        return function.apply(page, queryWrapper);
    }
}
