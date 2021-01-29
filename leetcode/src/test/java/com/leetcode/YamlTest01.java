package com.leetcode;

import com.leetcode.config.BeanConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.statements.SpringRepeat;

/**
 * 测试 yaml 多文件是否生效
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/29 21:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class YamlTest01 {
    @Autowired
    BeanConfig beanConfig;

    @Test
    public void test(){
        System.out.println(beanConfig.getPrivateKey());
    }
}
