package com.leetcode.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leetcode.model.constant.TokenConstant;
import com.leetcode.model.dto.CommitRequestDto;
import com.leetcode.model.dto.CommitResponseDto;
import com.leetcode.model.dto.RunCodeRequestDTO;
import com.leetcode.model.dto.RunCodeResponseDTO;
import com.leetcode.util.executor.CompileResult;
import com.leetcode.util.executor.JavaClassExecuter;
import com.leetcode.util.executor.MyCompiler;
import com.leetcode.util.executor.ReturnInfo;
import com.leetcode.util.token.TokenUtil;
import com.leetcode.web.entity.Commit;
import com.leetcode.web.entity.Question;
import com.leetcode.web.entity.dto.QuestionData;
import com.leetcode.web.mapper.CommitMapper;
import com.leetcode.web.mapper.QuestionMapper;
import com.leetcode.web.service.AsyncService;
import com.leetcode.web.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@Service
@Slf4j
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private CommitMapper commitMapper;

    @Override
    public IPage<QuestionData> selectPageWithoutStatus(Page<QuestionData> page, String list, Integer difficulty, String tag, String keyword) {
        return formatPassRate(questionMapper.selectPageWithoutStatus(page, list, difficulty, tag, keyword));
    }

    @Override
    public IPage<QuestionData> selectPageWithTried(Page<QuestionData> page, String list, Integer difficulty, String status, String tag, String keyword, Long userid) {
        return formatPassRate(questionMapper.selectPageWithTried(page, list, difficulty, status, tag, keyword, userid));
    }

    @Override
    public IPage<QuestionData> selectPageWithUndo(Page<QuestionData> page, String list, Integer difficulty, String status, String tag, String keyword, Long userid) {
        return formatPassRate(questionMapper.selectPageWithUndo(page, list, difficulty, status, tag, keyword, userid));
    }

    @Override
    public IPage<QuestionData> selectPageWithAnswered(Page<QuestionData> page, String list, Integer difficulty, String status, String tag, String keyword, Long userid) {
        return formatPassRate(questionMapper.selectPageWithAnswered(page, list, difficulty, status, tag, keyword, userid));
    }

    private IPage<QuestionData> formatPassRate(IPage<QuestionData> questionDatas) {
        for (QuestionData record : questionDatas.getRecords()) {
            String passRate = record.getPassRate().substring(0, record.getPassRate().length() - 2);
            record.setPassRate(String.format("%.1f", Double.valueOf(passRate)));
        }
        return questionDatas;
    }

    /**
     * 提交代码（接口用）
     *
     * @param commitRequestDto 提交代码请求dto对象
     * @param token
     * @return
     */
    @Override
    public CommitResponseDto commitCode(CommitRequestDto commitRequestDto, String token) {
        CommitResponseDto commitResponseDto;
        // 获取questionId，去数据库中查找标准的测试用例
        String questionId = commitRequestDto.getQuestionId();
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.select("commit_test_case", "commit_test_case_answer", "submit_num", "pass_num").eq("question_id", questionId);
        Question question = questionMapper.selectOne(questionQueryWrapper);
        // 为此题目的提交次数+1
        question.setSubmitNum(question.getSubmitNum() + 1);
        // 给用户提交的代码和正确的测试代码拼接上必要的头信息
        String userCode = "import java.util.*;\npublic " + commitRequestDto.getCode();
        // 编译用户代码
        CompileResult compileResult = compileCode(userCode);

        Map<String, String> tokenMap = tokenUtil.parseToken(token);
        String userId = tokenMap.get(TokenConstant.USER_ID_CLAIN);
        Commit commit = new Commit();
        commit.setUserId(userId.trim());
        commit.setQuestionId(questionId.trim());
        commit.setCommitCode(commitRequestDto.getCode());

        if (! compileResult.isCompileRight()) {
            // 如果编译出错
            commitResponseDto = new CommitResponseDto("编译出错", "N/A", "", "N/A", "");
            commit.setCommitResult("编译出错");
            commit.setRuntime(- 1);
            commit.setMemory(- 1);
        } else {
            /*
                如果编译通过，运行代码
                区分执行是否出错
             */
            // 获取用于评测的测试用例
            String commitTestCase = question.getCommitTestCase();
            String[] paramStrArr = commitTestCase.split("\r\n");
            log.info("注意注意paramStrArr={}", Arrays.toString(paramStrArr));
            System.out.println("注意=" + Arrays.toString(paramStrArr));
            ReturnInfo returnInfo = JavaClassExecuter.execute(compileResult.getClassbytes(), paramStrArr);
            if (! returnInfo.isRight()) {
                // 执行期间出现错误
                log.info("执行异常={}", returnInfo.getStdout());
                commitResponseDto = new CommitResponseDto("执行出错", "N/A", "", "N/A", "");
                commit.setCommitResult("执行出错");
                commit.setRuntime(- 1);
                commit.setMemory(- 1);
            } else {
                /*
                    如果执行正确，判断算法是否出错
                 */
                // 获取到用户代码得到的答案
                String userAnswer = returnInfo.getReturnStr();
                Integer runtime = Integer.parseInt(returnInfo.getRuntime());
                Integer memory = Integer.parseInt(returnInfo.getMemory());
                if (! userAnswer.equals(question.getCommitTestCaseAnswer())) {
                    // 用户答案和标准答案不同，说明算法出错
                    log.info("用户答案={},标准答案={}", userAnswer, question.getCommitTestCaseAnswer());
                    commitResponseDto = new CommitResponseDto("算法出错", "N/A", "", "N/A", "");
                    commit.setCommitResult("算法出错");
                    commit.setRuntime(runtime);
                    commit.setMemory(memory);
                } else {
                    // 通过的情况
                    commit.setCommitResult("通过");
                    commit.setRuntime(runtime);
                    commit.setMemory(memory);
                    // 查出此题目正确提交记录总数
                    QueryWrapper<Commit> commitQueryWrapper = new QueryWrapper<>();
                    Map<String, Object> map = new HashMap<>();
                    map.put("question_id", questionId);
                    map.put("commit_result", "通过");
                    commitQueryWrapper.allEq(map);
                    Integer recordCount = commitMapper.selectCount(commitQueryWrapper);
                    // 分别查找出 此题目**通过**的 且 内存消耗和执行时间多于此次结果的记录条数
                    commitQueryWrapper.gt("runtime", runtime);
                    Integer runtimeCount = commitMapper.selectCount(commitQueryWrapper);
                    commitQueryWrapper.clear();
                    commitQueryWrapper.allEq(map);
                    commitQueryWrapper.gt("memory", memory);
                    Integer memoryCount = commitMapper.selectCount(commitQueryWrapper);

                    String memoryBeat = String.format("%.2f", ((double) memoryCount / recordCount) * 100) + "%";
                    String runtimeBeat = String.format("%.2f", ((double) runtimeCount / recordCount) * 100) + "%";

                    commitResponseDto = new CommitResponseDto("通过", returnInfo.getRuntime(), runtimeBeat, returnInfo.getMemory(), memoryBeat);

                    // 通过次数+1
                    question.setPassNum(question.getPassNum() + 1);
                }
            }
        }
        // 向commit表中插入提交记录
        commit.setCommitTime(new Date());
        commitMapper.insert(commit);
        // 修改question表中此题目的相关数据（提交次数和通过次数）
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", questionId);
        questionMapper.update(question, queryWrapper);

        return commitResponseDto;

    }

    /**
     * 执行代码（接口用）
     *
     * @param runCodeRequestDTO 执行代码请求dto对象
     * @return
     * @author liwenhao
     */
    @Override
    public RunCodeResponseDTO runCode(RunCodeRequestDTO runCodeRequestDTO) {
        RunCodeResponseDTO runCodeResponseDTO = new RunCodeResponseDTO();
        // 给用户提交的代码和正确的测试代码拼接上必要的头信息
        String userCode = "import java.util.*;\npublic " + runCodeRequestDTO.getCode();
        // 得到用于测试的正确代码，并拼上必要的信息
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        String questionId = runCodeRequestDTO.getQuestionId();
        questionQueryWrapper.select("correct_code").eq("question_id", questionId);
        Question question = questionMapper.selectOne(questionQueryWrapper);
        String correctCode = "import java.util.*;\npublic " + question.getCorrectCode();

        // 获取测试用例
        String testCase = runCodeRequestDTO.getTestCase();
        // 将input传回给前端（测试用例吗？不知道是干什么的）
        runCodeResponseDTO.setInput(testCase);
        // 将测试用例的多个参数分隔开成String数组
        String[] paramStrArr = testCase.split("\n");

        // 编译用户代码
        CompileResult compileResult = compileCode(userCode);
        log.info("编译结果={}", compileResult.isCompileRight());
        if (! compileResult.isCompileRight()) {
            /*
                编译出错
                如果用户代码编译出错，就不用再去编译运行标准代码了
                编译出错只需要返回状态为“编译出错”，输出为编译出错的信息即可
             */
            runCodeResponseDTO.setState("编译出错");
            runCodeResponseDTO.setOutput(compileResult.getCompileInfo());
            runCodeResponseDTO.setExceptResult("编译出错没必要给预期答案了");
        } else {
            // 编译正常
            CompletableFuture<ReturnInfo> userFuture = asyncService.execute(compileResult.getClassbytes(), paramStrArr);
            CompletableFuture<ReturnInfo> correctFuture = asyncService.compileAndExecute(correctCode, paramStrArr);
            try {
                ReturnInfo userReturnInfo = userFuture.get();
                ReturnInfo correctReturnInfo = correctFuture.get();
                /*
                    还需要处理运行时异常
                 */
                if (! userReturnInfo.isRight()) {
                    /*
                        如果执行代码是出现了运行时异常，将状态暂时设置为“RuntimeException”
                        并将输出流中打印的错误信息返回
                        预期结果也是不需要给的（参照于leetcode）
                     */
                    runCodeResponseDTO.setState("执行出错");
                    runCodeResponseDTO.setOutput(userReturnInfo.getStdout());
                } else {
                    // 分别获取用户和正确代码到运行返回的结果
                    String userReturn = userReturnInfo.getReturnStr();
                    String correctReturn = correctReturnInfo.getReturnStr();
                    runCodeResponseDTO.setState("已完成");
                    runCodeResponseDTO.setOutput(userReturn);
                    runCodeResponseDTO.setExceptResult(correctReturn);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return runCodeResponseDTO;
    }

    /**
     * 编译代码
     *
     * @param code 可编译的代码
     * @return
     * @author liwenhao
     */
    @Override
    public CompileResult compileCode(String code) {
        CompileResult compileResult;
        log.info("要编译运行的代码={}", code);
        DiagnosticCollector<JavaFileObject> compileCollector = new DiagnosticCollector<>();

        // 执行编译
        byte[] classBytes = MyCompiler.compile(code, compileCollector);
        if (classBytes == null) {
            // 如果编译出错
            List<Diagnostic<? extends JavaFileObject>> compileError = compileCollector.getDiagnostics();
            StringBuilder compileErrorRes = new StringBuilder();
            for (Diagnostic diagnostic : compileError) {
                compileErrorRes.append("Compilation error at ");
                compileErrorRes.append(diagnostic.getLineNumber());
                compileErrorRes.append(".");
                compileErrorRes.append(System.lineSeparator());
            }
            log.info("编译报错信息={}", compileErrorRes.toString());
            compileResult = new CompileResult(false, compileErrorRes.toString());
        } else {
            // 如果编译正确
            compileResult = new CompileResult(true, classBytes);
        }
        return compileResult;
    }
}
