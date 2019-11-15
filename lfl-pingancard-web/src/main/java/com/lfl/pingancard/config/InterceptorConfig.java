package com.lfl.pingancard.config;

import com.lfl.pingancard.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: lifalong
 * @create: 2019-10-31 09:27
 * @description: 创建登录拦截器的配置类
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册LoginInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(getLoginInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns(
                "/user/login",
                "/user/register",
                "/frontend/login");
    }


}
