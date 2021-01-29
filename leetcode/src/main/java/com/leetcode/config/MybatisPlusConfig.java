package com.leetcode.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Jarvan
 * @create 2020/8/7 15:06
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 乐观锁注册Bean
     * mybatis-plus使用乐观锁只有2步骤
     * 1. @Version
     * 2. 注册bean
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页插件
     * @return
     * @Bean 必须bean注册不然报错.
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}



