package com.leetcode.web.service.impl;

import com.leetcode.model.constant.MethodConst;
import com.leetcode.util.authCode.AuthCodeUtil;
import com.leetcode.util.string.StringUtil;
import com.leetcode.web.entity.User;
import com.leetcode.web.mapper.UserMapper;
import com.leetcode.web.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tanshixin
 * @since 2021-01-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private com.leetcode.util.authCode.RandomUtil RandomUtil;
    @Autowired
    private com.leetcode.util.redis.RedisUtil RedisUtil;
    @Autowired
    private com.leetcode.util.token.TokenUtil TokenUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    //    @Autowired
//    private SmsUtils SmsUtils;
    @Autowired
    private com.leetcode.util.mail.MailUtilImpl MailUtilImpl;
//    @Value("${aliyun.sms.template_code}")
//    private String template_code;
//    @Value("${aliyun.sms.sign_name}")
//    private String sign_name;

    /**
     * 发送短信验证码
     */
//    @Override
//    public void sendSms(String phone){
//        //生成6位随机数
//        String checkcode = RandomUtil.getSixRandomCode();
//        //向redis缓存中放一份
//        RedisUtil.set("checkcode_"+phone,checkcode,redisSiveTime)
//        //给用户发送一份
////        map<String,String> map = new HashMap<>();
////        map.put("phone",phone);
////        map.put("checkcode",checkcode);
////        rabbitTemplate.coverAndSend("sms",map);
//        try {
//            SmsUtils.sendSms(phone,template_code,sign_name,"{\"checkcode\":\""+checkcode+"\"}");
//        } catch (java.lang.Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    @Autowired
    AuthCodeUtil authCodeUtil;

    /**
     * 发送邮箱验证码
     */
    @Override
    public void sendSms2(String email) {
        //生成6位随机数
        String checkcode = String.valueOf(com.leetcode.util.authCode.RandomUtil.getSixRandomCode());
        //向redis缓存中放一份
        RedisUtil.set("checkcode_" + email, checkcode, 30000);
        //给用户发送一份
        try {
            authCodeUtil.sendAuthCode(email,checkcode);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 用户自助注册保存用户
     */
    @Override
    public void addNew(User user, String checkcode) {
        //验证码校验成功后再进行保存
        //保存用户到数据库
        User user1 = new User();
        if (checkcode.length() == 11) {
            user1.setPhone(checkcode);
        } else {
            user1.setEmail(checkcode);
        }
        user1.setNumSimp(0);
        user1.setNumMid(0);
        user1.setNumDiff(0);
        //注册日期
        user1.setCreateTime(new Date());
        user1.setUserRole(user.getUserRole());
//      user1.setUpdatedate(new Date());//更新日期
        //3.用户注册成功后，清除redis中的验证码
        RedisUtil.del("checkcode_" + user1.getPhone());
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
    }

    /**
     * 根据手机号/邮箱 和密码查询用户
     *
     * @return
     */
    @Override
    public User login(User user) {
        //1.根据手机号码查询用户
        if (user.getPhone().length() == 11) {
            User userLogin = userMapper.selectByPhone(user.getPhone());
            //与数据库密码进行匹配
            if (userLogin != null && passwordEncoder.matches(user.getPassword(), userLogin.getPassword())) {
                //登陆成功
                return userLogin;
            }
        } else {
            User userLogin = userMapper.selectByEmail(user.getEmail());
            //与数据库密码进行匹配
            if (userLogin != null && passwordEncoder.matches(user.getPassword(), userLogin.getPassword())) {
                //登陆成功
                return userLogin;
            }
        }
        //登陆失败
        return null;
    }

    /**
     * 根据id更新用户信息
     *
     * @param ，nickname，sex,avater,newpassword
     * @return
     */
    @Override
    public void updata(Long userId, String nickname, Number sex, String avater, String newpassword) {
        User user = new User();
        user.setUserId(userId);
        if (nickname != null) {
            user.setNickname(nickname);
        }
        if (sex != null) {
            user.setSex((Integer) sex);
        }
        if (avater != null) {
            user.setAvatar(avater);
        }
        //保存加密后的密码
        if (newpassword != null) {
            user.setPassword(passwordEncoder.encode(newpassword));
        }
        userMapper.updataUser(user);
    }

//    /**
//     * 增加
//     * @param user
//     */
//    public void saveUser(User user) {
//        user.setId( idWorker.nextId()+"" );
//        //密码加密
//        user.setPassword(encoder.encode(user.getPassword()))
//        userRepository.save(user);
//    }


    @Override
    public User getAnswerCondition(Long userid) {
        return userMapper.getAnswer(userid);
    }

    //
//    /**
//     * 忘记密码，发送邮箱验证码
//     */
//    @Override
//    public void sendSms3(String email){
//        //生成6位随机数
//        String checkcode = String.valueOf(com.leetcode.util.authCode.RandomUtil.getSixRandomCode());
//        //向redis缓存中放一份
//        RedisUtil.set("checkcode_"+email,checkcode,300);
//        //给用户发送一份
//        try {
//            MailUtilImpl.sendSimpleMail(email,"验证码","您的验证码为"+checkcode);
//        } catch (java.lang.Exception e) {
//            e.printStackTrace();
//        }
//    }
//
    @Override
    public boolean forget(String method, String loginbody, String code, String newPassword) {
        if (MethodConst.EMAIL.equals(method.toLowerCase())) {
            if (StringUtil.isNotEmpty(loginbody)
                    && !StringUtil.isEmpty(code)
                    && !StringUtil.isEmpty((String) RedisUtil.get("checkcode_" + loginbody))
                    && code.equals((String) RedisUtil.get("checkcode_" + loginbody))) {
                User user = userMapper.selectByEmail(loginbody);
                //修改密码
                user.setPassword(passwordEncoder.encode(newPassword));
                User user1 = userMapper.updataUser(user);
            }
        } else {

        }
        return  false;
        }


    }
