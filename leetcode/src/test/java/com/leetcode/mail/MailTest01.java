package com.leetcode.mail;

import com.leetcode.util.mail.MailUtilImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/31 21:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailTest01 {
    @Autowired
    MailUtilImpl mailUtil;
    @Test
    public void test(){
        boolean b = mailUtil.sendAttachmentsMail("2530196154@qq.com" , "hello 靓仔" , "hello 嘉文" , null);
        System.out.println(b);

    }
}
