package com.leetcode.util.authCode;


/**
 * <p>
 * 随机数工具类
 * </p>
 *
 * @author zchen
 * @create 2018-09-25 15:32
 */

public class RandomUtil {

    /**
     * 为编码生成随机数
     *
     * @return
     */
    public static Integer getForeRandomCode() {
        return (int) ((Math.random() * 9 + 1) * 1000);
    }

    /**
     * 生成 6 位随机数
     *
     * @return
     */
    public static Integer getSixRandomCode() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }


    /**
     * 生成12位随机数
     *
     * @return
     */
    public static String getTwelveRandomCode() {
        double v = ((Math.random() * 9 + 1) * 100000000000.0);
        Long v2 = (long) v;

        return v2.toString();
    }
}
