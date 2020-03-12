package com.fulltext.project.dao;

import com.fulltext.project.entity.Task;
import java.util.List;

public interface TaskMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(Task record);


    List<Task> selectAll();
}