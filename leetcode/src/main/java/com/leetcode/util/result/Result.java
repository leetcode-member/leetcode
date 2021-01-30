package com.leetcode.util.result;

import lombok.Data;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/30 9:48
 */

@Data
public  class Result<T> {

    private Integer code;

    private String message;

    private  T data;
    private Result(){
        this.code = 200;
        this.message = "ok";
        this.data = null;
    }
    private Result(ResultCode resultCode,T data){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 200	OK
     */
    public static <E> Result ok(E data){
        return new Result<E>(ResultCode.OK,data);
    }
    /**
     * 400	请求参数或者方式错误
     */
    public static <E> Result badRequest(E data){
        return new Result<E>(ResultCode.BAD_REQUEST,data);
    }
    /**
     * 401	没有授权
     */
    public static <E> Result unauthorized(E data){
        return new Result<E>(ResultCode.UNAUTHORIZED,data);
    }
    /**
     * 403	授权拒绝，权限不够
     */
    public static <E> Result permissionDenied(E data){
        return new Result<E>(ResultCode.PERMISSION_DENIED,data);
    }    /**
     * 404	找不到请求资源
     */
    public static <E> Result unknown(E data){
        return new Result<E>(ResultCode.UNKNOWN,data);
    }





}
