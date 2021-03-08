package com.leetcode.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.leetcode.model.constant.MethodConst;
import com.leetcode.model.constant.TokenConstant;
import com.leetcode.model.constant.UserRoleConstant;
import com.leetcode.model.dto.*;
import com.leetcode.model.exception.AuthCodeErrorException;
import com.leetcode.model.exception.AuthCodeExpiredException;
import com.leetcode.model.exception.BadRequestException;
import com.leetcode.util.redis.RedisUtil;
import com.leetcode.util.result.Result;
import com.leetcode.util.string.StringUtil;
import com.leetcode.util.token.TokenUtil;
import com.leetcode.web.entity.User;
import com.leetcode.web.entity.dto.CommitRecord;
import com.leetcode.web.entity.dto.UserActivityData;
import com.leetcode.web.mapper.UserMapper;
import com.leetcode.web.service.impl.CommitServiceImpl;
import com.leetcode.web.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Tanshixin
 * @since 2021-01-30
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private CommitServiceImpl commitService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TokenUtil tokenUtil;


    /**
     * 发送短信/邮箱验证码
     */
    @PostMapping("/requestcode")
    public Result<String> sendSms(@RequestBody RequestCodeDTO requestCodeDTO) {
//        if("phone".equals(method)){
//            IUserService.sendSms(number);
//            return Result.ok("发送成功");
//        }
        if ("email".equals(requestCodeDTO.getMethod())) {
            userService.sendSms2(requestCodeDTO.getNumber());
        }
        return Result.ok("发送成功");
    }


    //    /**
