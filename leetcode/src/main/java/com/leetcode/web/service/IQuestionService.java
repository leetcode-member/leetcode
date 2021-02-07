package com.leetcode.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
}
