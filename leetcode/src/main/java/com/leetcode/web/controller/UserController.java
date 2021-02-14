package com.leetcode.web.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService IUserService;
    @Autowired
    private RedisUtil RedisUtil;
    @Autowired
    private TokenUtil tokenUtil;


    /**
     * 发送短信/邮箱验证码
     * @param phone
     * @return
     */
    @PostMapping("/sendsms/{method}{number}")
    public Result sendSms(@PathVariable String phone){
        if(method==phone){
        IUserService.sendSms(number);
        return new Result(200,"发送成功");
        }
        if(method==email){
            IUserService.sendSms2(number);
        return new Result(200,"发送成功");
        }
    }

    /**
     * 用户注册
     * @return
     */
    @PostMapping("/register/{checkcode}")
    public ResultDTO register(@RequestBody User user ,@PathVariable String checkcode ){
        String checkcodeRedis1 = RedisUtil.get("checkcode_"+user.getPhone);
        String checkcodeRedis2 = RedisUtil.get("checkcode_"+user.getEmail);
        if(checkcodeRedis1.isEmpty){
            return new Result(401,"请先获取验证码");
        }
        if(!checkcodeRedis1.equals(checkcode)){
            return new Result(401,"请输入正确验证码");
        }
        if(checkcodeRedis2.isEmpty){
            return new Result(401,"请先获取验证码");
        }
        if(!checkcodeRedis2.equals(checkcode)){
            return new Result(401,"请输入正确验证码");
        }
        //保存用户
        IUserService.add(user,checkcode);
        return new Result(200,"用户注册成功");
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
     * @param loginMap
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        //调用业务层查询
        User userLogin = IUserService.login(user);
        //判断
        if(null!=userLogin){
            //登录成功
            //签发token
            String token = tokenUtil.getToken(user.getId(),user.getUserRole);

//            String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
            //放一个临时map
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);
            //用来给前端直接显示用户名用的，如果前端需要其他的东西，则也可以继续添加
            //用户昵称
            map.put("name",user.getNickname());
            //用户头像
            map.put("avatar",user.getAvatar());
            //用户年龄
            map.put("age",user.getAge());
            //用户性别
            map.put("sex",user.getSex());
            return new Result(200,"登录成功",map);
        }else{
            //登录失败
            return new Result(400,"用户名或密码错误");
        }
    }

//    /**
//     * 修改
//     * @param user
//     */
//    @PutMapping("/{id}")
//    public Result edit(@RequestBody User user, @PathVariable String id ){
//        user.setId(id);
//        userService.updateUser(user);
//        return new Result(200,"修改成功");
//    }
}
