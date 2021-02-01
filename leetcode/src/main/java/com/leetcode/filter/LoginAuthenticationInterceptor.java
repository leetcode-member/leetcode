package com.leetcode.filter;


import com.leetcode.model.constant.TokenConstant;
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
//@Component
public class LoginAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    TokenUtil tokenUtil;


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
        if (StringUtil.isEmpty(token)){
            return false;
        }
        String host = httpServletRequest.getHeader("Host");
        log.info("=============="+host);
        log.info("=============="+token);
        log.info("=============="+tokenUtil);

        Map<String, String> map = tokenUtil.parseToken(token, host);
        //角色匹配就通行.
        String roleCode = map.get("roleCode");
        if ("admin".equals(roleCode)){
            log.info("========token验证通过============");
            return true;
        }
        return false;
    }

}
