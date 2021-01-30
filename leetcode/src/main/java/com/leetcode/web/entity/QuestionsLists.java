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
@TableName("sys_questions_lists")
public class QuestionsLists implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  question id
     */
    @TableId
    private Integer questionId;

    /**
     * list（列表）id
     */
    private Integer listId;


}
