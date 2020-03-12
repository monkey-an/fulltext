package com.fulltext.project.service.impl;

import com.fulltext.project.dao.DepartmentMapper;
import com.fulltext.project.entity.Department;
import com.fulltext.project.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public Department selectDepartmentById(Long id) {
        return null;
    }

    @Override
    public List<Department> selectDepartmentListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(Department entity) {
        return 0;
    }

    @Override
    public int update(Department entity) {
        return 0;
    }
}
