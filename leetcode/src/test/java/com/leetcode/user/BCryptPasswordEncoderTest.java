package com.leetcode.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/26 17:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BCryptPasswordEncoderTest {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    public void encodeTest(){
        String the_fuck = bCryptPasswordEncoder.encode("the_fuck");
        System.out.println(the_fuck);
        boolean the_fuck1 = bCryptPasswordEncoder.matches("the_fuck", the_fuck);
        System.out.println(the_fuck1);
    }

    @Test
    public void test1(){
        System.out.println(bCryptPasswordEncoder.matches("the_fuck", "$2a$10$d5mi93Lj63jtITqBeSofPuwd4RU24nNW4G5NmKXePJnDFG4HDQpZO"));

    }
}
