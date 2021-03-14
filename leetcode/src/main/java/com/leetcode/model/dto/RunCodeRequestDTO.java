package com.leetcode.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author LWenH
 * @create 2021/3/9 - 21:17
 * <p>
 * 执行代码的请求dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunCodeRequestDTO implements Serializable {
    /**
     * 题目id
     */
    private String questionId;
    /**
     * 用户提交的代码
     */
    private String code;
    /**
     * 提交的测试用例
     */
    private String testCase;
}
