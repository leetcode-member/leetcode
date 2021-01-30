package com.leetcode.mp;

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
public class MybatisPlusConnetctionTest01 {
    @Autowired
    TagMapper tagMapper;
    @Test
    public void connectionTest(){
        System.out.println("======");
        System.out.println(tagMapper);
        List<Tag> tags = tagMapper.selectList(null);
        System.out.println(tags);
        if (null != tags) {
            for (Tag tag : tags) {
                System.out.println(tag);
            }
        }
        Tag tag = tagMapper.selectById(42314234);
        System.out.println(tag);
    }
}
