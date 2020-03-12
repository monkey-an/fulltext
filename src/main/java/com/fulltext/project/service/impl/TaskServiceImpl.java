package com.fulltext.project.service.impl;

import com.fulltext.project.dao.TaskMapper;
import com.fulltext.project.entity.Task;
import com.fulltext.project.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;

    @Override
    public Task selectTaskById(Long id) {
        return null;
    }

    @Override
    public List<Task> selectTaskListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(Task entity) {
        return 0;
    }

    @Override
    public int update(Task entity) {
        return 0;
    }
}
