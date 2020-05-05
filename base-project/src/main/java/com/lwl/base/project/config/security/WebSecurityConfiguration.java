package com.lwl.base.project.config.security;

import com.lwl.base.api.common.vo.Result;
import com.lwl.base.api.common.vo.ResultCode;
import com.lwl.base.project.filter.JwtAuthenticationFilter;
import com.lwl.base.project.filter.JwtAuthenticationTokenFilter;
import com.lwl.base.project.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * SpringSecurity配置类
 * @author LinWenLi
 * @since 2020-04-17
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //跨域预检请求全部允许访问
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //所有请求都需要身份认证
                .anyRequest().authenticated()
                //访问决策管理器
                .accessDecisionManager(myAccessDecisionManager)
                .and()
                //添加过滤器 登录认证，生成JWT
                .addFilterAt(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                //添加过滤器 请求访问资源时校验JWT
                .addFilterAt(new JwtAuthenticationTokenFilter(), BasicAuthenticationFilter.class)
                //设置身份验证异常处理程序 异常统一响应数据格式
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    //jwt校验失败的处理 统一响应数据格式
                    ResponseUtils.responseResult(response, Result.error(ResultCode.AUTHENTICATION_ERROR));
                })
                .accessDeniedHandler((request, response, exception) -> {
                    //403 统一响应数据格式
                    ResponseUtils.responseResult(response, Result.error(ResultCode.FORBIDDEN));
                })
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .logout().disable()
                //禁用 csrf
                .csrf().disable()
                .cors();
    }
}
