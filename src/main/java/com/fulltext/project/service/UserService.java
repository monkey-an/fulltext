package com.fulltext.project.service;
import com.fulltext.project.entity.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface UserService {
    User selectUserById(Long id);
    List<User> selectUserListByIdList(List<Long> idList);
    int insert(User entity);
    int update(User entity);

    User login(String userName,String password);

    PageInfo<User> selectAllUserByPaging(int pageNo, int pageSize,String nickName,String email);

    User selectUserByRealName(String userName);
}