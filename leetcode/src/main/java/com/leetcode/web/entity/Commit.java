package com.leetcode.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

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
    private Long commitId;

    /**
     * 提交用户id
     */
    private Long userId;

    /**
     * 提交题目id
     */
    private Long questionId;

    /**
     * 提交题目时间(time)
     */
    private Date commitTime;

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
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;


}
