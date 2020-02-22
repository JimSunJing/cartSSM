package com.jimmy.test;

import com.jimmy.domain.Product;
import com.jimmy.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class productDaoTest {

    @Autowired
    private ProductService service;

    @Test
    public void testFindAll() {
        List<Product> all = service.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }

    @Test
    public void testFindById() {
        Product product = service.findById(2);
        System.out.println(product);
    }
}
