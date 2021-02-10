package com.leetcode.web.controller;


import com.leetcode.model.constant.*;
import com.leetcode.web.entity.Comment;
import com.leetcode.web.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 周宗成
 * @Date: 2021/2/10 19:46
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;


    //接口8 评论发送
    @PostMapping("/add")
    public ResponseConstant comment(@RequestBody Comment comment){
        commentService.addComment(comment);
        return new ResponseConstant().success();
    }

    //评论获取
    @PostMapping("/get")
    public ResponseConstant getComment(@RequestBody GetCommentConstant get){
        CommentTrans t=commentService.getComment(get);
        return new ResponseConstant().success(t);
    }

    //回复获取
    @GetMapping("/reply")
    public ResponseConstant getReply(@RequestParam Long parentId){
        List<Reply> r=commentService.getReply(parentId);
        return new ResponseConstant().success(r);
    }

}
