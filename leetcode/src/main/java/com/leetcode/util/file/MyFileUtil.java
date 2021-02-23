package com.leetcode.util.file;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author LWenH
 * @create 2021/2/8 - 21:57
 *
 * 操作文件的工具类
 */
@Component
public class MyFileUtil {

    /**
     * 写出文件
     * @param filePath 文件地址
     * @param content 写出的内容
     */
    public void writeFile(String filePath, String content) {
        File file = new File(filePath);
        PrintWriter pw = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
                pw = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(file), "GBK")));
                pw.write(content);
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }
        }
    }
}
