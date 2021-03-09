package com.leetcode.model.exception;

/**
 * 请求错误
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/26 16:42
 */
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }
}
