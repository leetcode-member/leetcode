package com.leetcode.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tanshixing
 */
@Data
public class UpdateInfoRequestDTO implements Serializable {

    private String nickname;
    private String password;
    private String sex;
    private String avatar;
}
