package com.leetcode.web.service.impl;

import com.leetcode.web.entity.Commit;
import com.leetcode.web.entity.dto.CommitRecord;
import com.leetcode.web.entity.dto.UserActivityData;
import com.leetcode.web.mapper.CommitMapper;
import com.leetcode.web.service.ICommitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jarvan
 * @since 2021-01-30
 */
@Service
public class CommitServiceImpl extends ServiceImpl<CommitMapper, Commit> implements ICommitService {
    @Autowired
    private CommitMapper commitMapper;

    @Override
    public List<CommitRecord> getUserAllCommit(Long userid) {
        return commitMapper.getUserAllCommit(userid);
    }

    @Override
    public List<UserActivityData> getUserActivity(long userid) {
        return commitMapper.getUserActivity(userid);
    }
}
