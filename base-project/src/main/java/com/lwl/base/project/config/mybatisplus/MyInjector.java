package com.lwl.base.project.config.mybatisplus;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * 自定义sql注入器
 * @author LinWenLi
 * @since 2020-04-27
 */
public class MyInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //添加自定义注入方法
        methodList.add(new QueryPage());
        return methodList;
    }
}