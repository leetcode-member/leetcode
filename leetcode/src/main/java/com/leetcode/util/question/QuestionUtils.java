package com.leetcode.util.question;


import com.leetcode.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LWenH
 * @create 2021/2/8 - 14:46
 * <p>
 * 对题目进行操作的工具类
 */
@Slf4j
public class QuestionUtils {

    /**
     * 判断用户提交的代码中的方法是否是多参数方法
     *
     * @param code 用户提交的代码
     * @return 如果有多个参数返回true，否则返回false
     */
    public static boolean multiParams(String code) {
        QuestionInfo questionInfo = QuestionUtils.parseQuestion(code);
        int paramCount = questionInfo.getParamList().size();
        return paramCount > 1;
    }

    /**
     * 解析用户提交的代码，将需要的信息封装起来
     *
     * @param code 用户提交的代码
     * @return
     */
    private static QuestionInfo parseQuestion(String code) {
        QuestionInfo questionInfo = new QuestionInfo();
        // 获取到方法第一行的信息  类似于这样：public int[] twoSum(int[] nums, int target)
        int i = code.indexOf("{");
        String methodFirstRow = code.substring(i + 1, code.indexOf("{", i + 1)).trim();
        System.out.println(methodFirstRow);
        // 获取方法返回值
        int firstSpace = methodFirstRow.indexOf(" ");
        int secondSpace = methodFirstRow.indexOf(" ", firstSpace + 1);
        String returnType = methodFirstRow.substring(firstSpace + 1, secondSpace);
        log.info("返回值类型={}", returnType);
        questionInfo.setReturnType(returnType);
        // 获取方法名称
        int firstBracket = methodFirstRow.indexOf("(");
        String methodName = methodFirstRow.substring(secondSpace + 1, firstBracket);
        log.info("方法名称={}", methodName);
        questionInfo.setMethodName(methodName);
        // 获取方法的参数信息
        int secondBracket = methodFirstRow.indexOf(")");
        String paramInfo = methodFirstRow.substring(firstBracket + 1, secondBracket);
        log.info("参数信息={}", paramInfo);
        // 获取参数列表
        String[] paramArr = paramInfo.split(", ");
        List<String> paramList = new ArrayList<>(Arrays.asList(paramArr));
        questionInfo.setParamList(paramList);
        // 获取参数名称
        List<String> paramNames = new ArrayList<>();
        for (String s : paramArr) {
            String paramName = s.substring(s.indexOf(" ") + 1);
            log.info("参数名称={}", paramName);
            paramNames.add(paramName);
        }
        questionInfo.setParamNames(paramNames);
        return questionInfo;
    }

