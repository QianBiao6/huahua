package com.huahua.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//配置类的注解
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * authorizeRequests 所有 Security全注解配置实现的开始，表示开始说明需要的权限
     * antmatchers 拦截路径  permitAll任何权限都可以访问，直接放行
     * @param http
     * @throws Exception
     */
    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
