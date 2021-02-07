package com.leetcode.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/30 13:12
 */
public class ApiForFrontTest02 {

    /**
     * 使用 okhttp 模拟前端请求测试后端接口
     * 这个接口式之前给前端体验的一个接口
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\": \"zhangsan\",\r\n    \"age\": 13\r\n}");
        Request request = new Request.Builder()
                .url("http://47.107.90.201:8080/login")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "login=true")
                .addHeader("token", "header.playload.signature")
                .addHeader("Accept-Charset", "utf8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
    /**
     * 使用 Unirest 模拟前端请求测试后端接口
     * 这个接口式之前给前端体验的一个接口
     * @throws IOException
     */
    @Test
    public void test2() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://47.107.90.201:8080/login")
                .header("Content-Type", "application/json")
                .header("Cookie", "login=true")
                .header("token", "header.playload.signature")
                .header("Accept-Charset", "utf8")
                .body("{\r\n    \"name\": \"zhangsan\",\r\n    \"age\": 13\r\n}")
                .asString();
        System.out.println(response.getBody());
    }

    @Test
    public void test3() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("47.107.90.201:8080/login")
                .header("Content-Type", "application/json; utf8")
                .body("{\r\n    \"username\": \"fdsaf\",\r\n    \"password\": \"fdfdssf\"\r\n}")
                .asString();
        System.out.println(response.getBody());
    }


    //************************************************************************************
    //xxj
    //
    //以下测试基于以下token来执行
    //
    //     * 测试 token 获得 和解析
    //     * @throws InterruptedException
    //     */
    //    @Test
    //    public void connectionTest() throws InterruptedException {
    //        String token = tokenUtil.getToken("1", UserRoleConstant.ROLE_USER);
    //        System.out.println(token);
    //        Map<String, String> map = tokenUtil.parseToken(token);
    //        System.out.println(map.get(TokenConstant.USER_ROLE_CLAIN));
    //        System.out.println(map.get(TokenConstant.USER_ID_CLAIN));
    //        System.out.println(map.get(TokenConstant.TIME_STAMP_CLAIN));
    //    }

    /**
     *
     *获取用户做题信息的测试
     * xxj
     * @throws IOException
     * @throws UnirestException
     */
    @Test
    public void test4() throws IOException, UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("http://localhost:8080/user/answer")
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI2NzM4ODY0MzksInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMSJ9.l61yeshW4Z_zCELRhmsKDpWU8UvkNwXNn-TpNMz5DwQ")
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Cookie", "JSESSIONID=888E3704BF6EEA826E1C11BF931AE166")
                .asString();
        System.out.println(response.getBody());
    }

    /**
     * 获取题目列表测试
     * xxj
     * @throws UnirestException
     */
    @Test
    public void test5() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("http://localhost:8080/question/all")
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI2NzM4ODY0MzksInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMSJ9.l61yeshW4Z_zCELRhmsKDpWU8UvkNwXNn-TpNMz5DwQ")
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Cookie", "JSESSIONID=888E3704BF6EEA826E1C11BF931AE166")
                .asString();
        System.out.println(response.getBody());
    }


    /**
     * 获取用户活跃度测试
     * xxj
     * @throws UnirestException
     */
    @Test
    public void test6() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("http://localhost:8080/user/activity")
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI2NzM4ODY0MzksInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMSJ9.l61yeshW4Z_zCELRhmsKDpWU8UvkNwXNn-TpNMz5DwQ")
                .header("Content-Type", "application/json;charset=utf-8")
                .header("Cookie", "JSESSIONID=7BB6ED1F5A4A2E644DC294999224A6FF")
                .asString();
        System.out.println(response.getBody());
    }

    //************************************************************************************************
}
