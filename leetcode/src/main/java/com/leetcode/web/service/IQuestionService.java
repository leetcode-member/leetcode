package com.leetcode.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leetcode.model.dto.CommitRequestDto;
import com.leetcode.model.dto.CommitResponseDto;
import com.leetcode.model.dto.RunCodeRequestDTO;
import com.leetcode.model.dto.RunCodeResponseDTO;
import com.leetcode.util.executor.CompileResult;
import com.leetcode.web.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leetcode.web.entity.dto.QuestionData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
public interface IQuestionService extends IService<Question> {
    IPage<QuestionData> selectPageWithAnswered(Page<QuestionData> page,String list,Integer difficulty,String status,String tag,String keyword,Long userid);

    IPage<QuestionData> selectPageWithoutStatus(Page<QuestionData> page, String list, Integer difficulty, String tag, String keyword);

    IPage<QuestionData> selectPageWithUndo(Page<QuestionData> page, String list, Integer difficultyCon, String status, String tag, String keyword, Long userid);

    IPage<QuestionData> selectPageWithTried(Page<QuestionData> page, String list, Integer difficultyCon, String status, String tag, String keyword, Long userid);

    /**
     * 执行代码（执行代码的接口用）
     * @param runCodeRequestDTO 执行代码请求dto对象
     * @return
     */
    RunCodeResponseDTO runCode(RunCodeRequestDTO runCodeRequestDTO);

    /**
     * 编译代码
     * @param code 可编译的代码
     * @return
     */
    CompileResult compileCode(String code);

    /**
     * 提交代码
     * @param commitRequestDto
     * @return
     */
    CommitResponseDto commitCode(CommitRequestDto commitRequestDto, String token);
}
