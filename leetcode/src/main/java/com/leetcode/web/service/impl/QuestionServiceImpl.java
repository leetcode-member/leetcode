package com.leetcode.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leetcode.web.entity.Question;
import com.leetcode.web.entity.dto.QuestionData;
import com.leetcode.web.mapper.QuestionMapper;
import com.leetcode.web.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public IPage<QuestionData> selectPageWithoutStatus(Page<QuestionData> page, String list, Integer difficulty, String tag, String keyword) {
        return formatPassRate(questionMapper.selectPageWithoutStatus(page,list,difficulty,tag,keyword));
    }

    @Override
    public IPage<QuestionData> selectPageWithTried(Page<QuestionData> page, String list, Integer difficulty, String status, String tag, String keyword, Long userid) {
        return formatPassRate(questionMapper.selectPageWithTried(page,list,difficulty,status,tag,keyword,userid));
    }

    @Override
    public IPage<QuestionData> selectPageWithUndo(Page<QuestionData> page, String list, Integer difficulty, String status, String tag, String keyword, Long userid) {
        return formatPassRate(questionMapper.selectPageWithUndo(page,list,difficulty,status,tag,keyword,userid));
    }

    @Override
    public IPage<QuestionData> selectPageWithAnswered(Page<QuestionData> page,String list,Integer difficulty,String status,String tag,String keyword,Long userid) {
        return formatPassRate(questionMapper.selectPageWithAnswered(page,list,difficulty,status,tag,keyword,userid));
    }

    private IPage<QuestionData> formatPassRate(IPage<QuestionData> questionDatas) {
        for (QuestionData record : questionDatas.getRecords()) {
            String passRate = record.getPassRate().substring(0, record.getPassRate().length() - 2);
            record.setPassRate(String.format("%.1f",Double.valueOf(passRate)));
        }
        return questionDatas;
    }
}
