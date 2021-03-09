package com.leetcode.web.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leetcode.web.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leetcode.web.entity.dto.QuestionData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@Mapper
@Repository
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

    /**
     * 查询第 row+1 行记录
     * @author liwenhao
     * @param row 行数
     * @return 返回题目信息
     */
    Question randomSelect(Integer row);

    String getLastCommitCode(@Param("userId") Long userId, @Param("questionId")  Long questionId);

}
