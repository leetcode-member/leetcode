package com.leetcode.token;

import com.leetcode.util.authCode.AuthCodeUtil;
import com.leetcode.util.authCode.RandomUtil;
import com.leetcode.util.redis.RedisUtil;
import com.leetcode.util.token.TokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/30 21:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenTest02 {
    @Autowired
    TokenUtil tokenUtil;
    @Test
    public void connectionTest() throws InterruptedException {

    }
}
