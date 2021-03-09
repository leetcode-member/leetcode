package com.leetcode.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanshixing
 */
@Data
public class LoginResponseDTO implements Serializable {

    private String nickname;
    private String sex;
    private String avatar;

}
