package com.fulltext.project.service.impl.workflow;

import com.fulltext.project.dao.TaskDetailMapper;
import com.fulltext.project.entity.TaskDetail;
import com.fulltext.project.service.TaskDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/14.
 */
@Service
@Transactional
public class TaskDetailServiceImpl implements TaskDetailService {
    @Resource
    private TaskDetailMapper taskDetailMapper;

    @Override
    public TaskDetail selectTaskDetailById(Long id) {
        return null;
    }

    @Override
    public List<TaskDetail> selectTaskDetailListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(TaskDetail entity) {
        return taskDetailMapper.insert(entity);
    }

    @Override
    public int update(TaskDetail entity) {
        return 0;
    }

    @Override
    public List<TaskDetail> selectTaskDetailByTaskId(Long taskId) {
        return taskDetailMapper.selectTaskDetailByTaskId(taskId);
    }
}
