package com.leetcode.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leetcode.web.entity.User;
import com.leetcode.web.service.impl.UserServiceImpl;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/26 18:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginfdsTest {
    @Autowired
    UserServiceImpl userService;
    @Test
    public void loginServiceTest(){
        QueryWrapper wrapper = new QueryWrapper<User>();
        wrapper.eq("email", "1207402602@qq.com");
        System.out.println(userService.getOne(wrapper));
    }

    @Test
    public void test() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/user/login")
                .header("Content-Type", "application/json")
                .body("{\r\n  \"registerBody\": \"1207402602@qq.com\",\r\n  \"password\": \"jkljkl\",\r\n  \"method\": \"email\"\r\n}")
                .asString();
        System.out.println(response.getBody());
    }
}
