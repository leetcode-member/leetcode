package com.leetcode.user;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class RegisterTest {

    /**
     * 在获取验证码,测试记得改邮箱
     * @throws UnirestException
     */
    @Test
    public void test() throws UnirestException {
        //发送验证码
        //sendCode();
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/user/register")
                .header("Content-Type", "application/json")
                .body("{\r\n  \"registerBody\": \"1207402602@qq.com\",\r\n  \"password\": \"jkljkl\",\r\n  \"authCode\": \"339827\",\r\n  \"method\": \"email\"\r\n}")
                .asString();
        System.out.println(response.getBody());
    }

    @Test
    public void sendCode() throws UnirestException {
        HttpResponse<String> response = Unirest.post("http://localhost:8080/user/requestcode")
                .header("Content-Type", "application/json")
                .header("cache-control", "no-cache")
                .header("Postman-Token", "2219c883-091f-42df-a696-81c39609a441")
                .body("{\r\n  \"method\": \"email\",\r\n  \"number\": \"1207402602@qq.com\"\r\n}")
                .asString();
        System.out.println(response.getBody());
    }
}
