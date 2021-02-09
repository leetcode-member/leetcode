package com.leetcode.api;

import com.leetcode.model.constant.CommentConstant;
import com.leetcode.web.mapper.CommentMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private CommentMapper commentMapper;
    @Test
    public void test4(){
        CommentConstant[] search = commentMapper.search();
    }
}
