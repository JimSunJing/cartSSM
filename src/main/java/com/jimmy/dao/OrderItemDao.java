package com.jimmy.dao;

import com.jimmy.domain.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDao {

    @Insert("insert into orderitem (pid,num,oid) values (#{product.id},#{num},#{order.id})")
    void saveOrderItem(OrderItem orderItem);
}
