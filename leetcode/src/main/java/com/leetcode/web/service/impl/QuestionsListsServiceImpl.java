package com.leetcode.web.service.impl;

import com.leetcode.web.entity.QuestionsLists;
import com.leetcode.web.mapper.QuestionsListsMapper;
import com.leetcode.web.service.IQuestionsListsService;
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
public class QuestionsListsServiceImpl extends ServiceImpl<QuestionsListsMapper, QuestionsLists> implements IQuestionsListsService {

}
