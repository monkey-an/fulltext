package com.fulltext.project.service.impl.workflow;

import com.fulltext.project.dao.TaskFormHtmlMapper;
import com.fulltext.project.entity.Task;
import com.fulltext.project.entity.TaskFormHtml;
import com.fulltext.project.entity.User;
import com.fulltext.project.service.TaskFormHtmlService;
import com.fulltext.project.service.TaskService;
import com.fulltext.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2020/03/14.
 */
@Service
@Transactional
public class TaskFormHtmlServiceImpl implements TaskFormHtmlService {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

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

    @Override
    public List<TaskFormHtml> selectTaskFormHtmlByTaskId(Long taskId) {
        return taskFormHtmlMapper.selectTaskFormHtmlByTaskId(taskId);
    }

    @Override
    public PageInfo<TaskFormHtml> selectAllUserByPaging(int pageNo, int pageSize, String taskId) {
        PageInfo<TaskFormHtml> info;
        if(StringUtils.isNotEmpty(taskId)){
            List<Long> taskIdList = Arrays.asList(Long.parseLong(taskId));
            PageHelper.startPage(pageNo, pageSize);
            List<TaskFormHtml> taskFormHtmlList = taskFormHtmlMapper.selectByTaskIdList(taskIdList);
            info = new PageInfo<>(taskFormHtmlList);
        }else{
            List<Long> taskIdList = taskService.selectAllTaskId();
            PageHelper.startPage(pageNo, pageSize);
            List<TaskFormHtml> taskFormHtmlList = taskFormHtmlMapper.selectByTaskIdList(taskIdList);
            info = new PageInfo<>(taskFormHtmlList);
        }

        return info;
    }
}
