package com.leetcode.user;

import com.leetcode.model.dto.UpdateInfoRequestDTO;
import com.leetcode.web.mapper.UserMapper;
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
 * @create 2021/2/26 20:42
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UpdateAccountTest {
    @Autowired
    UserMapper userMapper;


    /**
     * 测试修改邮箱
     * @throws UnirestException
     */
    @Test
    public void test() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/user/update-account")
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTQzNDQ1OTkyODcsInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMTM1NzI1OTU2OTU0ODk1NTY0OSJ9.GewHc4evwxkOv4lzccVWuFcDFc9A1mJH_8Lq3PhmKfA")
                .header("Content-Type", "application/json")
                .body("{\r\n    \"oldAccount\": \"1207402602@qq.com\",\r\n    \"newAccount\": \"the show account\",\r\n    \"code\": \"734254\",\r\n    \"method\": \"meail\"\r\n}")
                .asString();
        System.out.println(response.getBody());
    }
}
