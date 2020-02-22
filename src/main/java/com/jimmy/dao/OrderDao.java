package com.jimmy.dao;

import com.jimmy.domain.Order;
import com.jimmy.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    @Insert("insert into order_ (uid) values (#{user.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveOrder(Order order);
}
