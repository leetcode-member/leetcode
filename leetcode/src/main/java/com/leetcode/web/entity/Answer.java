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
@TableName("sys_answer")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  题解id
     */
    @TableId
    private Integer answerId;

    /**
     * 题目id
     */
    private Integer  questionId;

    /**
     *  用户id，发布题解的人
     */
    private Integer  userId;

    /**
     *  题解标题
     */
    private String title;

    /**
     * 题解正文
     */
    private String content;

    /**
     *  题解封面图片url
     */
    private String  image;

    /**
     * 发表日期
     */
    private LocalDateTime  createTime;

    /**
     *  修改日期
     */
    private LocalDateTime  updateTime;

    /**
     * 浏览量
     */
    private Integer  view;

    /**
     *  点赞量
     */
    private Integer  thumbup;

    /**
     *  评论数
     */
    private Integer  comment;

    /**
     *  逻辑删除
     */
    private Integer deleted;


}
