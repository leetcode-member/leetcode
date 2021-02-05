package com.leetcode.model.constant;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Author: 周宗成
 * @Date: 2021/2/4 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentConstant {
    private String commentId;

    private String content;
    @TableField("user_id")
    private String userId;
    @TableField("parent_id")
    private String parentId;
    private String type;
}
