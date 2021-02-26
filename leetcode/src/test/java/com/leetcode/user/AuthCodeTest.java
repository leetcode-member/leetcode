package com.leetcode.user;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class AuthCodeTest {

    @Test
    public void test() throws UnirestException {
        HttpResponse<String> response = Unirest.post("http://localhost:8080/user/requestcode")
                .header("Content-Type", "application/json")
                .header("cache-control", "no-cache")
                .header("Postman-Token", "2219c883-091f-42df-a696-81c39609a441")
                .body("{\r\n  \"method\": \"email\",\r\n  \"number\": \"1207402602@qq.com\"\r\n}")
                .asString();
        System.out.println(response.getBody());

    }
}