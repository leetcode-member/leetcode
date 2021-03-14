package com.leetcode.question;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author LWenH
 * @create 2021/3/14 - 14:47
 *
 * 开始题目 执行代码 提交代码 接口测试
 */
@SpringBootTest
public class QuestionTest02 {

    /*
        开始题目接口的测试
        这个接口我没动过，把数据库里的信息同步了一下，测试跑的动了
     */
    @DisplayName("接口6_开始题目1")
    @Test()
    public void test6_1() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8081/leetcode/question/start/1367323169126813698")
                .method("GET", null)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI1MzI0OTczOTcsInVzZXJSb2xlIjoiUk9MRV9BRE1JTiIsInVzZXJJZCI6IjE0MTMyNCJ9.CoMPmhymsBokMp7wVpj1sFp4YVFZRJwGeDdE3elh08g")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @DisplayName("接口6_开始题目2")
    @Test()
    public void test6_2() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8081/leetcode/question/start/random")
                .method("GET", null)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI1MzI0OTczOTcsInVzZXJSb2xlIjoiUk9MRV9BRE1JTiIsInVzZXJJZCI6IjE0MTMyNCJ9.CoMPmhymsBokMp7wVpj1sFp4YVFZRJwGeDdE3elh08g")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    /*
        以下是执行代码的测试（数据库question表里我写了题目说明）
     */

    @DisplayName("接口13_执行代码测试1")
    @Test()
    public void test13_1() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"code\": \"class Solution {\\npublic int[] twoSum(int[] nums, int target) {\\n//int k = 1 / 0;\\nint[] result = new int[2];\\nfor (int i=0; i < nums.length-1; i++) {\\nfor (int j = i+1; j < nums.length; j++) {\\nif ((nums[i] + nums[j]) == target) {\\nresult[0] = i;\\nresult[1] = j;\\nbreak;\\n}\\n}\\n}\\nreturn result;\\n}\\n}\",\r\n  \"testCase\": \"[2,7,11,15]\\n9\",\r\n  \"questionId\": \"1367323169126813698\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8081/leetcode/question/run")
                .method("POST", body)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI4NTQ0MDYzNTIsInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMTQxMzI0In0.vybxoIe47dsVXgBBilwfgaORmuBd6--r1A0RgRiCgHE")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @DisplayName("接口13_执行代码测试2")
    @Test()
    public void test13_2() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"code\": \"class Solution {\\npublic boolean isValid(String s) {\\nStack<Character> stack = new Stack<>();\\nfor (int i = 0; i < s.length(); i++) {\\nchar ch = s.charAt(i);\\nif (ch == '(' || ch == '[' || ch == '{') {\\nstack.push(ch);\\n} else {\\nif (stack.isEmpty()) {\\nreturn false;\\n}\\nCharacter pop = stack.pop();\\nif (ch == ')' && pop != '(') {\\nreturn false;\\n} else if (ch == ']' && pop != '[') {\\nreturn false;\\n} else if (ch == '}' && pop != '{') {\\nreturn false;\\n}\\n}\\n}\\nreturn stack.isEmpty();\\n}\\n}\",\r\n  \"testCase\": \"{())}\",\r\n  \"questionId\": \"1367323170359939074\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8081/leetcode/question/run")
                .method("POST", body)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI4NTQ0MDYzNTIsInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMTQxMzI0In0.vybxoIe47dsVXgBBilwfgaORmuBd6--r1A0RgRiCgHE")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @DisplayName("接口13_执行代码测试3")
    @Test()
    public void test13_3() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"code\": \"class Solution {\\npublic int[][] testBuildParam(List<Integer> list1, List<List<String>> list2, char[][] chars, double[] doubles) {\\nSystem.out.println(list1.toString());\\nSystem.out.println(list2.toString());\\nSystem.out.println(Arrays.deepToString(chars));\\nSystem.out.println(Arrays.toString(doubles));\\nint[][] ints2 = new int[2][];\\nint[] ints1 = {1,2,3};\\nints2[0] = ints1;\\nints2[1] = ints1;\\nreturn ints2;\\n}\\n}\",\r\n  \"testCase\": \"[4,5,6,89]\\n[[\\\"A\\\",\\\"1\\\",\\\"B\\\"],[\\\"C\\\",\\\"D\\\",\\\"2\\\"],[\\\"3\\\",\\\"4\\\",\\\"E\\\",\\\"5\\\"]]\\n[[\\\"A\\\",\\\"B\\\",\\\"C\\\",\\\"E\\\"],[\\\"S\\\",\\\"F\\\",\\\"C\\\",\\\"S\\\"],[\\\"A\\\",\\\"D\\\",\\\"E\\\",\\\"E\\\"]]\\n[5.5,3.14,3.13,7.14]\",\r\n  \"questionId\": \"1367323171312046081\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8081/leetcode/question/run")
                .method("POST", body)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI4NTQ0MDYzNTIsInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMTQxMzI0In0.vybxoIe47dsVXgBBilwfgaORmuBd6--r1A0RgRiCgHE")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    /*
        以下是提交代码的测试
     */
    @DisplayName("接口14_提交代码测试1")
    @Test()
    public void test14_1() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"code\": \"class Solution {\\npublic int[] twoSum(int[] nums, int target) {\\n//int k = 1 / 0;\\nint[] result = new int[2];\\nfor (int i=0; i < nums.length-1; i++) {\\nfor (int j = i+1; j < nums.length; j++) {\\nif ((nums[i] + nums[j]) == target) {\\nresult[0] = i;\\nresult[1] = j;\\nbreak;\\n}\\n}\\n}\\nreturn result;\\n}\\n}\",\r\n  \"questionId\": \"1367323169126813698\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8081/leetcode/question/commit")
                .method("POST", body)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI4NTQ0MDYzNTIsInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMTQxMzI0In0.vybxoIe47dsVXgBBilwfgaORmuBd6--r1A0RgRiCgHE")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @DisplayName("接口14_提交代码测试2")
    @Test()
    public void test14_2() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"code\": \"class Solution {\\npublic boolean isValid(String s) {\\nStack<Character> stack = new Stack<>();\\nfor (int i = 0; i < s.length(); i++) {\\nchar ch = s.charAt(i);\\nif (ch == '(' || ch == '[' || ch == '{') {\\nstack.push(ch);\\n} else {\\nif (stack.isEmpty()) {\\nreturn false;\\n}\\nCharacter pop = stack.pop();\\nif (ch == ')' && pop != '(') {\\nreturn false;\\n} else if (ch == ']' && pop != '[') {\\nreturn false;\\n} else if (ch == '}' && pop != '{') {\\nreturn false;\\n}\\n}\\n}\\nreturn stack.isEmpty();\\n}\\n}\",\r\n  \"questionId\": \"1367323170359939074\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8081/leetcode/question/commit")
                .method("POST", body)
                .addHeader("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI4NTQ0MDYzNTIsInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMTQxMzI0In0.vybxoIe47dsVXgBBilwfgaORmuBd6--r1A0RgRiCgHE")
                .addHeader("content-type", "application/json;charset=UTF-8")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

}
