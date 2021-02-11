package com.leetcode.lwh_test;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author LWenH
 * @create 2021/2/10 - 23:12
 *
 * 接口测试
 */
@SpringBootTest
public class AllTest {

    @DisplayName("接口6_开始题目")
    @Test
    void test6() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/question/start?questionId=1357666015208296449")
                .method("GET", null)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI1MzI0OTczOTcsInVzZXJSb2xlIjoiUk9MRV9BRE1JTiIsInVzZXJJZCI6IjE0MTMyNCJ9.CoMPmhymsBokMp7wVpj1sFp4YVFZRJwGeDdE3elh08g")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @DisplayName("接口13_执行代码")
    @Test
    void test13() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"code\": \"class Solution {\\npublic int[] twoSum(int[] nums, int target) {\\nint[] result = new int[2];\\nfor (int i=0; i < nums.length-1; i++) {\\nfor (int j = i+1; j < nums.length; j++) {\\nif ((nums[i] + nums[j]) == target) {\\nresult[0] = i;\\nresult[1] = j;\\nbreak;\\n}\\n}\\n}\\nreturn result;\\n}\\n}\",\r\n  \"testCase\": \"[2,7,11,15]\\n9\",\r\n  \"questionId\": \"1357666015208296449\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/question/run")
                .method("POST", body)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI4NTQ0MDYzNTIsInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMTQxMzI0In0.vybxoIe47dsVXgBBilwfgaORmuBd6--r1A0RgRiCgHE")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @DisplayName("接口14_提交代码")
    @Test
    void test14() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"code\": \"class Solution {\\npublic int[] twoSum(int[] nums, int target) {\\nint[] result = new int[2];\\nfor (int i=0; i < nums.length-1; i++) {\\nfor (int j = i+1; j < nums.length; j++) {\\nif ((nums[i] + nums[j]) == target) {\\nresult[0] = i;\\nresult[1] = j;\\nbreak;\\n}\\n}\\n}\\nreturn result;\\n}\\n}\",\r\n  \"questionId\": \"1357666015208296449\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/question/commit")
                .method("POST", body)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI4NTQ0MDYzNTIsInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMTQxMzI0In0.vybxoIe47dsVXgBBilwfgaORmuBd6--r1A0RgRiCgHE")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
