package com.leetcode.web.service;

import java.util.concurrent.CompletableFuture;

/**
 * @author LWenH
 * @create 2021/2/10 - 0:37
 *
 * 异步任务方法的接口
 */
public interface AsyncService {

    /**
     * 编译运行代码
     * @param code 可以编译运行的代码
     * @param destPath 文件存放的目标位置
     * @param filename 保存的文件名称
     * @return 返回运行结果
     */
    CompletableFuture<String> executeCompileAndRun(String code, String destPath, String filename);
}
