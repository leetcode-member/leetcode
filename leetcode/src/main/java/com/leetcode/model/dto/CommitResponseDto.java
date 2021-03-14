package com.leetcode.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LWenH
 * @create 2021/3/13 - 22:52
 *
 * 提交代码响应dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommitResponseDto {
    /**
     * 提交代码的结果
     * leetcode一共有四种情况：
     *      - 通过
     *      - 编译出错（编译期出错）
     *      - 执行出错（运行时异常）
     *      - 解答错误（算法出错）
     */
    private String result;
    /**
     * 执行代码消耗的时间，出错为N/A
     */
    private String runtime;
    /**
     * 用时击败的比例，出错不显示
     */
    private String runtimeBeat;
    /**
     * 执行代码消耗的内存，出错为N/A
     */
    private String memory;
    /**
     * 内存消耗击败的比例，出错不显示
     */
    private String memoryBeat;
}
