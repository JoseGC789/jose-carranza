package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.service.crud.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishProductService {
    @Autowired
    private ProductService productService;
/*
    public Product createProduct (Product product){

    }*/
}
