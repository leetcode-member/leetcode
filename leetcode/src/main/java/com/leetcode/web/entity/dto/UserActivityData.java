package com.leetcode.web.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuxingjun
 * @data 2021/2/6  -  21:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityData {
    private Long userId;
    private String commitTime;
    private int commitTimes;
}
