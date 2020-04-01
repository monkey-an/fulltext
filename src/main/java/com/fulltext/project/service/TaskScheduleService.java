package com.fulltext.project.service;
import com.fulltext.project.bo.PageBean;
import com.fulltext.project.entity.TaskSchedule;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/04/01.
 */
public interface TaskScheduleService {
    TaskSchedule selectTaskScheduleById(Long id);
    List<TaskSchedule> selectTaskScheduleListByIdList(List<Long> idList);
    int insert(TaskSchedule entity);
    int update(TaskSchedule entity);

    List<TaskSchedule> selectAll();
    List<TaskSchedule> selectByTaskName(String taskName);

    PageInfo<TaskSchedule> selectAllByTaskNameAndPaging(int pageNo, int pageSize, String taskName);

    String addTaskSchedule(HttpServletRequest request) throws ParseException;
}