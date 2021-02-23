package com.leetcode.util.question;


import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author LWenH
 * @create 2021/2/8 - 14:33
 * <p>
 * 用于封装用户进行执行代码操作时，提交的题目的一些信息
 */
@Data
public class QuestionInfo {

    /**
     * 目前就认为所有的类名都是Solution，
     * leetCode有些题目也有其他类名的，那种题一般执行都比较复杂，类里面方法也有很多，目前没想到怎么实现
     * （这个属性其实用不到）
     */
    private static final String CLASSNAME = "Solution";
    /**
     * 目前只做单方法的题目，所有就只有一个方法名。
     */
    private String methodName;
    /**
     * 方法的参数列表
     */
    private List<String> paramList;
    /**
     * 参数名称
     */
    private List<String> paramNames;
    /**
     * 返回值类型（字符串）
     */
    private String returnType;
}
