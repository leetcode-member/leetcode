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

    User getAnswerCondition(Long userid);
}
