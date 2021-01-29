package com.leetcode.util.string;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2020/12/1 16:33
 */
public class StringUtil {
    /**
     * 判断是否为空，
     * @param string
     * @return
     */
    public static boolean isEmpty(String string){
        if (string.length()==0){
            return true;
        }else if (string.trim().length()==0){
            return true;
        }
        return false;
    }
}
