package com.fulltext.project.service.impl;

import com.fulltext.project.dao.TaskFormHtmlMapper;
import com.fulltext.project.entity.TaskFormHtml;
import com.fulltext.project.service.TaskFormHtmlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/14.
 */
@Service
@Transactional
public class TaskFormHtmlServiceImpl implements TaskFormHtmlService {
    @Resource
    private TaskFormHtmlMapper taskFormHtmlMapper;

    @Override
    public TaskFormHtml selectTaskFormHtmlById(Long id) {
        return taskFormHtmlMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(TaskFormHtml entity) {
        return taskFormHtmlMapper.insert(entity);
    }

    @Override
    public int update(TaskFormHtml entity) {
        return taskFormHtmlMapper.updateByPrimaryKey(entity);
    }

    @Override
    public TaskFormHtml selectTaskFormHtmlByTaskIdAndFormNo(Long taskId, String formNo) {
        return taskFormHtmlMapper.selectByTaskIdAndFormNo(taskId,formNo);
    }
}
