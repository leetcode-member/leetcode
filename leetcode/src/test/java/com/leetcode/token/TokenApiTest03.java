package com.leetcode.token;

import com.leetcode.model.constant.TokenConstant;
import com.leetcode.model.constant.UserRoleConstant;
import com.leetcode.util.token.TokenUtil;
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
 * @create 2021/2/4 22:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenApiTest03 {
    @Autowired
    TokenUtil tokenUtil;

    /**
     * 测试 token 请求过期
     * 测试前先修改 application-bean.yaml 中的过期时间为较短的时间，用于测试
     */
    @Test
    public void tokenApiTest() throws UnirestException {
        String token = tokenUtil.getToken("41241234", UserRoleConstant.ROLE_ADMIN);
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("http://localhost:8080/test1")
                .header("token", token)
                .asString();
        System.out.println(response.getBody());

    }
}
