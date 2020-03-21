package com.fulltext.project.service;
import com.fulltext.project.entity.UserRole;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface UserRoleService {
    UserRole selectUserRoleById(Long id);
    List<UserRole> selectUserRoleListByIdList(List<Long> idList);
    int insert(UserRole entity);
    int update(UserRole entity);

    UserRole selectOneByUserId(Long userId);
    UserRole selectOneByRoleName(String roleName);
    List<UserRole> selectListByRoleName(String roleName);
}