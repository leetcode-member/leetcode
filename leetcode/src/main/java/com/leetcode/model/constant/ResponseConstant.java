package com.leetcode.model.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author: 周宗成
 * @Date: 2021/2/4 16:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseConstant {
    private Integer code;
    private String message;
    private Object data;

    public ResponseConstant success(Object data) {
        ResponseConstant response = new ResponseConstant();
        response.setCode(0);
        response.setMessage("操作成功");
        response.setData(data);
        return response;
    }

    public ResponseConstant success() {
        ResponseConstant response = new ResponseConstant();
        response.setCode(0);
        response.setMessage("操作成功");
        return response;
    }
}
