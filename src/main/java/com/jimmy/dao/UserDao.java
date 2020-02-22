package com.jimmy.dao;

import com.jimmy.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    @Select("select * from user where name = #{name} and password = #{password}")
    List<User> findUser(User user);
}
