package com.leetcode.web.mapper;

import com.leetcode.web.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    public User selectByPhone(String phone);
    public User selectByEmail(String email);
    public User insertUser(user);
}
