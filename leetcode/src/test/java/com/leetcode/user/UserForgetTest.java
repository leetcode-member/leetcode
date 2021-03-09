package com.leetcode.user;

import com.leetcode.web.mapper.UserMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/26 20:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserForgetTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void mapperTest(){
        System.out.println(userMapper.forgetUpdate("1207402602@qq.com", "jioljkol"));
    }

    @Test
    public void test() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/user/forget")
                .header("Content-Type", "application/json")
                .body("{\r\n  \"forGetBody\": \"1207402602@qq.com\",\r\n  \"newPassword\": \"demoData\",\r\n  \"code\": \"734254\",\r\n  \"method\": \"email\"\r\n}")
                .asString();
        System.out.println(response.getBody());
    }
}
