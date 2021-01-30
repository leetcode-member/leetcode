package com.leetcode.util.result;

import lombok.Getter;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/1/30 9:43
 */
@Getter
public enum ResultCode {
    /** 成功*/
    OK(200,"OK"),
    /** 请求参数或者方式错误 */
    BAD_REQUEST(400,"BAD_REQUEST"),
    /** 没有授权 */
    UNAUTHORIZED(401,"UNAUTHORIZED"),
    /** 授权拒绝，权限不够 */
    PERMISSION_DENIED(403,"PERMISSION_DENIED"),
    /** 找不到请求资源 */
    NOT_FOUND(404,"NOT_FOUND"),
    /** 服务器未知错误 */
    UNKNOWN(500,"UNKNOWN");

    /** 错误代码*/
    private Integer code;
    /** 提示信息*/
    private String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}


