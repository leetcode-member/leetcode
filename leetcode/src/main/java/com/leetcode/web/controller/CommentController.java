package com.leetcode.web.controller;


import com.leetcode.model.constant.CommentConstant;
import com.leetcode.model.constant.ResponseConstant;
import com.leetcode.web.entity.Comment;
import com.leetcode.web.mapper.CommentMapper;
import com.leetcode.web.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ListIterator;

/**
 * @Author: zzc
 * @Date: 2021/2/5 15:00
 */
@RestController
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    //接口8 评论发送
    @PostMapping("/comment")
    public ResponseConstant comment(@RequestBody Comment comment){
        commentService.addComment(comment);
        return new ResponseConstant().success();
    }

    //接口9 评论获取
//    @PostMapping("/comment/all/{questionId}/{category}")
//    public ResponseConstant getComment(@PathVariable int questionId,@PathVariable String category){
//        List<CommentConstant> list=commentService.getComment(questionId);
//    }
}
