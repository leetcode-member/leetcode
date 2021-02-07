package com.leetcode.web.service;

import com.leetcode.web.entity.Commit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leetcode.web.entity.dto.CommitRecord;
import com.leetcode.web.entity.dto.UserActivityData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
public interface ICommitService extends IService<Commit> {

    List<CommitRecord> getUserAllCommit(Long userid);

    List<UserActivityData> getUserActivity(long userid);
}
