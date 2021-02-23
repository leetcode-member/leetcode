package com.leetcode.web.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 用来接受用户提交信息
 * @author xuxingjun
 * @data 2021/2/6  -  19:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommitRecord {
    private Long userId;
    private Long questionId;
    private String title;
    private String commitResult;
    private Date commitTime;


}
