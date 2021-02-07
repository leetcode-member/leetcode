package com.leetcode.web.entity.dto;
import lombok.ToString;

/**
 * 记录问题数据
 * @author xuxingjun
 * @data 2021/2/5  -  17:34
 */

@ToString
public class QuestionData {



    private Long questionId;
    private String title;
    private Integer answerNum;
    private String passRate;
    private String difficulty;

    public QuestionData() {
    }



    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public String getDifficulty() {
        return difficulty;
    }


    //修改数据，返回前端所需要的
    public void setDifficulty(String difficulty) {

        if("0".equals(difficulty)){
            this.difficulty = "简单";
        }else if("1".equals(difficulty)){
            this.difficulty = "中等";
        }else {
            this.difficulty = "困难";
        }

    }


}
