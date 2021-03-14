package com.leetcode.util.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author LWenH
 * @create 2021/3/11 - 18:36
 *
 * 这个类是我做测试用的，项目不需要可以删掉
 */
public class Test3 {
    public static void main(String[] args) {
        String s = "[[0,6,0],[5,8,7],[0,9,0]]";
        String[] split = s.split("],\\[");
        System.out.println(split[0]);
        System.out.println(split[1]);
        System.out.println(split[2]);

        for (int j = 0; j < split.length; j++) {
            split[j] = split[j].replace("[", "").replace("]", "");
            System.out.println(split[j]);
        }


        int[][] ints = ArrayUtils.str2IntArray2(split);
        System.out.println(Arrays.deepToString(ints));

        String ss = "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]";
        String[] split2 = ss.split("],\\[");
        for (int j = 0; j < split2.length; j++) {
            split2[j] = split2[j].replace("[", "").replace("]", "").replace("\"","").replace(",","");
            System.out.println(split2[j]);
        }

        System.out.println(Arrays.toString(split2));

        char[][] chars = ArrayUtils.str2CharArray2(split2);
        System.out.println(Arrays.deepToString(chars));

        String test = "[[\"li\",\"wen\"],[\"hao\"]]";
        String[] noArr = test.split("],\\[");
        for (int j = 0; j < noArr.length; j++) {
            noArr[j] = noArr[j].replace("[", "").replace("]", "");
        }

//        String[][] strArray2 = new String[noArr.length][];
//        for (int j = 0; j < noArr.length; j++) {
//            strArray2[j] = noArr[j].replace("\"","").split(",");
//        }
//        System.out.println(Arrays.deepToString(strArray2));

        String[][] strings = ArrayUtils.str2StrArray2(noArr);
        System.out.println(Arrays.deepToString(strings));


    }
}
