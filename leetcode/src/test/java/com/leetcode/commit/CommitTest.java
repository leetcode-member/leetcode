package com.leetcode.commit;

import com.leetcode.web.entity.Commit;
import com.leetcode.web.mapper.CommitMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/3/6 16:44
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommitTest {
    @Autowired
    CommitMapper commitMapper;
    @Test
    public void insertCommitTest(){
        Commit commit = new Commit();
        //admin
        commit.setUserId("1357259291638530049");
        commit.setQuestionId("1367323161421877250");
        commit.setCommitResult("正确");
        commit.setCommitCode("the fuck commit code");
        commit.setCommitTime(new Date());
        commit.setRuntime(1000);
        commit.setMemory(1000);
        log.info("commitMapper.insert(commit):" + commitMapper.insert(commit));
    }

    /**
     * 批量插入
     */
    @Test
    public void inserAll(){
        for (int i = 0; i < 10; i++) {
            insertCommitTest();
        }
    }
}
