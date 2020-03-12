package com.fulltext.project.dao;

import com.fulltext.project.entity.Department;
import java.util.List;

public interface DepartmentMapper {

    int deleteByPrimaryKey(Long id);


    int insert(Department record);


    Department selectByPrimaryKey(Long id);


    List<Department> selectAll();


    int updateByPrimaryKey(Department record);
}