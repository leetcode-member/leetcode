package com.leetcode.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 展示所有题目的请求体.
 * 建议使用 POST + JSON 后端使用 DTO 类接受，规范且..
 * @author Jarvan
 * @version 1.0
 * @create 2021/3/4 10:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllQuestionRequestDTO implements Serializable {
    private String listId ;
    private String difficulty ;
    private String status ;
    private Integer pageNum ;
    private Integer page ;
    private String tagId ;
    private String keyword ;
}
