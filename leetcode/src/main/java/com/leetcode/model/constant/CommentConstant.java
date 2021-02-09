package com.leetcode.model.constant;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @Author: 周宗成
 * @Date: 2021/2/4 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentConstant {
    private Long commentId;
    private String content;
    private Long userId;
    private String nickname;
    private String avatar;
    private Date commentTime;
    private Integer thumbup;
}
