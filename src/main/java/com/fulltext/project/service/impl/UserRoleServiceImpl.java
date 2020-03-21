package com.fulltext.project.service.impl;

import com.fulltext.project.dao.UserRoleMapper;
import com.fulltext.project.entity.UserRole;
import com.fulltext.project.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRole selectUserRoleById(Long id) {
        return null;
    }

    @Override
    public List<UserRole> selectUserRoleListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(UserRole entity) {
        return 0;
    }

    @Override
    public int update(UserRole entity) {
        return 0;
    }

    @Override
    public UserRole selectOneByUserId(Long userId) {
        return userRoleMapper.selectOneByUserId(userId);
    }

    @Override
    public UserRole selectOneByRoleName(String roleName) {
        return userRoleMapper.selectOneByRoleName(roleName);
    }

    @Override
    public List<UserRole> selectListByRoleName(String roleName) {
        return userRoleMapper.selectListByRoleName(roleName);
    }
}
