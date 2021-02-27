package com.leetcode.cos;

import com.leetcode.util.cos.TencentCOSUploadFileUtil;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/9 19:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileUploadApiTest {

    /**
     * 测试成功 文件用的是，放到 resource 下的一张图片.
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException, UnirestException {
        URL resource = GetPathTest.class.getClassLoader().getResource("uploadTest.png");
        String filePath = java.net.URLDecoder.decode(resource.getPath(),"utf-8");
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://localhost:8080/upload")
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0aW1lU3RhbXAiOjE2MTI5MjM5NTM2NDUsInVzZXJSb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjoiMSJ9.ITvEeNZw8Rbkw8K1pG8hfx4d0GqgRqQjc9Gk4IcAucY")
                .header("Cookie", "JSESSIONID=D634EC33DDF360EB01579E93D57E8AC2")
                .field("file", new File(filePath))
                .asString();
        System.out.println(response.getBody());
    }
}
