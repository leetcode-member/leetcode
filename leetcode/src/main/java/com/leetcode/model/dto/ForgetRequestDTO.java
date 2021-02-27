package com.leetcode.model.dto;

import lombok.Data;

/**
 * @author tanshixing
 */
@Data
public class ForgetRequestDTO {

    private String forGetBody;
    private String newPassword;
    private String code;
    private String method;
}
