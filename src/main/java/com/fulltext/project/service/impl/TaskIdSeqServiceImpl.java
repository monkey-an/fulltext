package com.fulltext.project.service.impl;

import com.fulltext.project.dao.TaskIdSeqMapper;
import com.fulltext.project.entity.TaskIdSeq;
import com.fulltext.project.service.TaskIdSeqService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class TaskIdSeqServiceImpl implements TaskIdSeqService {
    @Resource
    private TaskIdSeqMapper taskIdSeqMapper;

    @Override
    public TaskIdSeq selectTaskIdSeqById(Long id) {
        return null;
    }

    @Override
    public List<TaskIdSeq> selectTaskIdSeqListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(TaskIdSeq entity) {
        return 0;
    }

    @Override
    public int update(TaskIdSeq entity) {
        return 0;
    }
}