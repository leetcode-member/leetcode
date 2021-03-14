package com.leetcode.util.executor;

import com.leetcode.util.string.StringUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LWenH
 * @create 2021/3/10 - 22:31
 *
 * 这个类是我做测试用的，项目不需要可以删掉
 */
public class Test2 {
    public List<List<Integer>> test(List<String> list) {
        Integer i = 5;
        int ii = 9;
//        test2(ii);
        List<List<Integer>> listList = new ArrayList<>();

        List<Integer> list2 = new ArrayList<>();
        list2.add(i);
        list2.add(ii);

        List<Integer> list3 = new ArrayList<>();
        list3.add(i);
        list3.add(ii);

        listList.add(list2);
        listList.add(list3);

        return listList;
    }

    public int test2() {
        return 5;
    }

    public int[][] test3() {
        int[][] int2 = new int[2][];
        int[] ints1 = {7,8,9};
        int[] ints = {4,5,6};
        int2[0] = ints;
        int2[1] = ints1;
        return int2;
    }

    public char test4() {
        int[] ints = {4,5,6};
        return 'A';
    }

    public Integer[] test5() {
        Integer[] ints = {4,5,6};
        return ints;
    }

    public Integer[][] test7() {
        Integer[][] integers = new Integer[2][];
        Integer[] ints = {4,5,6};
        Integer[] ints1 = {4,5,6};
        integers[0] = ints;
        integers[1] = ints1;
        return integers;
    }

    public String[] test6() {
        String[] strs = {"test","return"};
        return strs;
    }


    public void test8() {
//        System.out.println(i);
    }
}

