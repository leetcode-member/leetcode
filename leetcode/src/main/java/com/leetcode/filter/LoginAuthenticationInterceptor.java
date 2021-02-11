package com.leetcode.filter;


import com.leetcode.config.BeanConfig;
import com.leetcode.model.constant.TokenConstant;
import com.leetcode.model.constant.UserRoleConstant;
import com.leetcode.model.exception.TokenExpiredException;
import com.leetcode.util.string.StringUtil;
import com.leetcode.util.token.TokenUtil;
import jdk.nashorn.internal.parser.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author Jarvan
 * @create 2020/8/16 15:48
 */
@Slf4j
@Component
public class LoginAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    BeanConfig beanConfig;


    /**
     * 处理器的拦截操作.
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("=======进入拦截器========");
        // 如果不是映射到方法直接通过,可以访问资源.
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        //为空就返回错误
        String token = httpServletRequest.getHeader(TokenConstant.TOKEN);
        if (StringUtil.isEmpty(token)) {
            return false;
        }
        log.info("==============token:" + token);
        Map<String, String> map = tokenUtil.parseToken(token);
        String userId = map.get(TokenConstant.USER_ID_CLAIN);
        String userRole = map.get(TokenConstant.USER_ROLE_CLAIN);
        long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get(TokenConstant.TIME_STAMP_CLAIN));
        //1.判断 token 是否过期
        //年轻 token
        if (timeOfUse < beanConfig.getYangToken()) {
           log.info("年轻 token");
        }
        //老年 token 就刷新 token
        else if (timeOfUse >= beanConfig.getYangToken() && timeOfUse < beanConfig.getOldToken()) {
            httpServletResponse.setHeader(TokenConstant.TOKEN,tokenUtil.getToken(userId,userRole));
        }
        //过期 token 就返回 token 无效.
        else {
            throw new TokenExpiredException();
        }
        //2.角色匹配.
        if (UserRoleConstant.ROLE_USER.equals(userRole)) {
            log.info("========user账户============");
            return true;
        }
        if (UserRoleConstant.ROLE_ADMIN.equals(userRole)) {
            log.info("========admin账户============");
            return true;
        }
        return false;
    }



}
