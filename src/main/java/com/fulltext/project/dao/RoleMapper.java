package com.fulltext.project.dao;

import com.fulltext.project.entity.Role;
import java.util.List;

public interface RoleMapper {

    int deleteByPrimaryKey(Long id);


    int insert(Role record);


    Role selectByPrimaryKey(Long id);


    List<Role> selectAll();


    int updateByPrimaryKey(Role record);
}