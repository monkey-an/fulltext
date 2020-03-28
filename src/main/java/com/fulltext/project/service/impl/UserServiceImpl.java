package com.fulltext.project.service.impl;

import com.fulltext.project.dao.UserMapper;
import com.fulltext.project.entity.User;
import com.fulltext.project.service.UserService;
import com.fulltext.project.util.Md5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;



    @Override
    public User selectUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectUserListByIdList(List<Long> idList) {
        return userMapper.selectByPrimaryKeyList(idList);
    }

    @Override
    public int insert(User entity) {
        return 0;
    }

    @Override
    public int update(User entity) {
        return 0;
    }

    @Override
    public User login(String userName, String password) {
        User user = userMapper.selectOneByLoginName(userName);
        if(user!=null){
            String md5Password = Md5Util.encode(password+user.getSalt());
            if(!md5Password.equals(user.getPassword())){
                user = null;
            }
        }
        return user;
    }

    @Override
    public PageInfo<User> selectAllUserByPaging(int pageNo, int pageSize,String nickName,String email) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> allUser = userMapper.selectAll(nickName,email);
        PageInfo<User> info=new PageInfo<>(allUser);
        return info;
    }

    @Override
    public User selectUserByRealName(String userName) {
        return userMapper.selectUserByRealName(userName);
    }
}
