package com.leetcode.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leetcode.model.constant.TokenConstant;
import com.leetcode.util.question.QuestionInfo;
import com.leetcode.util.redis.RedisUtil;
import com.leetcode.util.result.Result;
import com.leetcode.util.token.TokenUtil;
import com.leetcode.web.entity.User;
import com.leetcode.web.entity.dto.QuestionData;
import com.leetcode.web.service.impl.QuestionServiceImpl;
import com.leetcode.web.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leetcode.util.question.QuestionUtils;
import com.leetcode.web.entity.Commit;
import com.leetcode.web.entity.Question;
import com.leetcode.web.mapper.CommitMapper;
import com.leetcode.web.mapper.QuestionMapper;
import com.leetcode.web.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2021/2/1 10:42
 */
@RestController
@RequestMapping(value = "/question")
@ResponseBody
@Slf4j
public class QuestionController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private CommitMapper commitMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AsyncService asyncService;

    /**
     * 获取用户做题信息
     * xxj
     *
     * @param request
     * @return
     */
    @GetMapping("/user/answer")
    @ResponseBody
    public Result answerCondition(HttpServletRequest request) {
//        从token中获取用户ID和用户权限
        String token = request.getHeader("token");
        Map<String, String> map = tokenUtil.parseToken(token);
        Long userid = Long.valueOf(map.get(TokenConstant.USER_ID_CLAIN));
        String role = map.get(TokenConstant.USER_ROLE_CLAIN);


        //判断用户权限
        if ("ROLE_USER".equals(role)) {
            //普通用户
            //判断redis缓存中是否已经有数据
            Object userAnswerCondition = redisUtil.get("userid:" + userid + ":userAnswerCondition");
            if (userAnswerCondition != null) {

                return Result.ok((HashMap<String, Integer>) userAnswerCondition);
            } else {
                //缓存中不存在数据，到数据库中去查找
                User answerCondition = userService.getAnswerCondition(userid);
                Integer easy = answerCondition.getNumSimp();
                Integer mid = answerCondition.getNumMid();
                Integer diff = answerCondition.getNumDiff();
                Integer solved = easy + mid + diff;

                HashMap<String, Integer> newAnswerCondition = new HashMap<>();
                newAnswerCondition.put("solved", solved);
                newAnswerCondition.put("easy", easy);
                newAnswerCondition.put("mid", mid);
                newAnswerCondition.put("diff", diff);

                //将数据放在redis中缓存一段时间
                redisUtil.set("userid:" + userid + ":userAnswerCondition", newAnswerCondition, 6 * 60 * 60);
                return Result.ok(newAnswerCondition);
            }
        } else if ("ROLE_ADMIN".equals(role)) {
            //管理员
            return null;
        } else
            return Result.unauthorized("用户角色无法识别！");
    }


    /**
     * 获取题目列表
     * xxj
     *
     * @param list       题目所属列表
     * @param difficulty 题目难度
     * @param status     题目状态
     * @param pagenum    每页题目数量
     * @param page       第几页
     * @param tag        标签
     * @param keyword    关键字
     * @return
     */
    @GetMapping("/question/all")
    @ResponseBody
    public Result allQuestion(HttpServletRequest request,
                              String list,
                              String difficulty,
                              String status,
                              Integer pagenum,
                              Integer page,
                              String tag,
                              String keyword) {

        //由于前端传输的是String类型的信息，数据库中是int类型这里作出转换
        Integer difficultyCon = difficultyJudge(difficulty);
        //判断pagenum和page是否合理
        if (page == null || page <= 0) page = 1;
        if (pagenum == null || pagenum <= 0) pagenum = 50;
        Page<QuestionData> pageObj = new Page<>(page, pagenum);

        //判断用户是否筛选做题情况（已做，未做，尝试过）
        if (status == null || "".equals(status)) {
            //没有传入做题情况,
            IPage<QuestionData> questionDataIPageWithoutStatus = questionService.selectPageWithoutStatus(pageObj, list, difficultyCon, tag, keyword);
            return Result.ok(questionDataIPageWithoutStatus);
        } else {
            //传入做题情况

//            从token中获取用户ID和用户权限
            String token = request.getHeader("token");
            Map<String, String> map = tokenUtil.parseToken(token);
            Long userid = Long.valueOf(map.get(TokenConstant.USER_ID_CLAIN));
            String role = map.get(TokenConstant.USER_ROLE_CLAIN);
//            long userid = 1;
//            String role = "ROLE_USER";


            //判断用户权限
            if ("ROLE_USER".equals(role)) {
                //普通用户

                //判断用户要筛选的做题情况
                if ("answered".equals(status)) {
                    //筛选已解答
                    IPage<QuestionData> questionDataIPageWithAnswered = questionService.selectPageWithAnswered(pageObj, list, difficultyCon, status, tag, keyword, userid);
                    return Result.ok(questionDataIPageWithAnswered);
                } else if ("tried".equals(status)) {
                    //筛选已尝试
                    System.out.println("筛选尝试");
                    IPage<QuestionData> questionDataIPageWithTried = questionService.selectPageWithTried(pageObj, list, difficultyCon, status, tag, keyword, userid);

                    return Result.ok(questionDataIPageWithTried);
                } else if ("not-done".equals(status)) {
                    //筛选未作答
                    System.out.println("筛选未作答");
                    IPage<QuestionData> questionDataIPageWithUndo = questionService.selectPageWithUndo(pageObj, list, difficultyCon, status, tag, keyword, userid);
                    return Result.ok(questionDataIPageWithUndo);
                } else {
                    return Result.badRequest("请求的状态错误");
                }

            } else if ("ROLE_ADMIN".equals(role)) {
                //管理员
                return null;
            } else {
                return Result.unauthorized("用户角色错误！");
            }


        }

    }

    /**
     * 难度转换
     * xxj
     *
     * @param diff
     * @return
     */
    private Integer difficultyJudge(String diff) {
        if ("easy".equals(diff))
            return 0;
        else if ("mid".equals(diff))
            return 1;
        else if ("diff".equals(diff))
            return 2;
        else
            return null;
    }


    /**
     * 提交代码
     *
     * @param requestMap 用于接收参数
     * @param token      token
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @author liwenhao
     */
    @PostMapping(value = "/commit")
    public Result commitCode(@RequestBody Map<String, String> requestMap,
                             @RequestHeader("token") String token) throws ExecutionException, InterruptedException {
        // 用来返回数据
        Map<String, String> dataMap = new HashMap<>();
        // 生成的.java文件和.class文件的存储位置（也就是在哪个目录下编译运行）
        String destPath = "D:\\DeskTop\\tmp\\";

        // 获取questionId，去数据库中找标准的测试用例
        String questionId = requestMap.get("questionId");
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.select("commit_test_case").eq("question_id", questionId);
        Question question = questionMapper.selectOne(questionQueryWrapper);
        String testCaseAndAnswer = question.getCommitTestCase();
        /*
            [2,7,11,15]\n9@ANSWER@[0, 1]
            由于commit_test_case这个字段中，要包含两个信息，一个是标准测试用例，另一个是这个用例对应的标准答案
            因此插入题目的时候就以“@ANSWER@”分割测试用例和答案，类似于上面那样
            测试用例如果有多个，就以“\n”，也就是换行符来分割
            （这些只是暂定，如果要改后期也可以随时调整）
         */
        // 首先将拿到的信息分割为测试用例和答案两部分
        String[] split = testCaseAndAnswer.split("@ANSWER@");
        // 分别拿到测试用例和答案
        String testCase = split[0];
        String answer = split[1];
        // 获得用户提交的代码
        String userCode = requestMap.get("code");
        // 获得测试用例的集合
        List<String> testCaseList;
        // 判断这个题目的方法有几个参数
        if (QuestionUtils.multiParams(userCode)) {
            // 如果有多个参数，按照"\n"为标志分割
            testCaseList = Arrays.asList(testCase.split("\n"));
        } else {
            // 如果只有一个参数
            testCaseList = Collections.singletonList(testCase);
        }
        // 合并代码
        String s = QuestionUtils.mergeCode(userCode, testCaseList);
        // 修改类名（文件名）
        String filename = "Solution_" + questionId + "_" + System.currentTimeMillis();
        String finalCode = s.replace("Solution", filename);
        CompletableFuture<String> future = asyncService.executeCompileAndRun(finalCode, destPath, filename);
        String output = future.get();
        // 将输出划分，第一个是用户代码的输出结果，第二个是内存消耗（字节），第三个是时间消耗（毫秒）
        String[] outputArr = output.split("\n");
        String runResult = outputArr[0];
        String memory = outputArr[1];
        String runtime = outputArr[2];

        Map<String, String> tokenMap = tokenUtil.parseToken(token);
        String userId = tokenMap.get(TokenConstant.USER_ID_CLAIN);

        Commit commit = new Commit();
        commit.setUserId(Long.parseLong(userId));
        commit.setQuestionId(Long.parseLong(questionId));
        commit.setCommitCode(userCode);

        // 要返回给前端的结果
        String result;
        if (runResult.equals(answer)) {
            // 如果执行的结果和标准答案一样，再进行接下来的操作
            commit.setRuntime(Integer.parseInt(runtime));
            commit.setMemory(Integer.parseInt(memory));
            dataMap.put("memory", memory);
            dataMap.put("runtime", runtime);

            result = "pass";
            // 查出提交记录总数
            QueryWrapper<Commit> commitQueryWrapper = new QueryWrapper<>();
            commitQueryWrapper.eq("question_id", questionId);
            Integer recordCount = commitMapper.selectCount(commitQueryWrapper);
            // 分别查找出内存消耗和执行时间多余此次结果的用户
            commitQueryWrapper.clear();
            commitQueryWrapper.gt("memory", memory);
            Integer memoryCount = commitMapper.selectCount(commitQueryWrapper);


            commitQueryWrapper.clear();
            commitQueryWrapper.gt("runtime", runtime);
            Integer runTimeCount = commitMapper.selectCount(commitQueryWrapper);

            String memoryBeat = String.format("%.2f", ((double) memoryCount / recordCount) * 100);
            String runtimeBeat = String.format("%.2f", ((double) runTimeCount / recordCount) * 100);
            dataMap.put("runtimeBeat", runtimeBeat);
            dataMap.put("memoryBeat", memoryBeat);
        } else {
            // 如果答案错误 判断runResult是编译出错还是还是解答错误 错误情况下击败多少的百分比不合实际，不处理
            if (runResult.contains("编译出错_")) {
                result = "编译出错";
            } else {
                // 如果和标准答案不一样，编译还正常的话，那就归为解答错误（无论是抛运行时异常还是算法错了）
                result = "解答错误";
            }
        }
        commit.setCommitResult(result);
        commit.setCommitTime(new Date());
        // 将数据插入到表中去
        commitMapper.insert(commit);

        dataMap.put("result",result);
        return Result.ok(dataMap);
    }

    /**
     * 执行代码（管理员未做）
     *
     * @param requestMap 用于接收参数
     * @param token      token
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @author liwenhao
     */
    @PostMapping(value = "/run")
    public Result runCode(@RequestBody Map<String, String> requestMap,
                          @RequestHeader("token") String token) throws ExecutionException, InterruptedException {
        // 用来返回数据
        Map<String, String> dataMap = new HashMap<>();
        // 生成的.java文件和.class文件的存储位置（也就是在哪个目录下编译运行）
        String destPath = "D:\\DeskTop\\tmp\\";

        String questionId = requestMap.get("questionId");
        String userCode = requestMap.get("code");
        String testCase = requestMap.get("testCase");

        List<String> testCaseList;
        if (QuestionUtils.multiParams(userCode)) {
            // 如果有多个参数
            // 现在先假设测试用例的多个参数之间使用“\n”符号分割（这个后期有待商议）
            // 获取测试用例的集合
            testCaseList = Arrays.asList(testCase.split("\n"));
        } else {
            // 只有一个参数，直接放到集合中
            testCaseList = Collections.singletonList(testCase);
        }

        // 合并生成可以编译执行的代码
        String uCode = QuestionUtils.mergeCode(userCode, testCaseList);
        Map<String, String> tokenMap = tokenUtil.parseToken(token);
        String userId = tokenMap.get(TokenConstant.USER_ID_CLAIN);
        // 用户执行代码文件名称保存为：Solution_用户id_当前毫秒数（不带后缀）。 例如：Solution_141324_1612927764160
        String uFilename = "Solution_" + userId + "_" + System.currentTimeMillis();
        String userFinalCode = uCode.replace("Solution", uFilename);
        log.info("用户可执行代码={}", userFinalCode);
        // 从数据库中查出标准的正确代码，和前段传来的测试用例跑一遍
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.select("correct_code").eq("question_id", questionId);
        Question question = questionMapper.selectOne(questionQueryWrapper);
        String correctCode = question.getCorrectCode();
        String aCode = QuestionUtils.mergeCode(correctCode, testCaseList);
        String aFilename = "Solution" + "_" + userId + "_" + System.currentTimeMillis() + "_answer";
        String answerFinalCode = aCode.replace("Solution", aFilename);
        log.info("标准代码={}", answerFinalCode);
        // 分别去跑这两份代码，得到输出结果
        CompletableFuture<String> userFuture = asyncService.executeCompileAndRun(userFinalCode, destPath, uFilename);
        CompletableFuture<String> answerFuture = asyncService.executeCompileAndRun(answerFinalCode, destPath, aFilename);
        // 这个方法会阻塞主线程，一定可以拿到结果，但是有可能出现用户提交恶意代码的情况，暂时先放下，先做功能。
        String userResult = userFuture.get();
        String answerResult = answerFuture.get();
        // 一共会有三个输出，按顺序分别是程序执行结果、内存消耗（字节）和时间消耗（毫秒）
        String[] userArr = userResult.split("\n");
        String[] answerArr = answerResult.split("\n");

        dataMap.put("input", testCase);
        dataMap.put("output", userArr[0]);
        dataMap.put("exceptResult", answerArr[0]);
        if (userArr[0].equals(answerArr[0])) {
            dataMap.put("state", "已完成");
        } else {
            dataMap.put("state", "error");
        }

        return Result.ok(dataMap);
    }

    /**
     * 题目随机开始以及开始（管理员未做）
     *
     * @param questionId 题目id（随机开始的为random）
     * @param token      token
     * @return
     * @author liwenhao
     */
    @GetMapping(value = "/start")
    public Result startQuestion(@RequestParam String questionId,
                                @RequestHeader("token") String token) {
        Question question;
        if ("random".equals(questionId)) {
            // 随机开始的情况
            Random random = new Random();
            int i = random.nextInt(questionMapper.selectCount(null));
            question = questionMapper.randomSelect(i);
        } else {
            // 题目id确定的情况
            Question redisQuestion = (Question) redisUtil.get("question");
            if (redisQuestion != null) {
                log.info("通过缓存取数据");
                // 如果缓存中有相应的数据
                question = redisQuestion;
                long time = redisUtil.getExpire("question");
                log.info("question redis过期时间={}", time);
            } else {
                log.info("通过数据库取数据");
                question = questionMapper.selectById(questionId);
                if (question == null) {
                    return Result.badRequest("请求参数错误");
                }
                // 存入缓存 有效期1min（其实这方面我也不知道设置多少合适，主要是防止用户重复请求题目信息，1min感觉足够了）
                redisUtil.set("question", question, 60);
            }
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("questionNum", question.getQuestionNum());
        dataMap.put("title", question.getTitle());
        dataMap.put("answerNum", question.getAnswerNum());
        // 通过次数（正确提交的总次数）
        Integer passNum = question.getPassNum();
        log.info("通过次数={}", passNum);
        /*
            这里passNum和submit的num如果到时候表中数据初始值默认为0，就不需要判断为null了
        */
        if (passNum == null) {
            passNum = 0;
        }
        log.info("通过次数={}", passNum);
        // 提交次数（无论正确）
        Integer submitNum = question.getSubmitNum();
        log.info("提交次数={}", submitNum);
        if (submitNum == null) {
            submitNum = 0;
        }
        log.info("提交次数={}", submitNum);
        // 通过率
        String passRateStr;
        if (submitNum != 0) {
            double passrate = (double) passNum / submitNum;
            passRateStr = String.format("%.1f", passrate * 100) + "%";
        } else {
            passRateStr = "暂无";
        }
        dataMap.put("passRate", passRateStr);
        dataMap.put("difficulty", question.getDifficulty());
        dataMap.put("content", question.getContent());
        dataMap.put("createTime", question.getCreateTime());
        dataMap.put("commentNum", question.getCommentNum());
        dataMap.put("updateTime", question.getUpdateTime());
        dataMap.put("thumbup", question.getThumbup());
        dataMap.put("answerNum", passNum);
        dataMap.put("commitNum", submitNum);
        /*
            commit_body 提交的代码
            两种情况：没做过——原始代码，做过——用户提交过的代码，并且还要最新的那一份
         */
        Map<String, String> tokenMap = tokenUtil.parseToken(token);
        String userId = tokenMap.get(TokenConstant.USER_ID_CLAIN);
        log.info("userId={}", userId);
        // 获取此用户此题目最后一次提交的代码
        String lastCommitCode = questionMapper.getLastCommitCode(Long.parseLong(userId), Long.parseLong(questionId));
        String commitBody;
        /*
            题目状态，表示题目有没有被做过 暂时以0代表未做，1代表做过（接口里没提怎么规定）。
         */
        int state;
        if (lastCommitCode == null) {
            commitBody = question.getInitialCode();
            state = 0;
        } else {
            commitBody = lastCommitCode;
            state = 1;
        }
        dataMap.put("commitBody", commitBody);
        dataMap.put("state", state);

        return Result.ok(dataMap);
    }

}
