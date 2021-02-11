package com.leetcode.cos;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.propertyeditors.CharsetEditor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.CharsetEncoder;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/10 14:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GetPathTest {
    /**
     * 通过相对地址获得可用的地址,
     * 这事因为 中文 编码就会找不到文件的问题，我们用 解码，就解决了
     */
    @Test
    public void test() throws UnsupportedEncodingException, MalformedURLException {
        URL resource = GetPathTest.class.getClassLoader().getResource("uploadTest.png");
        String s = java.net.URLDecoder.decode(resource.getPath(),"utf-8");
        System.out.println(s);

    }
}
