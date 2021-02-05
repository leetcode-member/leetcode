package com.leetcode.web.mapper;

import com.leetcode.model.constant.CommentConstant;
import com.leetcode.web.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 *
 * @Editor: 周宗成
 * @Since: 2021-2-4 16:17
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    void comment(CommentConstant commentConstant);
}
