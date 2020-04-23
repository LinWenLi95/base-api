package com.lwl.base.project.config.security;
import com.lwl.base.project.entity.SysUser;
import com.lwl.base.project.service.ISysRoleService;
import com.lwl.base.project.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 自定义用户认证与授权
 * @author LinWenLi
 * @since 2020-04-04 23:57:04
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        Optional<SysUser> optional = sysUserService.queryByUsername(username);
        if (optional.isPresent()) {
            SysUser sysUser = optional.get();
            // 获取用户的角色列表
            List<String> roles = sysRoleService.queryRoleNamesByUserId(sysUser.getId());
            List<GrantedAuthority> grantedAuthorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            //创建一个用户对象，存入用户名/密码/用户的角色 用于认证授权
            return new User(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities);
        }
        return null;
    }
}
