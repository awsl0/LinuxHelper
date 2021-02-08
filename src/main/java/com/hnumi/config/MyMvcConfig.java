package com.hnumi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginHandleInterceptor loginHandleInterceptor;
    @Autowired
    FilterConfig filterConfig;
    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandleInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/css/**","/js/**","img/**");

        registry.addInterceptor(filterConfig)
                .addPathPatterns("/**");
    }
}
