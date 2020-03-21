package com.fulltext.project.dao;

import com.fulltext.project.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {

    int deleteByPrimaryKey(Long id);


    int insert(UserRole record);


    UserRole selectByPrimaryKey(Long id);


    List<UserRole> selectAll();


    int updateByPrimaryKey(UserRole record);

    UserRole selectOneByUserId(@Param("userId") Long userId);

    UserRole selectOneByRoleName(@Param("roleName") String roleName);

    List<UserRole> selectListByRoleName(@Param("roleName") String roleName);
}