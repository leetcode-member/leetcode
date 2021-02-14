package com.leetcode.web.service.impl;

import com.leetcode.web.entity.User;
import com.leetcode.web.mapper.UserMapper;
import com.leetcode.web.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private RandomUtil RandomUtil;
    @Autowired
    private RedisUtil RedisUtil;
    @Autoweird
    private UserMapper UserMapper;
    @Autowired
    private TokenUtil TokenUtil;
    @Autoeired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private SmsUtils SmsUtils;
    @Autowired
    private MailUtilImpl MailUtilImpl;
    @Value("${aliyun.sms.template_code}")
    private String template_code;
    @Value("${aliyun.sms.sign_name}")
    private String sign_name;

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    public void sendSms(String phone){
        //生成6位随机数
        String checkcode = RandomUtil.getSixRandomCode();
        //向redis缓存中放一份
        RedisUtil.set("checkcode_"+phone,checkcode,redisSiveTime)
        //给用户发送一份
//        map<String,String> map = new HashMap<>();
//        map.put("phone",phone);
//        map.put("checkcode",checkcode);
//        rabbitTemplate.coverAndSend("sms",map);
        try {
            SmsUtils.sendSms(phone,template_code,sign_name,"{\"checkcode\":\""+checkcode+"\"}");
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    public void sendSms2(String email){
        //生成6位随机数
        String checkcode = RandomUtil.getSixRandomCode();
        //向redis缓存中放一份
        RedisUtil.set("checkcode_"+email,checkcode,redisSiveTime)
        //给用户发送一份
        try {
            MailUtilImpl.sendSimpleMail(email,"验证码","您的验证码为"+checkcode);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 用户自助注册保存用户
     * @param user
     * @param checkcode
     */
    public void add(User user,String checkcode) {
        //验证码校验成功后再进行保存
        //保存用户到数据库
        User user = new User();
        if(checkcode.length==11){
            user.setPhone(checkcode);
        }else{
            user.setEmail(checkcode);
        }
        user.setNumSimp(0);
        user.setNumMid(0);
        user.setNumDiff(0);
        user.setRegdate(new Date());//注册日期
        user.setUserRole(ROLE_USER);
//      user.setUpdatedate(new Date());//更新日期
        //3.用户注册成功后，清除redis中的验证码
        RedisUtil.del("checkcode_"+user.getPhone());
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserMapper.insertUser(user);
    }

    /**
     * 根据手机号/邮箱 和密码查询用户
     * @param mobile
     * @param password
     * @return
     */
    public User login(User user){
        //1.根据手机号码查询用户
        if(user.length==11){
            User userLogin = UserMapper.selectByPhone(user.getPhone());
        }else{
            User userLogin = UserMapper.selectByEmail(user.getEmail());
        }
        //与数据库密码进行匹配
        if(userLogin != null && encoder.match(user.getPassword(),userLogin.getPassword)){
            //登陆成功
            return userLogin;
        }
        //登陆失败
        return null;
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

}
