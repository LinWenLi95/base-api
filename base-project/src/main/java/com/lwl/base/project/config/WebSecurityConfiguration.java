package com.lwl.base.project.config;

import com.lwl.base.project.config.jwt.JwtAuthenticationFilter;
import com.lwl.base.project.config.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Spring Security配置类
 * @author LinWenLi
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义登录身份认证组件
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf, 使用的是JWT，不需要csrf
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 登录URL
                .antMatchers(SecurityConstants.AUTH_LOGIN_URL).permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 关闭form表单登录
                .formLogin().disable()
                .logout().logoutUrl(SecurityConstants.AUTH_LOGOUT_URL);
    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                //静态资源访问无需认证
//                .antMatchers("/image/**").permitAll()
//                //admin开头的请求，需要admin权限
//                .antMatchers("/admin/**").hasAnyRole("ADMIN")
//                //需登陆才能访问的url
//                .antMatchers("/article/**").hasRole("USER")
//                //默认其它的请求都需要认证，这里一定要添加
//                .anyRequest().authenticated()
//                .and()
//                //CRSF禁用，因为不使用session
//                .csrf().disable()
//                //禁用session
//                .sessionManagement().disable()
//                //禁用form登录
//                .formLogin().disable()
//                //支持跨域
//                .cors()
//                .and()
//                //添加header设置，支持跨域和ajax请求
//                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
//                new Header("Access-control-Allow-Origin","*"),
//                new Header("Access-Control-Expose-Headers","Authorization"))))
//                .and()
//                //拦截OPTIONS请求，直接返回header
////                .addFilterAfter(new OptionRequestFilter(), CorsFilter.class)
//                //添加登录filter
//                .apply(new JsonLoginConfigurer<>())
////                .loginSuccessHandler(jsonLoginSuccessHandler())
////                .and()
////                //添加token的filter
////                .apply(new JwtLoginConfigurer<>())
////                .tokenValidSuccessHandler(jwtRefreshSuccessHandler())
//                .permissiveRequestUrls("/logout")
//                .and()
//                //使用默认的logoutFilter
//                .logout()
////              .logoutUrl("/logout")   //默认就是"/logout"
////                .addLogoutHandler(tokenClearLogoutHandler())  //logout时清除token
//                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()) //logout成功后返回200
//                .and()
//                .sessionManagement().disable();
//    }
//
//    @Bean
//    protected JwtRefreshSuccessHandler jwtRefreshSuccessHandler() {
//        return new JwtRefreshSuccessHandler(jwtUserService());
//    }
//
//    @Bean
//    protected JwtUserService jwtUserService() {
//        return new JwtUserService();
//    }
//
//    @Bean
//    protected JsonLoginSuccessHandler jsonLoginSuccessHandler() {
//        return new JsonLoginSuccessHandler(jwtUserService());
//    }
//
//
//
//    //配置provider
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider()).authenticationProvider(jwtAuthenticationProvider());
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean("jwtAuthenticationProvider")
//    protected AuthenticationProvider jwtAuthenticationProvider() {
//        return new JwtAuthenticationProvider(jwtUserService());
//    }
//
//    @Bean("daoAuthenticationProvider")
//    protected AuthenticationProvider daoAuthenticationProvider() throws Exception{
//        //这里会默认使用BCryptPasswordEncoder比对加密后的密码，注意要跟createUser时保持一致
//        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
//        daoProvider.setUserDetailsService(userDetailsService());
//        return daoProvider;
//    }

}