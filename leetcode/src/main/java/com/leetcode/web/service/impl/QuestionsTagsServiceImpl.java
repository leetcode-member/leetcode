package com.leetcode.web.service.impl;

import com.leetcode.web.entity.QuestionsTags;
import com.leetcode.web.mapper.QuestionsTagsMapper;
import com.leetcode.web.service.IQuestionsTagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class QuestionsTagsServiceImpl extends ServiceImpl<QuestionsTagsMapper, QuestionsTags> implements IQuestionsTagsService {

}
