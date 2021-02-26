package com.leetcode.web.mapper;

import com.leetcode.web.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Tanshixin
 * @since 2021-01-30
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectByPhone(String phone);
    User selectByEmail(String email);
    User insertUser(User user);
    User updataUser(User user);
    User getAnswer(Long userid);
}
