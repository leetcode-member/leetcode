package com.leetcode.web.service.impl;

import com.leetcode.web.entity.Answer;
import com.leetcode.web.mapper.AnswerMapper;
import com.leetcode.web.service.IAnswerService;
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
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {

}
