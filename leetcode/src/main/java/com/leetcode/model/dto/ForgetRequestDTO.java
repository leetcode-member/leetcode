package com.leetcode.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tanshixing
 */
@Data
public class ForgetRequestDTO implements Serializable {

    private String forGetBody;
    private String newPassword;
    private String code;
    private String method;
}
