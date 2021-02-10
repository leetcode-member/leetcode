package com.leetcode.config;


import com.leetcode.filter.LoginAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2020/11/29 16:54
 */
//@Configuration 拦截器
public class WebMvcConfig
        implements WebMvcConfigurer {
    @Autowired
    LoginAuthenticationInterceptor loginAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //这里添加我们制作的拦截器.
        registry.addInterceptor(loginAuthenticationInterceptor)
                //添加拦截的地址
                .addPathPatterns("/user/test",
                        "/user/test2",
                        "/file/**")
                //放行的地址,我们可以通过注解实现或者在这里统一添加
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register");
    }

}
