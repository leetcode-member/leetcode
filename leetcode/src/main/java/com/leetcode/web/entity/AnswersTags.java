package com.leetcode.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("sys_answers_tags")
public class AnswersTags implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 题解的id
     */
    @TableId
    private Integer answerId;

    /**
     * 标签的id
     */
    private Integer tagId;


}
