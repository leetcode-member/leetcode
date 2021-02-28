package com.leetcode.model.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * @Author: 周宗成
 * @Date: 2021/2/10 19:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    private String at;
    private String content;
    private String nickname;
    private String avatar;
    private Long userId;
    private Long commentId;
    private Date commentTime;
    private Integer thumbup;
}
