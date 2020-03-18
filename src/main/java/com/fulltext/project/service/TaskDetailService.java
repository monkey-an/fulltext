package com.fulltext.project.service;
import com.fulltext.project.entity.TaskDetail;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/14.
 */
public interface TaskDetailService {
    TaskDetail selectTaskDetailById(Long id);
    List<TaskDetail> selectTaskDetailListByIdList(List<Long> idList);
    int insert(TaskDetail entity);
    int update(TaskDetail entity);

    List<TaskDetail> selectTaskDetailByTaskId(Long taskId);

    List<TaskDetail> selectTaskDetailByTaskIdAndOperNodeNo(Long taskId, String formNo);
}