//     * 用户注册
//     * @return
//     */
//    @PostMapping("/register")
//    public Result register(@RequestBody User user ,@PathVariable String checkcode ){
//        String checkcodeRedis1 = (String) redisUtil.get("checkcode_"+user.getPhone());
//        String checkcodeRedis2 = (String) redisUtil.get("checkcode_"+user.getEmail());
//        if(checkcodeRedis1.isEmpty()){
//            return Result.unauthorized("请先获取验证码");
//        }
//        if(!checkcodeRedis1.equals(checkcode)){
//            return Result.permissionDenied("请输入正确验证码");
//        }
//        if(checkcodeRedis2.isEmpty() ){
//            return Result.permissionDenied("请先获取验证码");
//        }
//        if(!checkcodeRedis2.equals(checkcode)){
//            return Result.ok("请输入正确验证码");
//        }
//        //保存用户
//        IUserService.addNew(user,checkcode);
//        return Result.ok("用户注册成功");
//    }
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 用户注册
     * 1. method
     * 2. 对应不同的 authcode "checkcode_:[method]" 从 Redis上拿到 然后验证 ，失败抛出验证码错误异常
     * 3. 如果用户已经存在就返回用户已经注册.
     * 4. 验证通过就注册
     *
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        // 1.redis 验证码
        try {
            String authCode = (String) redisUtil.get("checkcode_" + registerRequestDTO.getRegisterBody());
            if (StringUtil.isNotEmpty(authCode) && registerRequestDTO.getAuthCode().equals(authCode)) {
                //
            } else {
                throw new AuthCodeErrorException();
            }
        } catch (NullPointerException e) {
            //验证码过期
            throw new AuthCodeExpiredException();
        }
       // 3. 如果用户已经存在就返回用户已经注册.
        int exist = userMapper.isExistByAccount(registerRequestDTO.getRegisterBody());
        if (exist > 0) {
            throw new BadRequestException("用户已经存在,请登录");
        }
        // 4.正确就注册 ...
        //注册之前清理 redis 验证码
        redisUtil.del("checkcode_" + registerRequestDTO.getRegisterBody());
        User user = new User();
        if ("phone".equals(registerRequestDTO.getMethod().toLowerCase())) {
            user.setPhone(registerRequestDTO.getRegisterBody());
        } else if ("email".equals(registerRequestDTO.getMethod().toLowerCase())) {
            user.setEmail(registerRequestDTO.getRegisterBody());
        }
        //密码加密,每次的 Hash 值不一样.
        user.setPassword(bCryptPasswordEncoder.encode(registerRequestDTO.getPassword()));
        //都是平民
        user.setUserRole(UserRoleConstant.ROLE_USER);
        //默认nickname 为邮箱 或者手机号
        user.setNickname(registerRequestDTO.getRegisterBody());
        //默认男性
        user.setSex(1);
        //默认头像 leetcode
        user.setAvatar("https://assets.leetcode.com/static_assets/public/images/LeetCode_logo.png");
        //保存用户
        boolean saveSuccess = userService.save(user);


        if (!saveSuccess) {
            //甩锅
            return Result.badRequest("注册失败");
        }
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
     * 1. method
     * 2. 通过 method 查询
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        log.info(loginRequestDTO.toString());
        boolean loginSuccess = false;
        if (null == loginRequestDTO) {
            throw new BadRequestException("登录用户为空");
        }
        User user = null;
        if (MethodConst.PHONE.equals(loginRequestDTO.getRegisterBody())) {
            log.info("phone");
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("phone", loginRequestDTO.getRegisterBody());
            user = userService.getOne(wrapper);
        } else {
            log.info("email");
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("email", loginRequestDTO.getRegisterBody());
            user = userService.getOne(wrapper);
        }
        //登录失败
        if (null == user) {
            throw new BadRequestException("没这个用户");
        }
        //如果密码不匹配
        if (!bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(),user.getPassword()) ) {
            throw new BadRequestException("密码错误");
        }
        log.info(user.toString());
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setAvatar(user.getAvatar());
        responseDTO.setNickname(user.getNickname());
        responseDTO.setSex(user.getSex() + "");
        //token 给到 header
        String token = tokenUtil.getToken(user.getUserId() + "", user.getUserRole());
        log.info("token:" + token);
        response.setHeader("token",token);
        return Result.ok(responseDTO);
    }

    @Autowired
    UserMapper userMapper;

    /**
     * 根据id更新用户信息
     *
     * @return
     */
    @PostMapping("/update-info")
    public Result update(HttpServletRequest request, @RequestBody UpdateInfoRequestDTO updateInfoRequestDTO) {
        //   从token中获取用户ID和用户权限
        String token = request.getHeader("token");
        Map<String, String> map = tokenUtil.parseToken(token);
        Long userid = Long.valueOf(map.get(TokenConstant.USER_ID_CLAIN));
//        String role = map.get(TokenConstant.USER_ROLE_CLAIN);
        boolean updateInfo = userMapper.updateInfo(updateInfoRequestDTO, userid + "");
        if (updateInfo) {
            return Result.ok("修改信息成功");
        }
        throw new BadRequestException("前端请求参数错误");
//        //判断用户权限
//        if ("ROLE_USER".equals(role)) {
//            //普通用户
//            userService.updata(userid, nickname, sex, avater, password);
//            return Result.ok("修改成功");
//        } else {
//            return Result.unauthorized("用户角色错误！！");
//        }
    }

    /**
     * 修改用户的账户信息
     * 一次只能修改手机或者是邮箱
     *
     * @param request
     * @return
     */
    @PostMapping("/update-account")
    public Result updateAccount(HttpServletRequest request, @RequestBody UpdateAccountRequestDTO accountRequestDTO) {
        String userId = tokenUtil.getUserId(request.getHeader("token"));
        //错了，这里应该验证新的验证码
        if (authCodeSuccess(accountRequestDTO.getCode(), accountRequestDTO.getNewAccount())) {
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            if (MethodConst.EMAIL.equals(accountRequestDTO.getMethod().toLowerCase())) {
                wrapper.set("email", accountRequestDTO.getNewAccount());
            } else {
                wrapper.set("phone", accountRequestDTO.getNewAccount());
            }
            boolean updateSuccess = userService.update(wrapper);
            if (updateSuccess) {
                return Result.ok("修改用户账户成功");
            }
        }
        throw new BadRequestException("验证码错误");
    }

    /**
     *  验证验证码是否真确
     */
    private boolean authCodeSuccess(String code, String account) {
        return  redisUtil.get("checkcode_" + account).equals(code);
    }

    /**
     * 获取用户提交记录
     * xxj
     *
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
            if (o != null) {
                //缓存中有数据
//                System.out.println("缓存中有数据");
                return Result.ok((List<CommitRecord>) o);
            } else {
                //缓存中没有数据
//                System.out.println("缓存中没有数据");
                List<CommitRecord> userAllCommit = commitService.getUserAllCommit(userid);
                redisUtil.set("userid:" + userid + ":userCommitRecord", userAllCommit, 6 * 60 * 60);
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
     *
     * @param request
     * @return
     */
    @GetMapping("/user/activity")
    @ResponseBody
    public Result<List<UserActivityData>> userActivity(HttpServletRequest request) {

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
     * 1.验证 code
     * 2. 更新 user
     */
    @PostMapping("/forget")
    public Result forget(@RequestBody ForgetRequestDTO forgetRequestDTO) {
//        userService.forget(forgetRequestDTO.getMethod(), forgetRequestDTO.getForGetBody(), forgetRequestDTO.getCode() ,forgetRequestDTO.getNewPassword());
        boolean updateSuccess = false;
        if (null != forgetRequestDTO) {
            Object code = redisUtil.get("checkcode_" + forgetRequestDTO.getForGetBody());
            if (code != null) {
                if (code.equals(forgetRequestDTO.getCode())) {
                    updateSuccess = userMapper.forgetUpdate(forgetRequestDTO.getForGetBody(), bCryptPasswordEncoder.encode(forgetRequestDTO.getNewPassword()));
                }
            }
        }
        if (updateSuccess) {
            return Result.ok("设置成功");
        }
        throw new BadRequestException("前端参数错误");
    }

}
