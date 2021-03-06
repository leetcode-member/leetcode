package com.leetcode.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author  tanshixing
 */
@Data
public class RequestCodeDTO  implements Serializable  {
    private String method;
    private String number;

}
