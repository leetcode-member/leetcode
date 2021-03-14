package com.leetcode.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LWenH
 * @create 2021/3/13 - 22:51
 *
 * 提交代码请求dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommitRequestDto {
    /**
     * 用户提交的代码
     */
    private String code;
    /**
     * 题目id
     */
    private String questionId;
}
