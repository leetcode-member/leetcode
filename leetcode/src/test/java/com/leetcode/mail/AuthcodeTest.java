package com.leetcode.mail;

import com.leetcode.util.authCode.AuthCodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/31 21:32
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthcodeTest {
    @Autowired
    AuthCodeUtil authCodeUtil;
    @Test
    public void test() throws InterruptedException {
        authCodeUtil.sendAuthCode("2530196154@qq.com","4321");
        Thread.sleep(10000);
    }
}
