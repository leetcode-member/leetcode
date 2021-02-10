package com.leetcode.web.mapper;

import com.leetcode.model.constant.CommentConstant;
import com.leetcode.model.constant.GetCommentConstant;
import com.leetcode.model.constant.Reply;
import com.leetcode.web.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 *

 */
/**
 * @Author: 周宗成
 * @Date: 2021/2/10 19:46
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    CommentConstant[] getComment(GetCommentConstant g);
    int commentTotalPage(GetCommentConstant g);

    List<Reply> getReply(Long parentId);

}
