package com.leetcode.web.service;

import com.leetcode.web.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
public interface IUserService extends IService<User> {
    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    public void sendSms(String phone){ }
    public void sendSms2(String email){ }
    /**
     * 用户自助注册保存用户
     * @param user
     * @param checkcode
     */
    public void add(User user,String checkcode) { }
    /**
     * 根据手机号和密码查询用户
     * @param mobile
     * @param password
     * @return
     */
    public User login(User user){ }

    //修改用户信息
    public boolean updata(String nickname,Number sex,String avater,String password){}
    User getAnswerCondition(Long userid);
}
