package com.leetcode.web.mapper;

import com.leetcode.web.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}
