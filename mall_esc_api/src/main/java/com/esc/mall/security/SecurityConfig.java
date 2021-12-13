package com.esc.mall.security;

import com.esc.mall.dto.ums.admin.AdminUserDetails;
import com.esc.mall.model.UmsAdmin;
import com.esc.mall.model.UmsPermission;
import com.esc.mall.service.IUmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Security 配置类
 * @author jiaorun
 * @date 2021/11/4 10:34
 **/
@Component
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //启用Security的前置注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 变量方式注入应尽量避免，使用构造器方式或者set方式进行注入，具体的话如果是强制依赖的话
     * 就使用构造器方式，选择依赖的话就使用set方式
     */
    private IUmsAdminService umsAdminService;

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    public SecurityConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                          RestfulAccessDeniedHandler restfulAccessDeniedHandler) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.restfulAccessDeniedHandler = restfulAccessDeniedHandler;
    }

    @Autowired
    public void setUmsAdminService(IUmsAdminService umsAdminService) {
        this.umsAdminService = umsAdminService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf() //由于使用的是JWT，这里不需要csrf
                .disable()
                .sessionManagement() //基于token，所以这里不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, //允许对于网络静态资源的无权限访问
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                )
                .permitAll()
                .antMatchers("/v1/ums/admin/login", "/v1/ums/admin/register") //对登录注册要允许匿名访问
                //.antMatchers("/v1/**") //测试时放开所有接口
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS) //跨域请求会先进行一次options请求
                .permitAll()
                .anyRequest() //除上面的所有请求全部需要鉴权认证
                .authenticated();
        //禁用缓存
        httpSecurity.headers().cacheControl();
        //添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    // 获取用户及对应的角色信息
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(myPasswordEncode());
    }

    // 加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 自定义加密比较器
    @Bean
    public MyPasswordEncode myPasswordEncode() {
        return new MyPasswordEncode();
    }

    // 获取用户登录信息
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UmsAdmin admin = umsAdminService.getAdminByUsername(username);
            if(admin != null) {
                List<UmsPermission> permissionList = umsAdminService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin, permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
