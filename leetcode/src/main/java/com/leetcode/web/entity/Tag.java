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
@TableName("sys_tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     * 如果不加 @TableId selectId 就不知道这个id是什么，所以一定要加上
     */
    @TableId
    private Integer tagId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 逻辑删除
     */
    private Integer deleted;


}
