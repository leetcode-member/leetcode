package com.leetcode.util.executor;

import java.io.InputStream;

/**
 * @author LWenH
 * @create 2021/3/10 - 14:32
 */
public class MyInputStream extends InputStream {
    public static final ThreadLocal<InputStream> MY_INPUT_STREAM = new ThreadLocal<>();

    @Override
    public int read() {
        return 0;
    }

    @Override
    public void close() {
        MY_INPUT_STREAM.remove();
    }
}
