package com.leetcode.web.service.impl;

import com.leetcode.model.constant.CommentConstant;
import com.leetcode.model.constant.CommentTrans;
import com.leetcode.model.constant.GetCommentConstant;
import com.leetcode.model.constant.Reply;
import com.leetcode.web.entity.Comment;
import com.leetcode.web.mapper.CommentMapper;
import com.leetcode.web.mapper.TagMapper;
import com.leetcode.web.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p>
 *  服务实现类
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
@Service
@Transactional
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    //评论插入
    public void  addComment(Comment comment){
       commentMapper.insert(comment);

    }


    //获取评论
    public CommentTrans getComment(GetCommentConstant g){
        g.setPageNum((g.getPageNum()-1)*10);
        CommentConstant[] c=commentMapper.getComment(g);
        Integer i=commentMapper.commentTotalPage(g);
        return  new CommentTrans(c,i);
    }

    //获取回复
    public List<Reply> getReply(Long parentId){
        Queue<Reply> q = new LinkedList<>();
        List<Reply> re = new ArrayList<>();

        List<Reply> g1=commentMapper.getReply(parentId);
        for (Reply r:
             g1) {
            re.add(r);
            q.add(r);
        }
        re=this.getChildren(q,re);

        return re;
    }

    //回复函数
    public List<Reply> getChildren(Queue<Reply> q,List<Reply> re){
        while(true){
            Reply r=q.poll();
            if (r==null)break;
            List<Reply> r2=commentMapper.getReply(r.getCommentId());
            for (Reply r3:
                 r2) {
                q.add(r3);
                re.add(r3);
            }
        }
        return re;
    }

}
