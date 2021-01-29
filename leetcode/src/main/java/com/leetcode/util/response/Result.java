package com.leetcode.util.response;

import lombok.Data;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2020/11/28 13:03
 */

@Data
public  class Result<T> {

    private Integer code;

    private String message;

    private  T data;

    private Result(ResultCode resultCode,T data){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 暴露外面静态方法. 成功
     */
    public static <E> Result success(E data){
        return new Result<E>(ResultCode.SUCCESS,data);
    }

    /**
     * 失败
     */
    public static <E> Result fail(E data){
        return new Result<E>(ResultCode.FAIL,data);
    }

}

