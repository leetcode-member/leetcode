package com.leetcode.token;

import com.leetcode.config.BeanConfig;
import com.leetcode.model.constant.TokenConstant;
import com.leetcode.model.constant.UserRoleConstant;
import com.leetcode.util.authCode.AuthCodeUtil;
import com.leetcode.util.authCode.RandomUtil;
import com.leetcode.util.redis.RedisUtil;
import com.leetcode.util.token.TokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/30 21:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TokenTest02 {
    @Autowired
    TokenUtil tokenUtil;

    /**
     * 测试 token 获得 和解析
     * @throws InterruptedException
     */
    @Test
    public void connectionTest() throws InterruptedException {
        //1357259291638530049
        String token = tokenUtil.getToken("1357259569548955649", UserRoleConstant.ROLE_USER);
        System.out.println(token);
        Map<String, String> map = tokenUtil.parseToken(token);
        System.out.println(map.get(TokenConstant.USER_ROLE_CLAIN));
        System.out.println(map.get(TokenConstant.USER_ID_CLAIN));
        System.out.println(map.get(TokenConstant.TIME_STAMP_CLAIN));
    }

//    获得 token yaml 值
    @Autowired
    BeanConfig beanConfig;
    @Test
    public void getTokenTimeYamlTest(){
        System.out.println(beanConfig.getOldToken());
        System.out.println(beanConfig.getYangToken());
    }
}
