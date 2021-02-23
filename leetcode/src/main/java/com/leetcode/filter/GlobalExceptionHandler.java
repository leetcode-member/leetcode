package com.leetcode.filter;

import com.leetcode.model.exception.ParameterEmptyException;
import com.leetcode.model.exception.TokenExpiredException;
import com.leetcode.util.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理类
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/4 21:06
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 用户 token 过期
     * @return
     */
    @ExceptionHandler(value = TokenExpiredException.class)
    @ResponseBody
    public Result<String> tokenExpiredExceptionHandler(){
        log.warn("用户 token 过期");
        return Result.unauthorized("用户 token 过期");
    }
    /**
     * 空指针异常
     * @return
     */
    @ExceptionHandler(value = ParameterEmptyException.class)
    @ResponseBody
    public Result<String> parameterEmptyExceptionHandler(){
        log.warn("参数为空(空指针异常)");
        return Result.badRequest("参数为空(空指针异常)");
    }

}
