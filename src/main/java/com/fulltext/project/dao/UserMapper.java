package com.fulltext.project.dao;

import com.fulltext.project.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Long id);


    int insert(User record);


    User selectByPrimaryKey(@Param("id") Long id);


    List<User> selectAll(@Param("nickName") String nickName,@Param("email")String email);


    int updateByPrimaryKey(User record);

    User selectOneByLoginName(@Param("loginName") String loginName);
}