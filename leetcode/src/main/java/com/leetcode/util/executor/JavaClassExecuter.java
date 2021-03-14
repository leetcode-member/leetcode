package com.leetcode.util.executor;

import java.lang.reflect.Method;
import java.util.List;
/**
 * @author LWenH
 * @create 2021/3/10 - 15:01
 * <p>
 * class执行工具
 */
public class JavaClassExecuter {

    /**
     * 运行代表class文件的byte[]
     *
     * @param classBytes   要运行的字节码数组
     * @param paramStrArr 测试用例字符串数组
     * @return
     */
    public static ReturnInfo execute(byte[] classBytes, String[] paramStrArr) {
        // 替换class信息中的System
        ClassModifier classModifier = new ClassModifier(classBytes);
        byte[] modifyedBytes = classModifier.modifyUTF8Constant("java/lang/System", "com/leetcode/util/executor/MySystem");

        HotSwapClassLoader classLoader = new HotSwapClassLoader();
        Class clazz = classLoader.loadByte(modifyedBytes);

        String runtime = "运行时间", memory = "消耗内存";
        try {
            /*
                就只做了一个题目的class中只有一个方法的情况
                多个方法的题目不多，而且处理起来太麻烦（感觉 在线编译+反射 执行代码要自己写太多基础功能）
             */
            Method[] methods = clazz.getMethods();
            Method method = methods[0];
            // 调用构建参数的方法，传入前段传来的字符串和method对象，返回包含所有参数的list
            List list = ParamsUtils.buildParams(paramStrArr, method);
            // 反射执行
            Runtime r = Runtime.getRuntime();
            long before = System.currentTimeMillis();
            r.gc();
            long start = r.freeMemory();
            Object returnValue = method.invoke(clazz.getDeclaredConstructor().newInstance(), list.toArray());
            long end = r.freeMemory();
            long after = System.currentTimeMillis();
            // 处理执行时间和内存消耗
            runtime = String.valueOf(after-before);
            memory = String.valueOf(start-end);
            // 将反射执行得到的Object类型的返回值转换为String类型
            String returnType = method.getReturnType().getSimpleName();
            String returnStr = ParamsUtils.returnValue2Str(returnType, returnValue);
            return new ReturnInfo(true, returnStr, MySystem.getBufferString(), runtime,memory);
        } catch (Throwable e) {
            e.printStackTrace(MySystem.out);
            String outInfo = MySystem.getBufferString();
            String errInfo;
            if (outInfo.contains("Caused by")) {
                int i = MySystem.getBufferString().indexOf("Caused by");
                errInfo = MySystem.getBufferString().substring(i);
            } else {
                errInfo = outInfo;
            }
            return new ReturnInfo(false,null, errInfo,runtime,memory);
        } finally {
            MySystem.closeBuffer();
        }
    }
}
