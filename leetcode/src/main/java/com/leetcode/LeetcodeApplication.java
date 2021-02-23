package com.leetcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LeetcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeetcodeApplication.class, args);
    }

}
@Bean
public BcryptPasswordEncoder encoder(){
    return new BCryptPasswordEncoder();
}

@Bean
public TokenUtil tokenUtil(){
    return new TokenUtil();
}
