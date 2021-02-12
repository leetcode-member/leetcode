package com.leetcode.lwh_test;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LWenH
 * @create 2021/2/7 - 17:37
 */
@SpringBootTest
public class ParseQuestionTest {

    @DisplayName("获取题目关键信息")
    @Test
    public void getQuestionImformation() {
        // 用户提交的代码
        String code = "class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "        // int i = 1 / 0;\n" +
                "        int[] result = new int[2];\n" +
                "        for (int i=0; i < nums.length-1; i++) {\n" +
                "            for (int j = i+1; j < nums.length; j++) {\n" +
                "                if ((nums[i] + nums[j]) == target) {\n" +
                "                    result[0] = i;\n" +
                "                    result[1] = j;\n" +
                "                    break;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        return result;\n" +
                "    }\n" +
                "}";
//        System.out.println(code);
        // 获取第一个括号出现的位置
        int firstSign = code.indexOf("(");
//        System.out.println(firstSign);

        int i = code.indexOf("{");
        int j = code.indexOf("{", i + 1);
        System.out.println(i + "---" + j);

        String s1 = code.substring(i + 1, j);
        String s2 = s1.trim();
        System.out.println(s2);

        System.out.println(s2.indexOf(" "));

        System.out.println(s2.indexOf(" ", 7));

        int k = s2.indexOf("(");

        // 截取出方法名字符串
        System.out.println(s2.substring(12+1, k));

        // 截取出参数列表
        System.out.println(s2);
        int l = s2.indexOf(")");
        String params = s2.substring(k + 1, l);
        String[] paramss = params.split(", ");
        for (String s : paramss) {
            // 得到参数列表信息
            System.out.println(s);

            System.out.println(s.substring(s.indexOf(" ") + 1));
        }
    }

    @Test
    public void test1() {
        String test = "1234";

    }




}
