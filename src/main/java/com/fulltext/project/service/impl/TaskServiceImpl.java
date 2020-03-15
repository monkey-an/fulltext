package com.fulltext.project.service.impl;

import com.fulltext.project.dao.TaskMapper;
import com.fulltext.project.entity.Task;
import com.fulltext.project.entity.User;
import com.fulltext.project.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/14.
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
        return taskMapper.insert(entity);
    }

    @Override
    public int update(Task entity) {
        return taskMapper.updateByPrimaryKey(entity);
    }

    @Override
    public Task selectTaskByTaskId(Long taskId) {
        return taskMapper.selectTaskByTaskId(taskId);
    }

    @Override
    public List<Task> selectByCommitUserId(Long userId) {
        return taskMapper.selectByCommitUserId(userId);
    }

    @Override
    public List<Task> selectByCurrentUserId(Long userId) {
        return taskMapper.selectByCurrentUserId(userId);
    }

    @Override
    public String initTaskDetail(User user, Long taskId, Model model) {
        //读取当前task详情，DOING状态
        Task task = selectTaskByTaskId(taskId);
        if(!"DOING".equals(task.getCurrentApprovalStatus())){
            return "ERROR";
        }

        //判断当前登录用户是不是审核人
        //如果不是，返回error，无权查看
        if(user.getId().equals(Long.parseLong(task.getCurrentApprovalUserId()))){

        }

        //task装进model
        //取出taskDetail，装进model
        //取出附件下载链接
        //装进model
        //取出表单
        //在第一个表单上生成审批意见
        //装进model
        //返回success

        return "SUCCESS";
    }

    @Override
    public String initMyTaskDetail(User user, Long taskId, Model model) {
        //读取当前task详情，DOING状态
        //判断当前登录用户是不是提交人
        //如果不是，返回error，无权查看
        //task装进model
        //取出taskDetail，装进model
        //取出附件下载链接
        //装进model
        //取出第一个表单
        //装进model
        //返回success

        return "SUCCESS";
    }
}
