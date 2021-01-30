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
@TableName("sys_list")
public class List implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  list id
     */
    @TableId
    private Integer listId;

    /**
     * 列表名称
     */
    private String listName;

    /**
     * 逻辑删除
     */
    private Integer deleted;


}
