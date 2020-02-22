package com.jimmy.dao;

import com.jimmy.domain.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    /**
     * 查询所有的产品列表
     * @return
     */
    @Select("select * from product order by id desc")
    List<Product> findAll();

    /**
     * 根据id查找产品信息
     * @param pid
     * @return
     */
    @Select("select * from product where id = #{pid}")
    Product findById(int pid);
}
