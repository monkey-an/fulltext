package com.fulltext.project.dao;

import com.fulltext.project.entity.TaskDesign;
import java.util.List;

public interface TaskDesignMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(TaskDesign record);


    List<TaskDesign> selectAll();
}