package com.fulltext.project.service;
import com.fulltext.project.entity.Role;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface RoleService {
    Role selectRoleById(Long id);
    List<Role> selectRoleListByIdList(List<Long> idList);
    int insert(Role entity);
    int update(Role entity);
}