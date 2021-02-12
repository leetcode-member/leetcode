package com.leetcode.web.controller;


import com.leetcode.model.constant.TokenConstant;
import com.leetcode.util.redis.RedisUtil;
import com.leetcode.util.result.Result;
import com.leetcode.util.token.TokenUtil;
import com.leetcode.web.entity.dto.CommitRecord;
import com.leetcode.web.entity.dto.UserActivityData;
import com.leetcode.web.service.impl.CommitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@RestController

public class UserController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private CommitServiceImpl commitService;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 获取用户提交记录
     * xxj
     * @param request
     * @return
     */
    @PostMapping("/user/commit/all")
//    @RequestMapping(value = "/user/commit/all",method = RequestMethod.POST)
    @ResponseBody
    public Result userAllCommit(HttpServletRequest request) {
//         从token中获取用户ID和用户权限
        String token = request.getHeader("token");
        Map<String, String> map = tokenUtil.parseToken(token);
        Long userid = Long.valueOf(map.get(TokenConstant.USER_ID_CLAIN));
        String role = map.get(TokenConstant.USER_ROLE_CLAIN);


        //判断用户权限
        if ("ROLE_USER".equals(role)) {
            //普通用户

            //判断redis缓存中是否存在该用户的数据
            Object o = redisUtil.get("userid:" + userid + ":userCommitRecord");
            if (o != null){
                //缓存中有数据
//                System.out.println("缓存中有数据");
                return Result.ok((List<CommitRecord>)o);
            }else{
                //缓存中没有数据
//                System.out.println("缓存中没有数据");
                List<CommitRecord> userAllCommit = commitService.getUserAllCommit(userid);
                redisUtil.set("userid:" + userid + ":userCommitRecord",userAllCommit,6*60*60);
                return Result.ok(userAllCommit);
            }

        } else if ("ROLE_ADMIN".equals(role)) {
            //管理员
            return null;
        } else {
            return Result.unauthorized("用户角色错误！！");
        }

    }

    /**
     * 获取用户活跃度
     * xxj
     * @param request
     * @return
     */
    @GetMapping("/user/activity")
    @ResponseBody
    public Result userActivity(HttpServletRequest request){

        // 从token中获取用户ID和用户权限
        String token = request.getHeader("token");
        Map<String, String> map = tokenUtil.parseToken(token);
        Long userid = Long.valueOf(map.get(TokenConstant.USER_ID_CLAIN));
        String role = map.get(TokenConstant.USER_ROLE_CLAIN);
        //判断用户权限
        if ("ROLE_USER".equals(role)) {
            List<UserActivityData> userActivityData = commitService.getUserActivity(userid);
            return Result.ok(userActivityData);

        } else if ("ROLE_ADMIN".equals(role)) {
            //管理员
            return null;
        } else {
            return Result.unauthorized("用户角色错误！！");
        }



    }

}
