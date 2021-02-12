package com.leetcode.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leetcode.model.constant.TokenConstant;
import com.leetcode.util.redis.RedisUtil;
import com.leetcode.util.result.Result;
import com.leetcode.util.token.TokenUtil;
import com.leetcode.web.entity.User;
import com.leetcode.web.entity.dto.QuestionData;
import com.leetcode.web.service.impl.QuestionServiceImpl;
import com.leetcode.web.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/1 10:42
 */

@RestController
public class QuestionController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取用户做题信息
     *xxj
     * @param request
     * @return
     */
    @GetMapping("/user/answer")
    @ResponseBody
    public Result answerCondition(HttpServletRequest request) {
//        从token中获取用户ID和用户权限
        String token = request.getHeader("token");
        Map<String, String> map = tokenUtil.parseToken(token);
        Long userid = Long.valueOf(map.get(TokenConstant.USER_ID_CLAIN));
        String role = map.get(TokenConstant.USER_ROLE_CLAIN);


        //判断用户权限
        if ("ROLE_USER".equals(role)) {
            //普通用户
            //判断redis缓存中是否已经有数据
            Object userAnswerCondition = redisUtil.get("userid:" + userid + ":userAnswerCondition");
            if (userAnswerCondition != null) {

                return Result.ok((HashMap<String, Integer>) userAnswerCondition);
            } else {
                //缓存中不存在数据，到数据库中去查找
                User answerCondition = userService.getAnswerCondition(userid);
                Integer easy = answerCondition.getNumSimp();
                Integer mid = answerCondition.getNumMid();
                Integer diff = answerCondition.getNumDiff();
                Integer solved = easy + mid + diff;

                HashMap<String, Integer> newAnswerCondition = new HashMap<>();
                newAnswerCondition.put("solved", solved);
                newAnswerCondition.put("easy", easy);
                newAnswerCondition.put("mid", mid);
                newAnswerCondition.put("diff", diff);

                //将数据放在redis中缓存一段时间
                redisUtil.set("userid:" + userid + ":userAnswerCondition", newAnswerCondition, 6 * 60 * 60);
                return Result.ok(newAnswerCondition);
            }
        } else if ("ROLE_ADMIN".equals(role)) {
            //管理员
            return null;
        } else
            return Result.unauthorized("用户角色无法识别！");
    }


    /**
     * 获取题目列表
     * xxj
     * @param list 题目所属列表
     * @param difficulty 题目难度
     * @param status  题目状态
     * @param pagenum 每页题目数量
     * @param page  第几页
     * @param tag   标签
     * @param keyword  关键字
     * @return
     */
    @GetMapping("/question/all")
    @ResponseBody
    public Result allQuestion(HttpServletRequest request,
                              String list,
                              String difficulty,
                              String status,
                              Integer pagenum,
                              Integer page,
                              String tag,
                              String keyword) {

        //由于前端传输的是String类型的信息，数据库中是int类型这里作出转换
        Integer difficultyCon = difficultyJudge(difficulty);
        //判断pagenum和page是否合理
        if (page == null ||page <= 0) page = 1;
        if (pagenum == null || pagenum <= 0) pagenum = 50;
        Page<QuestionData> pageObj = new Page<>(page, pagenum);

        //判断用户是否筛选做题情况（已做，未做，尝试过）
        if (status == null || "".equals(status)){
            //没有传入做题情况,
            IPage<QuestionData> questionDataIPageWithoutStatus = questionService.selectPageWithoutStatus(pageObj, list, difficultyCon, tag, keyword);
            return Result.ok(questionDataIPageWithoutStatus);
        }else{
            //传入做题情况

//            从token中获取用户ID和用户权限
            String token = request.getHeader("token");
            Map<String, String> map = tokenUtil.parseToken(token);
            Long userid = Long.valueOf(map.get(TokenConstant.USER_ID_CLAIN));
            String role = map.get(TokenConstant.USER_ROLE_CLAIN);
//            long userid = 1;
//            String role = "ROLE_USER";


            //判断用户权限
            if("ROLE_USER".equals(role)){
                //普通用户

                //判断用户要筛选的做题情况
                if ("answered".equals(status)){
                    //筛选已解答
                    IPage<QuestionData> questionDataIPageWithAnswered = questionService.selectPageWithAnswered(pageObj, list, difficultyCon, status, tag, keyword,userid);
                    return Result.ok(questionDataIPageWithAnswered);
                }else if ("tried".equals(status)){
                    //筛选已尝试
                    System.out.println("筛选尝试");
                    IPage<QuestionData> questionDataIPageWithTried = questionService.selectPageWithTried(pageObj, list, difficultyCon, status, tag, keyword, userid);

                    return Result.ok(questionDataIPageWithTried);
                }else if("not-done".equals(status)){
                    //筛选未作答
                    System.out.println("筛选未作答");
                    IPage<QuestionData> questionDataIPageWithUndo = questionService.selectPageWithUndo(pageObj, list, difficultyCon, status, tag, keyword,userid);
                    return Result.ok(questionDataIPageWithUndo);
                }else{
                    return Result.badRequest("请求的状态错误");
                }

            }else if("ROLE_ADMIN".equals(role)){
                //管理员
                return null;
            }else {
                return Result.unauthorized("用户角色错误！");
            }


        }

    }

    /**
     * 难度转换
     * xxj
     * @param diff
     * @return
     */
    private Integer difficultyJudge(String diff){
        if ("easy".equals(diff))
            return 0;
        else if ("mid".equals(diff))
            return 1;
        else if ("diff".equals(diff))
            return 2;
        else
            return null;
    }
}
