package com.leetcode.controller;

import com.leetcode.util.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/30 9:27
 */
@RestController
public class TestController {
    @GetMapping("/test1")
    public Result<String> test1(){
        return Result.ok("测试 controller 返回数据");
    }
}
