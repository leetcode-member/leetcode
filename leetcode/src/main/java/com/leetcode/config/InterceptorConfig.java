package com.leetcode.config;

import com.leetcode.filter.LoginAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/4 21:24
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    LoginAuthenticationInterceptor loginAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //除了登录都要 token
        registry.addInterceptor(loginAuthenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/test1")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/forget")
                .excludePathPatterns("/user/requestcode");
    }

}
