package com.leetcode.util.executor;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.util.List;

/**
 * @author LWenH
 * @create 2021/3/10 - 15:25
 *
 * 这个类是我做测试用的，项目不需要可以删掉
 */
public class Test {
    public static void main(String[] args) {
        test1();
//        test2();
    }

    public static void test1() {

        String code = "import java.util.*;\npublic " +
                "class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "        int i = 1 / 0;\n" +
                "        int[] result = new int[2];\n" +
                "        for (int i=0; i < nums.length-1; i++) {\n" +
                "            for (int j = i+1; j < nums.length; j++) {\n" +
                "                if ((nums[i] + nums[j]) == target) {\n" +
                "                    result[0] = i;\n" +
                "                    result[1] = j;\n" +
                "                    break;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        System.out.println(\"测试\");\n" +
                "        return result;\n" +
                "    }\n" +
                "}";
//        System.out.println(code);

        DiagnosticCollector<JavaFileObject> compileCollector = new DiagnosticCollector<>();
        byte[] classBytes = MyCompiler.compile(code, compileCollector);

        //如果编译不通过则打印错误信息
        if (classBytes == null) {
            List<Diagnostic<? extends JavaFileObject>> compileError = compileCollector.getDiagnostics();
            StringBuilder compileErrorRes = new StringBuilder();
            for (Diagnostic diagnostic : compileError) {
                compileErrorRes.append("Compilation error at ");
                compileErrorRes.append(diagnostic.getLineNumber());
                compileErrorRes.append(".");
                compileErrorRes.append(System.lineSeparator());
            }
            System.out.println(compileErrorRes.toString());
            return;
        }
        String get = "[2,7,11,15]\n9";
        String[] split = get.split("\n");

        ReturnInfo returnInfo = JavaClassExecuter.execute(classBytes, split);

        System.out.println(returnInfo);

    }

}
