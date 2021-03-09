package com.leetcode.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tanshixing
 */
@Data
public class UpdateAccountRequestDTO implements Serializable {

    private String newAccount;
    private String oldAccount;
    private String code;
    private String method;

}
