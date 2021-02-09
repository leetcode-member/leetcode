package com.leetcode.web.mapper;

import com.leetcode.web.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
     List<Tag> selectTags();
}
