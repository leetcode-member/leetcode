package com.leetcode.web.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leetcode.web.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leetcode.web.entity.dto.QuestionData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    IPage<QuestionData> selectPageWithAnswered(Page<QuestionData> page,
                                   @Param("list") String list,
                                   @Param("difficulty") Integer difficulty,
                                   @Param("status") String status,
                                   @Param("tag") String tag,
                                   @Param("keyword") String keyword,
                                   @Param("userid")Long userid);

    IPage<QuestionData> selectPageWithoutStatus(Page<QuestionData> page,
                                                @Param("list") String list,
                                                @Param("difficulty") Integer difficulty,
                                                @Param("tag") String tag,
                                                @Param("keyword") String keyword);

    IPage<QuestionData> selectPageWithUndo(Page<QuestionData> page,
                                           @Param("list") String list,
                                           @Param("difficulty") Integer difficulty,
                                           @Param("status") String status,
                                           @Param("tag") String tag,
                                           @Param("keyword") String keyword,
                                           @Param("userid") Long userid);

    IPage<QuestionData> selectPageWithTried(Page<QuestionData> page,
                                            @Param("list") String list,
                                            @Param("difficulty") Integer difficulty,
                                            @Param("status") String status,
                                            @Param("tag") String tag,
                                            @Param("keyword") String keyword,
                                            @Param("userid") Long userid);
}
