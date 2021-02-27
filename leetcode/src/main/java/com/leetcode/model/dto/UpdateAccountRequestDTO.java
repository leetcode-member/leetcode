package com.leetcode.model.dto;

import lombok.Data;

/**
 * @author tanshixing
 */
@Data
public class UpdateAccountRequestDTO {

    private String newAccount;
    private String oldAccount;
    private String code;
    private String method;

}
