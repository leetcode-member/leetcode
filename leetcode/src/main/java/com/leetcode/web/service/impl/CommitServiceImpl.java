package com.leetcode.web.service.impl;

import com.leetcode.web.entity.Commit;
import com.leetcode.web.mapper.CommitMapper;
import com.leetcode.web.service.ICommitService;
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
public class CommitServiceImpl extends ServiceImpl<CommitMapper, Commit> implements ICommitService {

}
