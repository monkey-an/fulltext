package com.fulltext.project.service.impl;

import com.fulltext.project.bo.PageBean;
import com.fulltext.project.constants.ConstantValue;
import com.fulltext.project.dao.TaskScheduleMapper;
import com.fulltext.project.entity.TaskSchedule;
import com.fulltext.project.entity.User;
import com.fulltext.project.service.TaskScheduleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class TaskScheduleServiceImpl implements TaskScheduleService {
    @Resource
    private TaskScheduleMapper taskScheduleMapper;

    @Override
    public TaskSchedule selectTaskScheduleById(Long id) {
        return taskScheduleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TaskSchedule> selectTaskScheduleListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(TaskSchedule entity) {
        return taskScheduleMapper.insert(entity);
    }

    @Override
    public int update(TaskSchedule entity) {
        return taskScheduleMapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<TaskSchedule> selectAll() {
        return taskScheduleMapper.selectAll();
    }

    @Override
    public List<TaskSchedule> selectByTaskName(String taskName) {
        return taskScheduleMapper.selectByTaskName(taskName);
    }

    @Override
    public PageInfo<TaskSchedule> selectAllByTaskNameAndPaging(int pageNo, int pageSize, String taskName) {
        PageInfo<TaskSchedule> info;
        if(StringUtils.isNotEmpty(taskName)){
            PageHelper.startPage(pageNo, pageSize);
            List<TaskSchedule> taskFormHtmlList = selectByTaskName(taskName);
            info = new PageInfo<>(taskFormHtmlList);
        }else{
            PageHelper.startPage(pageNo, pageSize);
            List<TaskSchedule> taskFormHtmlList = selectAll();
            info = new PageInfo<>(taskFormHtmlList);
        }

        return info;
    }

    @Override
    public String addTaskSchedule(HttpServletRequest request) throws ParseException {
        String taskName = request.getParameter("task_name");
        String beginTime = request.getParameter("begin_time");
        String endTime = request.getParameter("end_time");

        if(StringUtils.isEmpty(taskName) || StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)){
            return "ERROR";
        }

        User user = (User) request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        TaskSchedule taskSchedule = TaskSchedule.builder()
                .taskName(taskName)
                .beginDateTime(sdf.parse(beginTime))
                .endDateTime(sdf.parse(endTime))
                .operUserId(user.getId())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        insert(taskSchedule);

        return "SUCCESS";
    }
}
