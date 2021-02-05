package com.leetcode.web.controller;


import com.leetcode.model.constant.CommentConstant;
import com.leetcode.model.constant.ResponseConstant;
import com.leetcode.web.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;
    @PostMapping("/comment")
    public ResponseConstant comment(@RequestBody CommentConstant commentConstant){
        commentService.comment(commentConstant);
        return new ResponseConstant().success();
    }
}
