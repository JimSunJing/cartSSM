package com.jimmy.service;

import com.jimmy.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(int pid);
}
