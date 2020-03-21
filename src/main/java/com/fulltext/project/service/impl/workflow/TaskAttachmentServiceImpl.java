package com.fulltext.project.service.impl.workflow;

import com.fulltext.project.dao.TaskAttachmentMapper;
import com.fulltext.project.entity.TaskAttachment;
import com.fulltext.project.service.TaskAttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/14.
 */
@Service
@Transactional
public class TaskAttachmentServiceImpl implements TaskAttachmentService {
    @Resource
    private TaskAttachmentMapper taskAttachmentMapper;

    @Override
    public TaskAttachment selectTaskAttachmentById(Long id) {
        return null;
    }

    @Override
    public List<TaskAttachment> selectTaskAttachmentListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(TaskAttachment entity) {
        return taskAttachmentMapper.insert(entity);
    }

    @Override
    public int update(TaskAttachment entity) {
        return 0;
    }

    @Override
    public List<TaskAttachment> selectTaskAttachmentListByTaskId(Long taskId) {
        return taskAttachmentMapper.selectTaskAttachmentListByTaskId(taskId);
    }
}
