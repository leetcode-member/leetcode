package com.leetcode.web.mapper;

import com.leetcode.web.entity.Commit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leetcode.web.entity.dto.CommitRecord;
import com.leetcode.web.entity.dto.UserActivityData;
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
public interface CommitMapper extends BaseMapper<Commit> {

    List<CommitRecord> getUserAllCommit(Long userid);

    List<UserActivityData> getUserActivity(long userid);
}
