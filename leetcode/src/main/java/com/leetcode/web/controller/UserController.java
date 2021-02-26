package com.leetcode.web.controller;


import com.leetcode.model.constant.TokenConstant;
import com.leetcode.model.dto.RequestCodeDTO;
import com.leetcode.util.redis.RedisUtil;
import com.leetcode.util.result.Result;
import com.leetcode.util.token.TokenUtil;
import com.leetcode.web.entity.User;
import com.leetcode.web.entity.dto.CommitRecord;
import com.leetcode.web.entity.dto.UserActivityData;
import com.leetcode.web.service.impl.CommitServiceImpl;
import com.leetcode.web.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Tanshixin
 * @since 2021-01-30
 */
@RestController
@RequestMapping(value = "/user/**")
public class UserController {


    @Autowired
    private CommitServiceImpl commitService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserServiceImpl IUserService;

    @Autowired
    private TokenUtil tokenUtil;




    /**
     * 发送短信/邮箱验证码
     */
    @PostMapping("/requestcode")
    public Result<String> sendSms(@RequestBody RequestCodeDTO requestCodeDTO){
//        if("phone".equals(method)){
//            IUserService.sendSms(number);
//            return Result.ok("发送成功");
//        }
        if("email".equals(requestCodeDTO.getMethod())){
            IUserService.sendSms2(requestCodeDTO.getNumber());
        }
        return Result.ok("发送成功");
    }


    /**
     * 用户注册
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user ,@PathVariable String checkcode ){
        String checkcodeRedis1 = (String) redisUtil.get("checkcode_"+user.getPhone());
        String checkcodeRedis2 = (String) redisUtil.get("checkcode_"+user.getEmail());
        if(checkcodeRedis1.isEmpty()){
            return Result.unauthorized("请先获取验证码");
        }
        if(!checkcodeRedis1.equals(checkcode)){
            return Result.permissionDenied("请输入正确验证码");
        }
        if(checkcodeRedis2.isEmpty() ){
            return Result.permissionDenied("请先获取验证码");
        }
        if(!checkcodeRedis2.equals(checkcode)){
            return Result.ok("请输入正确验证码");
        }
        //保存用户
        IUserService.addNew(user,checkcode);
        return Result.ok("用户注册成功");
    }


//    /**
//     * 增加
//     * @param user
//     */
//    @PostMapping
//    public Result add(@RequestBody User user  ){
//        userService.saveUser(user);
//        return new Result(true,StatusCode.OK,"增加成功");
//    }


    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        //调用业务层查询
        User userLogin = IUserService.login(user);
        //判断
        if(null!=userLogin){
            //登录成功
            //签发token
            String token = tokenUtil.getToken(user.getUserId(), user.getUserRole());

//            String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
            //放一个临时map
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);
            //用来给前端直接显示用户名用的，如果前端需要其他的东西，则也可以继续添加
            //用户昵称
            map.put("name",user.getNickname());
            //用户头像
            map.put("avatar",user.getAvatar());
            //用户性别
            map.put("sex",user.getSex());
            return Result.ok(map);
        }else{
            //登录失败
            return Result.permissionDenied("用户名或密码错误");
        }
    }



    /**
     * 根据id更新用户信息
     * @param nickname，sex,avater,newpassword
     * @return
     */
    @PostMapping("/update-info")
    public Result update(HttpServletRequest request,@RequestBody String nickname,Number sex,String avater,String password) {
        //   从token中获取用户ID和用户权限
        String token = request.getHeader("token");
        Map<String, String> map = tokenUtil.parseToken(token);
        Long userid = Long.valueOf(map.get(TokenConstant.USER_ID_CLAIN));
        String role = map.get(TokenConstant.USER_ROLE_CLAIN);

        //判断用户权限
        if ("ROLE_USER".equals(role)) {
            //普通用户
            IUserService.updata(userid,nickname, sex, avater, password);
            return Result.ok("修改成功");
        } else{
            return Result.unauthorized("用户角色错误！！");
        }
    }


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

    /**
     * 忘记密码
     */
    @PostMapping("/forget")
    public Result forget(@RequestBody String method,String loginbody,String code,String newpassword){
        IUserService.foget(method,loginbody,code,newpassword);
        return Result.ok("设置成功");
    }

}
