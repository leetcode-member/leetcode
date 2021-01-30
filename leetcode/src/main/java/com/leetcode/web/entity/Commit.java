package com.leetcode.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_commit")
public class Commit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId
    private Integer commitId;

    /**
     * 提交用户id
     */
    private Integer userId;

    /**
     * 提交题目id
     */
    private Integer questionId;

    /**
     * 提交题目时间(time)
     */
    private LocalDateTime commitTime;

    /**
     * 提交结果（有几种类型需要确定）
     */
    private String commitResult;

    /**
     * 提交的代码
     */
    private String commitCode;

    /**
     * 运行时间(time)毫秒
     */
    private Integer runtime;

    /**
     * 内存消耗bit
     */
    private Integer memory;

    /**
     * 逻辑删除
     */
    private Integer deleted;


}
