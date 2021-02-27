package com.leetcode.web.service.impl;

import com.leetcode.web.entity.User;
import com.leetcode.web.mapper.UserMapper;
import com.leetcode.web.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserMapper userMapper;

    @Override
    public User getAnswerCondition(Long userid) {
        return userMapper.getAnswer(userid);
    }
}
