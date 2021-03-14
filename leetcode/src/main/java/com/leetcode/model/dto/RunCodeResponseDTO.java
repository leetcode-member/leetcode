package com.leetcode.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author LWenH
 * @create 2021/3/9 - 21:17
 * <p>
 * 执行代码的响应dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RunCodeResponseDTO implements Serializable {
    /**
     * 状态（已完成or?）
     */
    private String state;
    /**
     * 不知道是干什么的
     * 猜测也许是用户输入的测试用例
     */
    private String input;
    /**
     * 输出
     */
    private String output;
    /**
     * 预期结果（真实答案）
     */
    private String exceptResult;
}
