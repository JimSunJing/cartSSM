package com.jimmy.service.impl;

import com.jimmy.dao.UserDao;
import com.jimmy.domain.User;
import com.jimmy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public User findUser(User user) {
        // 实际上是登陆方法，根据表单用户信息进行数据库搜索
        List<User> users = dao.findUser(user);
        if (users==null || users.size()==0){
            return null;
        }else{
            return users.get(0);
        }
    }
}
