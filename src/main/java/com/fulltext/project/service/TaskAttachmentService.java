package com.fulltext.project.service;
import com.fulltext.project.entity.TaskAttachment;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/14.
 */
public interface TaskAttachmentService {
    TaskAttachment selectTaskAttachmentById(Long id);
    List<TaskAttachment> selectTaskAttachmentListByIdList(List<Long> idList);
    int insert(TaskAttachment entity);
    int update(TaskAttachment entity);

    List<TaskAttachment> selectTaskAttachmentListByTaskId(Long taskId);
}