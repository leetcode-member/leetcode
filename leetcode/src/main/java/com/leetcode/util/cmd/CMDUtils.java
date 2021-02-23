package com.leetcode.util.cmd;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author LWenH
 * @create 2021/2/8 - 21:16
 * <p>
 * cmd工具类
 */
@Component
public class CMDUtils {

    /**
     * 使用ProcessBuilder管理进程
     * ProcessBuilder主要用于创建操作系统进程
     *
     * @param command
     * @return
     */
    public String execProcessBuider(String... command) {
        // 执行系统命令后返回的信息
        String result = "";
        // 利用指定的操作系统程序和参数构造一个进程生成器。
        ProcessBuilder builder = new ProcessBuilder(command[0], command[1]);
        // 设置此进程生成器的工作目录（也就是.java文件，.class文件所在的那个目录）
        builder.directory(new File(command[2]));
        // 将错误输出和标准输出合并，使得关联错误消息和相应的输出变得更容易
        builder.redirectErrorStream(true);
        try {
            // 启动进程，将会执行刚才设置的参数
            Process cmdProess = builder.start();
            result = cmdExecResultInfo(cmdProess.getInputStream());
            cmdProess.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将执行操作系统命令返回的信息转换为字符串
     *
     * @param inputStream
     * @return
     */
    private String cmdExecResultInfo(InputStream inputStream) {
        StringBuilder result = new StringBuilder();
        BufferedReader cmdExecInfoReader = null;
        try {
            cmdExecInfoReader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            String line;
            while ((line = cmdExecInfoReader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (cmdExecInfoReader != null) {
                try {
                    cmdExecInfoReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

}
