package com.leetcode.web.service;

import com.leetcode.util.result.Result;
import com.leetcode.web.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @authorTanshixin
 * @since 2021-01-30
 */
public interface IUserService extends IService<User> {
    /**
     * 发送短信验证码
     */
//    public void sendSms(String phone);
    public void sendSms2(String email);
    /**
     * 用户自助注册保存用户
     */
    public void addNew(User user,String checkcode);
    /**
     * 根据手机号和密码查询用户
     * @return
     */
    public User login(User user);

    //修改用户信息
    public void updata(Long userId,String nickname,Number sex,String avater,String newpassword);
    User getAnswerCondition(Long userid);
    public boolean forget(String method, String username, String code, String newpassword);
}
