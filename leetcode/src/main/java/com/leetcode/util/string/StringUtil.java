package com.leetcode.util.string;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2020/12/1 16:33
 */
public class StringUtil {
    /**
     * 不为空
     */
    public static boolean isNotEmpty(String string){
        return !isEmpty(string);
    }
    /**
     * 判断是否为空，
     * @param string
     * @return
     */
    public static boolean isEmpty(String string){
        if (null == string) {
            return false;
        }
        if (string.length()==0){
            return true;
        }else if (string.trim().length()==0){
            return true;
        }
        return false;
    }

    /**
     * 判断一个字符串在另一个字符串中出现的次数
     * @param str 比较长的字符串（上述的另一个）
     * @param key 要判断次数的字符串
     * @return
     */
    public static int getSubCount(String str, String key) {
        int count = 0;
        int index = 0;
        while((index = str.indexOf(key)) != -1)
        {
            str = str.substring(index+key.length());
            count++;
        }
        return count;
    }
}
