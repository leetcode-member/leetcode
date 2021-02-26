package com.leetcode.redis;

import com.leetcode.util.authCode.AuthCodeUtil;
import com.leetcode.util.authCode.RandomUtil;
import com.leetcode.util.mail.MailUtil;
import com.leetcode.util.mail.MailUtilImpl;
import com.leetcode.util.redis.RedisUtil;
import com.leetcode.web.entity.Tag;
import com.leetcode.web.mapper.TagMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/30 21:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest01 {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    AuthCodeUtil authCodeUtil;
    @Test
    public void connectionTest() throws InterruptedException {
        System.out.println("======");
        String email = "2530196154@qq.com";
        Integer authCode = RandomUtil.getSixRandomCode();
        redisUtil.set(email,authCode);
        authCodeUtil.sendAuthCode(email,""+authCode);
        Object o = redisUtil.get(email);
        System.out.println(o);
        Thread.sleep(10000);
    }

    @Test
    public void redisAuthGetTest(){
        System.out.println(redisUtil.get("checkcode_1207402602@qq.com"));
    }
}
