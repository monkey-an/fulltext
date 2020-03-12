package com.fulltext.project.service.impl;

import com.fulltext.project.dao.WorkGroupMapper;
import com.fulltext.project.entity.WorkGroup;
import com.fulltext.project.service.WorkGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class WorkGroupServiceImpl implements WorkGroupService {
    @Resource
    private WorkGroupMapper workGroupMapper;

    @Override
    public WorkGroup selectWorkGroupById(Long id) {
        return null;
    }

    @Override
    public List<WorkGroup> selectWorkGroupListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(WorkGroup entity) {
        return 0;
    }

    @Override
    public int update(WorkGroup entity) {
        return 0;
    }
}
