package com.leetcode.web.service.impl;

import com.leetcode.web.entity.List;
import com.leetcode.web.mapper.ListMapper;
import com.leetcode.web.service.IListService;
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
public class ListServiceImpl extends ServiceImpl<ListMapper, List> implements IListService {

}
