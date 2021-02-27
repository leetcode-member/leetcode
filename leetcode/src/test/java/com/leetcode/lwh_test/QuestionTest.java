package com.leetcode.lwh_test;

import com.leetcode.util.cmd.CMDUtils;
import com.leetcode.util.file.MyFileUtil;
import com.leetcode.util.question.QuestionUtils;
import com.leetcode.util.string.StringUtil;
import com.leetcode.web.mapper.QuestionMapper;
import com.leetcode.web.service.AsyncService;
import com.leetcode.web.service.IQuestionService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

/**
 * @author LWenH
 * @create 2021/2/5 - 19:59
 *
 * 查询题目信息的相关测试类
 */
@SpringBootTest
public class QuestionTest {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

//    RandomUtil randomUtil = new RandomUtil();

    @Test
    void test1() {
//        questionService.getById(null);
//        questionMapper.selectList(null);

//        for (int i = 1; i <= 100; i++) {
//            double result=i +  i/10.0 + i/100.0;
//            String num = String.format("%.2f",result);
//            System.out.println(num);
//
//            Question q = new Question();
//            q.setQuestionNum(num);
//            q.setContent(num);
//            questionMapper.insert(q);
//        }

//        Question question = questionService.randomSelect();
//        System.out.println(question);

//        List<Question> questions = questionMapper.selectList(null);
//        questions.forEach(System.out::println);
//        System.out.println(questions.size());

        Random random = new Random();
        Integer rows = questionMapper.selectCount(null);
        System.out.println(rows);
//        int i = random.nextInt(200);
//        System.out.println(i);
////        Question question = questions.get(i);
////        System.out.println(question);
//
//        questionMapper.randomSelect(i);

    }

    @Test
    void test2() {
        String str = "int[][][][]";
        String key = "[]";
        System.out.println(StringUtil.getSubCount(str, key));
    }

    @Autowired
    private CMDUtils cmdUtils;

    @Autowired
    private MyFileUtil myFileUtil;

    @Test
    void test3() {
        String filepath = "D:\\DeskTop\\tmp";
        String destPath = filepath + "\\Solution.java";
        // 用户提交的代码
        String userCode = "class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "        // int i = 1 / 0;\n" +
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
                "        return result;\n" +
                "    }\n" +
                "}";
        // 用户提交的测试用例
        List<String> testCaseList = Arrays.asList("[2,7,11,15]","9");
        // 得到合并好的代码
        String finalCode = QuestionUtils.mergeCode(userCode, testCaseList);
        // 写出成为文件
        myFileUtil.writeFile(destPath,finalCode);
        // 编译并返回结果
        String compileResult = cmdUtils.execProcessBuider("javac", "Solution.java", filepath);
        System.out.println("编译结果=" + compileResult);
        // 运行并返回结果
        String runResult = cmdUtils.execProcessBuider("java", "Solution", filepath);
        System.out.println("运行结果=" + runResult);


    }

    @Autowired
    private AsyncService asyncService;

    @Test
    void test4() throws ExecutionException, InterruptedException {
        String dethPath = "D:\\DeskTop\\tmp\\";
//        String filePath = filepath + "\\Solution.java";
        // 用户提交的代码
        String userCode = "class Solution {\n" +
                "    public int[] twoSum(int[] nums, int target) {\n" +
                "        // int i = 1 / 0;\n" +
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
                "        return result;\n" +
                "    }\n" +
                "}";
        // 用户提交的测试用例
        List<String> testCaseList = Arrays.asList("[2,7,11,15]","9");
        // 得到合并好的代码
        String finalCode1 = QuestionUtils.mergeCode(userCode, testCaseList).replace("Solution","Solution1");
        String finalCode2 = QuestionUtils.mergeCode(userCode, testCaseList).replace("Solution","Solution2");

        CompletableFuture<String> solution1 = asyncService.executeCompileAndRun(finalCode1, dethPath, "Solution1");
        CompletableFuture<String> solution2 = asyncService.executeCompileAndRun(finalCode2, dethPath, "Solution2");

        // 这个get是阻塞主线程的！
        System.out.println(solution1.get());
        System.out.println(solution2.get());
    }


    @Test
    void test6() {
        Runtime r = Runtime.getRuntime();
        System.out.println("gc之前jvm空闲容量=" + r.freeMemory());
        r.gc();
        // 虚拟机空闲内存容量
        long startMem = r.freeMemory();
        System.out.println("程序开始时jvm空闲容量=" + startMem);
        StringBuilder sb = new StringBuilder();
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            result += i;
            sb.append(i);
        }
        System.out.println(result);
//        System.out.println(sb);
        long endMem = r.freeMemory();
        System.out.println("程序结束时jvm空闲容量=" + endMem);
        System.out.println(startMem - endMem);
    }
}
