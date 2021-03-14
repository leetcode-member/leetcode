package com.leetcode.web.service.impl;

import com.leetcode.util.cmd.CMDUtils;
import com.leetcode.util.executor.CompileResult;
import com.leetcode.util.executor.JavaClassExecuter;
import com.leetcode.util.executor.ReturnInfo;
import com.leetcode.util.file.MyFileUtil;
import com.leetcode.web.service.AsyncService;

import com.leetcode.web.service.IQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;


/**
 * @author LWenH
 * @create 2021/2/10 - 0:40
 */
@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {


    @Autowired
    private MyFileUtil myFileUtil;

    @Autowired
    private CMDUtils cmdUtils;

    @Autowired
    private IQuestionService questionService;



    /**
     * 编译运行代码
     *
     * @param code     可以编译运行的代码
     * @param destPath 文件存放的位置（将来在哪个目录编译运行）
     * @param filename 保存的文件名称
     * @return 返回运行结果
     */
    @Override
    @Async("asyncServiceExecutor")
    public CompletableFuture<String> executeCompileAndRun(String code, String destPath, String filename) {
        String filePath = destPath + filename + ".java";
        String classPath = destPath + filename + ".class";
        File javaFile = new File(filePath);
        File classFile = new File(classPath);
        System.out.println(filePath);
        // 将代码写出成为文件
        myFileUtil.writeFile(filePath, code);

        // 编译并返回运行结果
        String compileResult;
        String runResult;
        compileResult = cmdUtils.execProcessBuider("javac", filename + ".java", destPath);
        if (compileResult.equals("")) {
            // 编译期如果无错误，进行下一步运行
            runResult = cmdUtils.execProcessBuider("java", filename, destPath);
            javaFile.delete();
            classFile.delete();
            return CompletableFuture.completedFuture(runResult.substring(0, runResult.length() - 1));
        } else {
            // 如果编译的结果不为空，说明编译就出现了问题，直接返回信息
            // 删除文件
            javaFile.delete();
            return CompletableFuture.completedFuture("编译出错_" + compileResult.substring(0, compileResult.length() - 1));
        }

    }

    /**
     * 编译运行代码第二版
     *
     * @param code       要编译运行的代码
     * @param paramStrArr String[]类型的参数数组
     * @return ReturnInfo---算法的返回信息
     */
    @Override
    @Async
    public CompletableFuture<ReturnInfo> compileAndExecute(String code, String[] paramStrArr) {
        ReturnInfo returnInfo;

        CompileResult compileResult = questionService.compileCode(code);
        if (!compileResult.isCompileRight()) {
            // 如果编译不通过
            returnInfo = new ReturnInfo(compileResult.getCompileInfo());
            return CompletableFuture.completedFuture(returnInfo);
        } else {
            // 执行代码
            log.info("参数字符串数组={}", Arrays.toString(paramStrArr));
            returnInfo = JavaClassExecuter.execute(compileResult.getClassbytes(), paramStrArr);
            return CompletableFuture.completedFuture(returnInfo);
        }
    }

    /**
     * 运行代码
     * @param classBytes  内存中的字节码数组
     * @param paramStrArr 参数数组
     * @return 运行信息
     */
    @Override
    @Async
    public CompletableFuture<ReturnInfo> execute(byte[] classBytes, String[] paramStrArr) {
        ReturnInfo returnInfo = JavaClassExecuter.execute(classBytes, paramStrArr);
        return CompletableFuture.completedFuture(returnInfo);
    }
}
