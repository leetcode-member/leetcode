package com.leetcode.util.executor;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.channels.Channel;
import java.util.Map;
import java.util.Properties;

/**
 * @author LWenH
 * @create 2021/3/10 - 14:15
 * <p>
 * 将标准打印流替换成MyPrintStream
 */
public final class MySystem {
    private MySystem() {

    }

    /**
     * 用MyPrintStream替换默认的标准输出和标准错误输出
     */
    public static final PrintStream out = new MyPrintStream();
    public static final PrintStream err = out;
    public static final InputStream in = new MyInputStream();

    /**
     * 获取线程输出流的内容
     *
     * @return
     */
    public static String getBufferString() {
        return out.toString();
    }

    /**
     * 关闭当前线程的输入输出流
     */
    public static void closeBuffer() {
        ((MyInputStream) in).close();
        out.close();
    }

    private static volatile SecurityManager securityManager = null;

    public static String clearProperty(String key) {
        throw new SecurityException("Use hazardous method: System.clearProperty().");
    }

    public static Console console() {
        throw new SecurityException("Use hazardous method: System.console().");
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static void exit(int status) {
        throw new SecurityException("Use hazardous method: System.exit().");
    }

    public static void gc() {
        throw new SecurityException("Use hazardous method: System.gc().");
    }

    public static Map<String, String> getenv() {
        throw new SecurityException("Use hazardous method: System.getenv().");
    }

    public static String getenv(String name) {
        throw new SecurityException("Use hazardous method: System.getenv().");
    }

    public static Properties getProperties() {
        throw new SecurityException("Use hazardous method: System.getProperties().");
    }

    public static String getProperty(String key) {
        throw new SecurityException("Use hazardous method: System.getProperty().");
    }

    public static String getProperty(String key, String def) {
        throw new SecurityException("Use hazardous method: System.getProperty().");
    }

    public static SecurityManager getSecurityManager() {
        throw new SecurityException("Use hazardous method: System.getSecurityManager().");
    }

    public static int identityHashCode(Object x) {
        return System.identityHashCode(x);
    }

    public static Channel inheritedChannel() throws IOException {
        throw new SecurityException("Use hazardous method: System.inheritedChannel().");
    }

    public static String lineSeparator() {
        return System.lineSeparator();
    }

    public static void load(String filename) {
        throw new SecurityException("Use hazardous method: System.load().");
    }

    public static void loadLibrary(String libname) {
        throw new SecurityException("Use hazardous method: System.loadLibrary.");
    }

    public static String mapLibraryName(String libname) {
        throw new SecurityException("Use hazardous method: System.mapLibraryName().");
    }

    public static long nanoTime() {
        return System.nanoTime();
    }

    public static void runFinalization() {
        throw new SecurityException("Use hazardous method: System.runFinalization().");
    }

    @Deprecated
    public static void runFinalizersOnExit(boolean value) {
        throw new SecurityException("Use hazardous method: System.runFinalizersOnExit().");
    }

    public static void setErr(PrintStream err) {
        throw new SecurityException("Use hazardous method: System.setErr().");
    }

    public static void setIn(InputStream in) {
        throw new SecurityException("Use hazardous method: System.setIn().");
    }

    public static void setOut(PrintStream out) {
        throw new SecurityException("Use hazardous method: System.setOut().");
    }

    public static void setProperties(Properties props) {
        throw new SecurityException("Use hazardous method: System.setProperties().");
    }

    public static String setProperty(String key, String value) {
        throw new SecurityException("Use hazardous method: System.setProperty().");
    }

    public static void setSecurityManager(final SecurityManager s) {
        throw new SecurityException("Use hazardous method: System.setSecurityManager().");
    }

}
