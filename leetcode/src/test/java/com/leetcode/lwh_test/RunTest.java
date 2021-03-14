package com.leetcode.lwh_test;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * @author LWenH
 * @create 2021/3/9 - 21:31
 */
@SpringBootTest
public class RunTest {

    @Test
    public void test5() {
        try {
            Class clazz = Class.forName("com.leetcode.util.executor.Test2");
            Method[] methods = clazz.getMethods();
            Method method = methods[0];
            Parameter[] parameters = method.getParameters();
            Parameter parameter = parameters[0];
            String typeName = parameter.getParameterizedType().getTypeName();
            String simpleTypeName = typeName.replace("java.util.", "").replace("java.lang.", "");
            System.out.println("参数类型=" + simpleTypeName);

            Class returnType = method.getReturnType();
            System.out.println("返回值类型=" + returnType.getSimpleName());

            List list = new ArrayList();
            Object returnValue = method.invoke(clazz.newInstance(), list);
            String returnStr = returnValue.toString();
            System.out.println(returnStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test8() {
        try {
            Class clazz = Class.forName("com.leetcode.util.executor.Test2");
            Method test3 = clazz.getMethod("test3", null);
            Object returnValue = test3.invoke(clazz.newInstance(), null);
            String returnStr = Arrays.deepToString((Object[]) returnValue);
            System.out.println(returnStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test9() {
        Class clazz = null;
        try {
            clazz = Class.forName("com.leetcode.util.executor.Test2");
            Method test4 = clazz.getMethod("test4", null);
            Object returnValue = test4.invoke(clazz.newInstance(), null);
            String returnStr = returnValue.toString();
            System.out.println(returnStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test11() {
        Class clazz = null;
        try {
            clazz = Class.forName("com.leetcode.util.executor.Test2");
            Method test5 = clazz.getMethod("test5", null);
            Object returnValue = test5.invoke(clazz.newInstance(), null);
            String returnStr = Arrays.toString((Integer[]) returnValue);
            System.out.println(returnStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test12() {
        Class clazz = null;
        try {
            clazz = Class.forName("com.leetcode.util.executor.Test2");
            Method test6 = clazz.getMethod("test6", null);
            Object returnValue = test6.invoke(clazz.newInstance(), null);
            String returnStr = Arrays.toString((Object[]) returnValue);
            System.out.println(returnStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test13() {
        Class clazz = null;
        try {
            clazz = Class.forName("com.leetcode.util.executor.Test2");
            Method test7 = clazz.getMethod("test7", null);
            Object returnValue = test7.invoke(clazz.newInstance(), null);
            String returnStr = Arrays.deepToString((Object[]) returnValue);
            System.out.println(returnStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test14() {
        Class clazz = null;
        try {
            clazz = Class.forName("com.leetcode.util.executor.Test2");
            Method test8 = clazz.getMethod("test8", null);
            Object returnValue = test8.invoke(clazz.newInstance(), null);

            System.out.println("返回值类型=" + test8.getReturnType().getSimpleName());
//            String returnStr = Arrays.deepToString((Object[]) returnValue);
//            System.out.println(returnStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test7() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list);
    }

    @Test
    public void test6() {
        int[] intarr = new int[4];
        System.out.println(Integer.class.getSimpleName());
        System.out.println(int.class.getSimpleName());
        System.out.println(String.class.getSimpleName());

        String s = "\"20th Oct 2052\"";
        System.out.println(s);
        System.out.println(s.replace("\"",""));

        String ss = "6";
        char c = ss.charAt(0);
        System.out.println(c);
    }

    @Test
    public void test10() {
        String testCase = "12345";
        String[] split = testCase.split("\n");
        System.out.println(Arrays.toString(split));
    }

    /**
     * 判断括号是否匹配
     * @param s 只包含([{的字符串
     * @return
     */
    public boolean isVaild(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (ch == ')' && pop != '(') {
                    return false;
                } else if (ch == ']' && pop != '[') {
                    return false;
                } else if (ch == '}' && pop != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void testIsVaild() {
        String s = "(){}[]][";
        System.out.println(isVaild(s));
    }

    /*
        这个方法基本上把我构建参数过程中很难搞的都写进来了，只要测试过了大部分题目就没问题了
        我没有考虑无参数的情况，因为我没看到这种情况（太少了可能），有的话就换个题目来测试吧
    */
    public int[][] testBuildParam(List<Integer> list1, List<List<String>> list2, char[][] chars, double[] doubles) {
        System.out.println(list1.toString());
        System.out.println(list2.toString());
        System.out.println(Arrays.deepToString(chars));
        System.out.println(Arrays.toString(doubles));

        int[][] ints2 = new int[2][];
        int[] ints1 = {1,2,3};
        ints2[0] = ints1;
        ints2[1] = ints1;
        return ints2;
    }


}
