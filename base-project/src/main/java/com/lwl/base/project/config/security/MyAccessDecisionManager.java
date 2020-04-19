package com.lwl.base.project.config.security;
import	java.util.HashSet;

import com.lwl.base.project.config.redis.RedisConstants;
import com.lwl.base.project.entity.pojo.SysPermission;
import com.lwl.base.project.entity.pojo.SysRole;
import com.lwl.base.project.service.SysPermissionService;
import com.lwl.base.project.service.SysRoleService;
import com.lwl.base.project.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 访问决策管理器（确认请求是否可访问资源）
 * @author LinWenLi
 * @date 2020-04-18
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //取出Object中的请求对象request
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        //使用set的去重特性，若有重复的角色则有访问权限
        Set<String> roleNameSet = getRoleNames(request);
        Set<String> authoritieSet = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        //两个Set集合的数据总条数
        int total = authoritieSet.size() + roleNameSet.size();
        //将一个Set集合添加到另一个Set集合中
        roleNameSet.addAll(authoritieSet);
        //如果两个set集合的元素有重复部分，合并后的Set集合size将小于total
        if (roleNameSet.size() < total) {
            //两个set集合的元素有重复部分，有权限访问资源,直接return
            return;
        }
        throw new AccessDeniedException("抱歉，您没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 获取有该url访问权限的所有角色名
     * ================================================
     * # 持久化
     * RDB: 当满足其中一个条件就会执行一次快照
     * 操作  时间（秒） 次数
     * save 900 1
     * AOF: 每次操作成功都会被记录到aof文件中，执行恢复会按照这些指令依次执行进行数据恢复
     * appendonly no => yes 开启aof模式
     * 怎么恢复: 执行恢复指令
     *
     * ==================================================
     * url => roles(r1, r2)
     * hash
     * 1. url => {role1, permissonId}{role2, permissonId}
     * 2. hmget(url【key】, r1【field】, r2【field】 .....)
     * ===================================================
     * set => 1000
     * 1. url => {role1, role2, role4}
     * 2. roles.contains(r1, r2) true/false
     * ===================================================
     * zset
     * url => {{role1, score}, {role2, score}}
     *
     */
    private Set<String> getRoleNames(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        /*获取所有已启用 未删除的权限*/
        Set<Object> zget = RedisUtils.zget(String.format(RedisConstants.URL_METHOD, requestUrl, method));
        Set<String> roleNameSet = new HashSet<> ();
        for (Object o : zget) {
            roleNameSet.add((String)o);
        }
        return roleNameSet;
    }



}
