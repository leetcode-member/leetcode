package com.leetcode.question;

import com.leetcode.web.entity.Question;
import com.leetcode.web.mapper.QuestionMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.querydsl.QSort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/3/4 11:29
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionTest01 {
    @Autowired
    QuestionMapper questionMapper;

    /**
     * 插入题目
     */
    @Test
    public void insertQuestion(){
        var questions = new ArrayList<Question>();
        for (int i = 0; i < 100; i++) {
            Question question = new Question();
            question.setAnswerNum(10);
            question.setQuestionNum(""+i);
            question.setDifficulty("0");
            question.setTitle("题目"+i);
            question.setContent("内容"+i);
            //100
            question.setListId("1357250087251021826");
            //栈
            question.setTagId("1357245099137261570");
            question.setThumbup(new Random().nextInt(100));
            question.setCommentNum(new Random().nextInt(100));
            //提交次数
            var submit = new Random().nextInt(100);
            question.setSubmitNum(submit);
            log.info("submit: " + submit);
            question.setPassNum(submit -= new Random().nextInt(submit));
            question.setInitialCode("public class QuestionCode {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        \n" +
                    "    }\n" +
                    "}");
            question.setInitialTestCase("初始测试用例: 134 34214 341234 3421 4");
            question.setCommitTestCase("评测测试用例: 43124 43214 4214 21421 432 14 2314214214 324214 1234312 4 32 423 4 324 32 4 234234324 234 23 4243 24 234 ");
            log.info("questionMapper.insert(question): " + questionMapper.insert(question));
            questions.add(question);
        }
    }

    /**
     * 插入正确的代码
     */
    @Test
    public void insertCurrentCode(){

    }
}
