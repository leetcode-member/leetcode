package com.leetcode.util.executor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LWenH
 * @create 2021/3/10 - 20:32
 * <p>
 * 编译、执行代码的返回信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnInfo {
    /**
     * 编译代码产生的错误
     */
    private String compileError;
    /**
     * 算法的执行正误情况
     * 正确返回true，错误返回false
     */
    private boolean isRight;
    /**
     * 算法的返回值
     */
    private String returnStr;
    /**
     * 打印流中的内容：错误信息或是用户输出的信息
     */
    private String stdout;
    /**
     * 执行代码消耗的时间
     */
    private String runtime;
    /**
     * 执行代码消耗的内存
     */
    private String memory;

    /**
     * 编译出现错误需要用到的构造器
     *
     * @param compileError 编译错误信息
     */
    public ReturnInfo(String compileError) {
        this.compileError = compileError;
    }

    /**
     * 执行代码需要用到的构造器
     *
     * @param isRight   执行是否正确
     * @param returnStr 返回值字符串
     * @param stdout    标准输出
     * @param runtime   运行时间
     * @param memory    消耗内存
     */
    public ReturnInfo(boolean isRight, String returnStr, String stdout, String runtime, String memory) {
        this.isRight = isRight;
        this.returnStr = returnStr;
        this.stdout = stdout;
        this.runtime = runtime;
        this.memory = memory;
    }
}