    /**
     * 合并用户提交的代码和测试代码，组成一个可以编译运行的字符串
     *
     * @param testCaseList 用户提交代码的信息
     * @return
     */
    public static String mergeCode(String userCode, List<String> testCaseList) {
        QuestionInfo questionInfo = QuestionUtils.parseQuestion(userCode);
        /*
            拼接测试代码
         */
        List<String> paramList = questionInfo.getParamList();
        StringBuilder testCode = new StringBuilder(TestCode.MAIN_CODE);
        // 构建定义参数的语句
        for (int i = 0; i < paramList.size(); i++) {
            // 注意这里的参数都是按顺序进行拼接的，前端给过来的测试用例必须和参数列表顺序一致。
            String param = paramList.get(i);
            if (param.contains("[]")) {
                // 如果参数信息中包含[]，就可以说明这个参数是个数组，按照定义数组的法方式来写
                String testCase1 = testCaseList.get(i).replace("[", "{").replace("]", "}");
                String row1 = param + " = " + testCase1 + ";\n";
                log.info("测试数组的构建={}", row1);
                testCode.append(row1);
            } else if (param.contains("List")) {
                // 如果参数信息中包含"List"，则说明这个参数是一个List集合类型，按照集合的定义方式来写
                String testCase2_1 = testCaseList.get(i);
                String testCase2_2 = testCaseList.get(i).substring(1, testCase2_1.length() - 1);
                String row2 = param + " = " + "Arrays.asList(" + testCase2_2 + ");\n";
                log.info("测试集合的构建={}", row2);
                testCode.append(row2);
            } else {
                // 如果是普通的类型（比如基本类型和String），就直接定义
                String testCase3 = testCaseList.get(i);
                String row3 = param + " = " + testCase3 + ";\n";
                log.info("测试简单类型的构建={}", row3);
                testCode.append(row3);
            }
            /*
                因为技术有限，只能做这三种参数类型了（数组，List集合和直接定义的参数）
                Map集合的话我在leetcode看了，很少有参数是map类型，做的话也可以，就是很麻烦，暂时不写了
                其他类型，比如说力扣上的TreeNode，ListNode这种，因为情况太多变，我感觉改改数据库还能做，目前不好写
             */
        }
        // 在此插入计算时间和内存的语句
        testCode.append("        Runtime r = Runtime.getRuntime();\n" +
                "        long startMem, endMem, before, after;\n" +
                "        r.gc();\n" +
                "        startMem = r.freeMemory();\n" +
                "        before = System.currentTimeMillis();\n");

        // 构建执行和打印语句
        String methodName = questionInfo.getMethodName();
        List<String> paramNames = questionInfo.getParamNames();
        StringBuilder sb = new StringBuilder("s.").append(methodName).append("(");
        for (String paramName : paramNames) {
            sb.append(paramName).append(", ");
        }
        // 把最后一组", "替换成括号
        StringBuilder row4 = sb.replace(sb.length() - 2, sb.length(), ")");
        String returnType = questionInfo.getReturnType();
        int count = StringUtil.getSubCount(returnType, "[]");
        StringBuilder last = null;
        if ("void".equals(returnType)) {
            // 如果无返回值，直接加上分号换行
            last = row4.append(";\n");
        } else if (returnType.contains("[]") && count >= 2) {
            // 如果返回值包含"[]"并且个数大于等于2个，说明返回值是二维数组，调用相应的方法打印
            last = new StringBuilder("System.out.println(Arrays.deepToString(")
                    .append(row4).append("));\n");
        } else if (returnType.contains("[]") && count == 1) {
            // 如果返回值只包含一个"[]"，说明是一位数字，调用相应方法打印
            last = new StringBuilder("System.out.println(Arrays.toString(")
                    .append(row4).append("));\n");
        } else {
            // 如果返回值不是void，不是数组，那么还有可能是基本类型、String、集合这种，直接打印
            last = new StringBuilder("System.out.println(").append(row4).append(");\n");
        }
        // 将执行输出语句添加
        testCode.append(last);
        // 添加计算消耗内存和时间的代码段
        testCode.append("        after = System.currentTimeMillis();\n" +
                "        endMem = r.freeMemory();\n" +
                "        System.out.println(startMem - endMem);\n" +
                "        System.out.println(after - before);\n}\n}");
        // 将用户的方法和测试执行代码拼装起来。
        StringBuffer finalCode = new StringBuffer("import java.util.*;\n");
        finalCode.append(userCode, 0, userCode.lastIndexOf("}")).append(testCode);
        System.out.println(finalCode);

        return finalCode.toString();
    }

    public static void main(String[] args) {
        String code = "class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "        int[] result = new int[2];\n" +
                "        for (int i = 0; i < nums.length - 1; i++) {\n" +
                "            for (int j = i + 1; j < nums.length; j++) {\n" +
                "                if ((nums[i] + nums[j]) == target) {\n" +
                "                    result[0] = i;\n" +
                "                    result[1] = j;\n" +
                "                    break;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        return result;\n" +
                "    }\n" +
                "}";
        List<String> list = new ArrayList<>();
        list.add("[2,7,11,15]");
        list.add("9");
//        list.add("[\"leet\",\"code\"]");
        String s = QuestionUtils.mergeCode(code, list);
        System.out.println(s);
    }
}
