package com.fulltext.project.service;
import com.fulltext.project.entity.Task;
import com.fulltext.project.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/14.
 */
public interface TaskService {
    Task selectTaskById(Long id);
    List<Task> selectTaskListByIdList(List<Long> idList);
    int insert(Task entity);
    int update(Task entity);
    Task selectTaskByTaskId(Long taskId);

    List<Task> selectByCommitUserId(Long userId);
    List<Task> selectByCurrentUserId(Long userId);

    String initTaskDetail(User user, Long taskId, Model model);
    String initMyTaskDetail(User user, Long taskId, Model model);

    List<Task> selectByApprovalUserid(Long id);

    String initMyApprovalTaskDetail(User user, Long taskId, Model model);
}