package com.fulltext.project.service.impl;

import com.fulltext.project.dao.TaskFormMapper;
import com.fulltext.project.entity.TaskForm;
import com.fulltext.project.service.TaskFormService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/16.
 */
@Service
@Transactional
public class TaskFormServiceImpl implements TaskFormService {
    @Resource
    private TaskFormMapper taskFormMapper;

    @Override
    public TaskForm selectTaskFormByFormNo(String formNo) {
        return taskFormMapper.selectTaskFormByFormNo(formNo);
    }
}
