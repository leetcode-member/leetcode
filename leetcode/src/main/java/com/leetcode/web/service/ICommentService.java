package com.leetcode.web.service;

import com.leetcode.model.constant.CommentConstant;
import com.leetcode.web.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 *
 * @Editor: 周宗成
 * @Since: 2021-2-4 16:17
 */
public interface ICommentService extends IService<Comment> {
    void comment(CommentConstant commentConstant);
}
