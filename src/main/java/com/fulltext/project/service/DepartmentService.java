package com.fulltext.project.service;
import com.fulltext.project.entity.Department;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface DepartmentService {
    Department selectDepartmentById(Long id);
    List<Department> selectDepartmentListByIdList(List<Long> idList);
    int insert(Department entity);
    int update(Department entity);
}