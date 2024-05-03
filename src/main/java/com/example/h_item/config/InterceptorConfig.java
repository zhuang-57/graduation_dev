package com.example.h_item.config;

import com.example.h_item.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author fancy
 * @date 2022/2/20 20:38
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor loginInterceptor;
    // 是否登录拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns("/user/login", "/academy/queryList","/file/uploadFile","/img/**","/test/**",
                "/user/register","/index.html","/css/**","/js/**","/fonts/**","/favicon.ico","/","/static/**");
    }
}