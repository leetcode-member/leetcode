package com.leetcode.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanshixing
 */
@Data
public class LoginRequestDTO  implements Serializable {
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
