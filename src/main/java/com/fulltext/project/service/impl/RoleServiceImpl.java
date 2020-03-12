package com.fulltext.project.service.impl;

import com.fulltext.project.dao.RoleMapper;
import com.fulltext.project.entity.Role;
import com.fulltext.project.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role selectRoleById(Long id) {
        return null;
    }

    @Override
    public List<Role> selectRoleListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(Role entity) {
        return 0;
    }

    @Override
    public int update(Role entity) {
        return 0;
    }
}
