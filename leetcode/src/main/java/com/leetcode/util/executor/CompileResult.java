package com.leetcode.util.executor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LWenH
 * @create 2021/3/13 - 19:53
 * <p>
 * 编译结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompileResult {
    /**
     * 编译的正误 true代表正确
     */
    private boolean compileRight;

    /**
     * 编译正确得到的字节数组
     */
    private byte[] classbytes;
    /**
     * 编译错误返回的信息
     */
    private String compileInfo;

    /**
     * 编译正确需要用到的构造
     *
     * @param compileRight
     * @param classbytes
     */
    public CompileResult(boolean compileRight, byte[] classbytes) {
        this.compileRight = compileRight;
        this.classbytes = classbytes;
    }

    /**
     * 编译错误需要用到的构造
     *
     * @param compileRight
     * @param compileInfo
     */
    public CompileResult(boolean compileRight, String compileInfo) {
        this.compileRight = compileRight;
        this.compileInfo = compileInfo;
    }
}
