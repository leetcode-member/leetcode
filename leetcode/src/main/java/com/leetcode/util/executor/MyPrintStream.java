package com.leetcode.util.executor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintStream;

/**
 * @author LWenH
 * @create 2021/3/10 - 14:03
 * <p>
 * 自定义打印流，使得每个线程都私有一个输出流，不受其他线程影响
 */
public class MyPrintStream extends PrintStream {

    private ThreadLocal<ByteArrayOutputStream> out;
    private ThreadLocal<Boolean> trouble;

    public MyPrintStream() {
        super(new ByteArrayOutputStream());
        out = new ThreadLocal<ByteArrayOutputStream>();
        trouble = new ThreadLocal<>();
    }

    @Override
    public String toString() {
        if (out.get() != null) {
            return out.get().toString();
        }
        return "";
    }

    /**
     * Check to make sure that the stream has not been closed
     */
    private void ensureOpen() throws IOException {
        if (out.get() == null) {
            out.set(new ByteArrayOutputStream());
        }
    }

    @Override
    public void flush() {
        try {
            ensureOpen();
            out.get().flush();
        } catch (IOException e) {
            trouble.set(true);
        }
    }

    @Override
    public void close() {
        if (out.get() != null) {
            try {
                out.get().close();
            } catch (IOException x) {
                trouble.set(true);
            }
            out.remove();
        }
    }

    @Override
    public boolean checkError() {
        if (out.get() != null) {
            flush();
        }
        return trouble.get() != null ? trouble.get() : false;
    }

    @Override
    public void setError() {
        trouble.set(true);
    }

    @Override
    public void clearError() {
        trouble.remove();
    }

    @Override
    public void write(int b) {
        try {
            ensureOpen();
            out.get().write(b);
            if ((b == '\n')) {
                out.get().flush();
            }
        } catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        } catch (IOException x) {
            trouble.set(true);
        }
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        try {
            ensureOpen();
            out.get().write(buf, off, len);
        } catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        } catch (IOException x) {
            trouble.set(true);
        }
    }

    private void write(char buf[]) {
        try {
            ensureOpen();
            out.get().write(new String(buf).getBytes());
        } catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        } catch (IOException x) {
            trouble.set(true);
        }
    }

    private void write(String s) {
        try {
            ensureOpen();
            out.get().write(s.getBytes());
        } catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        } catch (IOException x) {
            trouble.set(true);
        }
    }

    private void newLine() {
        try {
            ensureOpen();
            out.get().write(System.lineSeparator().getBytes());
        } catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        } catch (IOException x) {
            trouble.set(true);
        }
    }

    @Override
    public void print(boolean b) {
        write(b ? "true" : "false");
    }

    @Override
    public void print(char c) {
        write(String.valueOf(c));
    }

    @Override
    public void print(int i) {
        write(String.valueOf(i));
    }

    @Override
    public void print(long l) {
        write(String.valueOf(l));
    }

    @Override
    public void print(float f) {
        write(String.valueOf(f));
    }

    @Override
    public void print(double d) {
        write(String.valueOf(d));
    }

    @Override
    public void print(char s[]) {
        write(s);
    }

    @Override
    public void print(String s) {
        if (s == null) {
            s = "null";
        }
        write(s);
    }

    @Override
    public void print(Object obj) {
        write(String.valueOf(obj));
    }

    @Override
    public void println() {
        newLine();
    }

    @Override
    public void println(boolean x) {
        print(x);
        newLine();
    }

    @Override
    public void println(char x) {
        print(x);
        newLine();
    }

    @Override
    public void println(int x) {
        print(x);
        newLine();
    }

    @Override
    public void println(long x) {
        print(x);
        newLine();
    }

    @Override
    public void println(float x) {
        print(x);
        newLine();
    }

    @Override
    public void println(double x) {
        print(x);
        newLine();
    }

    @Override
    public void println(char x[]) {
        print(x);
        newLine();
    }

    @Override
    public void println(String x) {
        print(x);
        newLine();
    }

    @Override
    public void println(Object x) {
        String s = String.valueOf(x);
        print(s);
        newLine();
    }
}
