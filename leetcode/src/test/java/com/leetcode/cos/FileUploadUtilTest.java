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
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/9 19:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileUploadUtilTest {

    /**
     * 因为是，中文查找资源失败，我们解码后测试成功
     *
     * 测试失败，无法使用绝对路径和相对路径，权限没给到么，奇怪
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        URL resource = GetPathTest.class.getClassLoader().getResource("uploadTest.png");
        String filePath = java.net.URLDecoder.decode(resource.getPath(),"utf-8");
        MockMultipartFile multipartFile = new MockMultipartFile("name.png","uploadTest.png",
                "image/png",
                new FileInputStream(filePath));
        String uploadfile = TencentCOSUploadFileUtil.uploadfile(multipartFile);
        System.out.println(uploadfile);
    }
}
