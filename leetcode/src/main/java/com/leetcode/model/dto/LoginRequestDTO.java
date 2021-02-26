package com.leetcode.model.dto;

import lombok.Data;

/**
 * @author  tanshixing
 */
@Data
public class LoginRequestDTO {
    /**
     * 对应的 phone 或者世 Email 的值
     */
    private String registerBody;
    private String password;
    /**
     * phone or email
     */
    private String method;
}
