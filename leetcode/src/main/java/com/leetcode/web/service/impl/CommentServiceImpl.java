package com.leetcode.web.service.impl;

import com.leetcode.model.constant.CommentConstant;
import com.leetcode.web.entity.Comment;
import com.leetcode.web.mapper.CommentMapper;
import com.leetcode.web.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 *
 * @Editor: 周宗成
 * @Since: 2021-2-4 16:17
 */
@Service
@Transactional
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;
    //评论插入
    public void  addComment(Comment comment){
        commentMapper.insert(comment);
    }

    //获得评论
//    public List<CommentConstant>  getComment(int questionId){
//
//    }
}
