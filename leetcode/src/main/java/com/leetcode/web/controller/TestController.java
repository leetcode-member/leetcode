package com.leetcode.web.controller;

import com.leetcode.util.result.Result;
import com.leetcode.web.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/30 9:27
 */
@RestController
public class TestController {
    @RequestMapping("/test1")
    public Result<String> test1(){
        return Result.ok("服务器部署测试4");
    }
    @RequestMapping("/test2")
    public Result<User> test2(){
        //
        return Result.ok(new User());
    }
}
