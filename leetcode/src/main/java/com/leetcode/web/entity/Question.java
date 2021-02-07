package com.leetcode.web.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("sys_question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * question id
     */
    @TableId
    private Long questionId;

    /**
     *  题目的序号
     */
    private String questionNum;

    /**
     *  解题数
     */
    private Integer answerNum;

    /**
     * 难度(easy,mid,diff)
     */
    private String difficulty;

    /**
     * 题目名字
     */
    private String title;

    /**
     * 题目正文
     */
    private String content;

    /**
     * 题目类型(属于哪个列表)
     */
    private Integer listId;

    /**
     * 题目标签(栈\队列\数组)
     */
    private Integer tagId;

    /**
     * 发表日期
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改日期
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 点赞数
     */
    private Integer thumbup;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 提交次数
     */
    private Integer submitNum;

    /**
     * 通过次数
     */
    private Integer passNum;

    /**
     * 逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;


}
