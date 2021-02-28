package com.leetcode.model.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 周宗成
 * @Date: 2021/2/10 19:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentTrans {
    private  CommentConstant[] g;
    private Integer totalPage;
}
