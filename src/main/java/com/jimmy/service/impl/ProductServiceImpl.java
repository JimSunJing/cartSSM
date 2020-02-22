package com.jimmy.service.impl;

import com.jimmy.dao.ProductDao;
import com.jimmy.domain.Product;
import com.jimmy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Override
    public List<Product> findAll() {
        System.out.println("product service - find all");
        List<Product> all = dao.findAll();
        return all;
    }

    @Override
    public Product findById(int pid) {
        return dao.findById(pid);
    }
}
