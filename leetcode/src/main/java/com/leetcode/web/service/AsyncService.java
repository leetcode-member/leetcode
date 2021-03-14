package com.leetcode.web.service;

import com.leetcode.util.executor.ReturnInfo;

import java.util.concurrent.CompletableFuture;

/**
 * @author LWenH
 * @create 2021/2/10 - 0:37
 * <p>
 * 异步任务方法的接口
 */
public interface AsyncService {

    /**
     * 基于本地编译运行代码（第一遍写的）
     *
     * @param code     可以编译运行的代码
     * @param destPath 文件存放的目标位置
     * @param filename 保存的文件名称
     * @return 返回运行结果
     */
    CompletableFuture<String> executeCompileAndRun(String code, String destPath, String filename);

    /**
     * 基于动态编译+反射的编译运行代码
     *
     * @param code        要编译运行的代码
     * @param paramStrArr String[]类型的参数数组
     * @return
     */
    CompletableFuture<ReturnInfo> compileAndExecute(String code, String[] paramStrArr);

    /**
     * 运行编译好的代码
     *
     * @param classBytes  内存中的字节码数组
     * @param paramStrArr 参数数组
     * @return
     */
    CompletableFuture<ReturnInfo> execute(byte[] classBytes, String[] paramStrArr);

}
