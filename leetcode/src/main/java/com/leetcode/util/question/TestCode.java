package com.leetcode.util.question;

import lombok.Data;

/**
 * @author LWenH
 * @create 2021/2/8 - 16:28
 *
 * 测试方法的模板
 */
@Data
public class TestCode {

    /**
     * 每一个可执编译运行的.java文件中的公共部分。
     */
    public static final String MAIN_CODE = "public static void main(String[] args) {\n" +
                                     "Solution s = new Solution();\n";

}
