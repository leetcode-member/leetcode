package com.leetcode.mp;

import com.baomidou.mybatisplus.annotation.TableId;
import com.leetcode.model.constant.UserRoleConstant;
import com.leetcode.web.entity.Tag;
import com.leetcode.web.entity.User;
import com.leetcode.web.mapper.ListMapper;
import com.leetcode.web.mapper.TagMapper;
import com.leetcode.web.mapper.UserMapper;
import com.leetcode.web.service.ITagService;
import com.leetcode.web.service.impl.ListServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/30 21:56
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class InsertDataTest02 {
    @Autowired
    TagMapper tagMapper;
    @Test
    public void connectionTest(){
        System.out.println("======");
        System.out.println(tagMapper);
        Tag tag = new Tag();
        tag.setTagName("堆");
        tag.setDeleted(0);
        int insert = tagMapper.insert(tag);
        System.out.println(insert);
        List<Tag> tags = tagMapper.selectList(null);
        for (Tag tag1 : tags) {
            System.out.println(tag1);
        }
    }
    @Autowired
    ITagService tagService;
    @Test
    public void insertTagList(){
        ArrayList<Tag> tags = new ArrayList<>();
        String line = "贪心算法\n" +
                "排序\n" +
                "位运算\n" +
                "树\n" +
                "深度优先搜索\n" +
                "广度优先搜索\n" +
                "并查集\n" +
                "图";
        String [] names = line.split("\n");
        for (int i = 0; i < 8; i++) {
            Tag tag = new Tag();
            tag.setTagName(names[i]);
            tag.setDeleted(0);
            tags.add(tag);
        }
        boolean b = tagService.saveBatch(tags);
        System.out.println(b);
        List<Tag> tags1 = tagMapper.selectList(null);
        for (Tag tag : tags1) {
            System.out.println(tag);
        }
    }

    @Autowired
    ListServiceImpl listService;
    @Autowired
    ListMapper listMapper;
    /**
     * 插入列表
     */
    @Test
    public void insertListList(){
        ArrayList<com.leetcode.web.entity.List> lists = new ArrayList<>();
        String line = "LeetCode热题HOT 100\n" +
                "LeetCode精选数牛i...\n" +
                "LeetCode精选算法200题\n" +
                "力扣杯–竞赛合集\n" +
                "腾讯精选练习50题";
        String [] names = line.split("\n");
        for (int i = 0; i < names.length; i++) {
            com.leetcode.web.entity.List tag = new com.leetcode.web.entity.List();
            tag.setListName(names[i]);
            tag.setDeleted(0);
            lists.add(tag);
        }
        boolean b = listService.saveBatch(lists);
        System.out.println(b);
        List<com.leetcode.web.entity.List> lists1 = listMapper.selectList(null);
        for (com.leetcode.web.entity.List list : lists1) {
            System.out.println(list);
        }
    }
    @Autowired
    UserMapper userMapper;
    /**
     * 插入用户
     */
    @Test
    public void insertUsers(){
        User user = new User();
        user.setUserRole(UserRoleConstant.ROLE_USER);
        user.setPhone("13388888888");
        user.setPassword("jkljkl");
        user.setNickname("用户2号");
        user.setSex(1);
        user.setAvatar("http://fdsafd.fasf.png");
        user.setEmail("fdasfd@leetcode.com");
        user.setNumDiff(11);
        user.setNumSimp(34);
        user.setNumMid(22);
        int insert = userMapper.insert(user);
        log.info(""+insert);
    }

}
