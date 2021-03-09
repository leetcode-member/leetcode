package com.leetcode.filter;

import com.leetcode.model.exception.*;
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

    /**
     * 验证码错误
     * @return
     */
    @ExceptionHandler(value = AuthCodeErrorException.class)
    @ResponseBody
    public Result<String> authCodeErrorException(){
        log.warn("验证码错误");
        return Result.badRequest("验证码错误");
    }
    /**
     * 验证码过期
     * @return
     */
    @ExceptionHandler(value = AuthCodeExpiredException.class)
    @ResponseBody
    public Result<String> authCodeExpiredException(){
        log.warn("验证码过期");
        return Result.badRequest("验证码过期");
    }
    /**
     * 前端请求错误大异常，尽量少用
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    @ResponseBody
    public Result<String> badRequestException( Exception e){
        log.warn("前端请求错误大异常");
        return Result.badRequest(e.getMessage());
    }
    /**
     * 拦截器，token 为空
     * @return
     */
    @ExceptionHandler(value = TokenIsNullException.class)
    @ResponseBody
    public Result<String> tokenIsNullException(){
        log.warn("拦截器，token 为空");
        return Result.badRequest("拦截器，token 为空");
    }

}
