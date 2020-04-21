package com.lwl.base.project.config.schedule;

import com.lwl.base.api.common.util.StringUtil;
import com.lwl.base.project.config.redis.RedisConstants;
import com.lwl.base.project.entity.pojo.SysPermission;
import com.lwl.base.project.entity.pojo.SysRole;
import com.lwl.base.project.entity.pojo.SysRolePermission;
import com.lwl.base.project.entity.pojo.UrlRole;
import com.lwl.base.project.service.SysPermissionService;
import com.lwl.base.project.service.SysRolePermissionService;
import com.lwl.base.project.service.SysRoleService;
import com.lwl.base.project.util.RedisUtils;
import com.lwl.base.project.util.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 初始化redis相关数据
 * @author Admin
 */
@Component
public class RedisDataInitSchedule {

    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 将权限表中url与role关联的数据存入redis，用于每次请求的鉴权
     */
    @PostConstruct
    public void init() {
        List<UrlRole> urlRoles = permissionService.queryPermissionUrlAndRoleName();
        if (urlRoles != null) {
            //使用zset，url和method作为key，存roleName列表
            for (UrlRole urlRole : urlRoles) {
                if (!StringUtils.isEmpty(urlRole.getRoleName())) {
                    String url = urlRole.getUrl();
                    //判断url是否含{xx}路径变量
                    if (UrlUtils.isPathVariable(url)) {
                        //将url的路径变量位置改成通配符*
                        url = UrlUtils.toMatchesString(urlRole.getUrl());
                        //存入redis（请求中含路径变量的请求url遍历匹配含通配符*的url集合，取出匹配的含通配符*的url，再去RedisConstants.URL_METHOD中取roleName）
                        RedisUtils.zset(RedisConstants.URL_MATCHERS_KEY, url);
                    }
                    //roleName存入redis
                    String key = String.format(RedisConstants.URL_METHOD, url, urlRole.getMethod());
                    RedisUtils.zset(key, urlRole.getRoleName());
                }
            }
        }
    }
}