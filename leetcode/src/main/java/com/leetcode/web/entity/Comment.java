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
@TableName("sys_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId
    private Long commentId;

    /**
     * 评论内容
     */
    private String  content;

    /**
     * 评论人ID
     */
    private Long  userId;

    /**
     *  评论父（目标）ID（目标类型通过 type类型确定）
     */
    private Long  parentId;

    /**
     * 评论日期
     */
    private LocalDateTime  commentTime;

    /**
     *  点赞数
     */
    private Integer  thumbup;

    /**
     *  评论类型可选（question,comment,answer）
     */
    private String type;

    /**
     *  逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer  deleted;


}
