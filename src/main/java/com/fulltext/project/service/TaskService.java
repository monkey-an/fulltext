package com.fulltext.project.service;
import com.fulltext.project.entity.Task;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface TaskService {
    Task selectTaskById(Long id);
    List<Task> selectTaskListByIdList(List<Long> idList);
    int insert(Task entity);
    int update(Task entity);
}