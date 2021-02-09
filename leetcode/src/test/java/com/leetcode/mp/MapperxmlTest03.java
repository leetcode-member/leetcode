package com.leetcode.mp;

import com.leetcode.web.entity.Tag;
import com.leetcode.web.mapper.TagMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/4 18:53
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MapperxmlTest03 {
    @Autowired
    TagMapper tagMapper;
    @Test
    public void tagMapperxmlTest(){
        for (Tag selectTag : tagMapper.selectTags()) {
            System.out.println(selectTag);
        }
    }
}
