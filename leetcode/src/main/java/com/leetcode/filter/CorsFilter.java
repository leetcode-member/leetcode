package com.leetcode.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域过滤器
 *
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/30 13:58
 */
@Component
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;

        //防止恶意 js 攻击
        res.addHeader("x-content-type-options", "nosniff ");
        //防止嵌入其他 frame ，恶意引导用户点击
        res.addHeader("X-Frame-Options", "deny");
        //类型规范+防止乱码
        res.addHeader("Content-Type", "application/json;charset=UTF-8");
        //防止 xxs 跨站点脚本攻击
        res.addHeader("x-xss-protection", "1; mode=block");

        if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
            response.getWriter().println("ok");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}

