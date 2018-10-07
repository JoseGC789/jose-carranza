package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.NotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository products;

    public List<Product> getProducts(){
        return products.findAll();
    }

    public Product getProduct(Integer id){
        products.findById(id)
                .orElseThrow(() -> new NotFoundException());
        return products.getOne(id);

    }

    public Product createProduct(Product product){
        return products.save(product);
    }

    public Product updateProduct(Product product){
        products.findById(product.getId())
                .orElseThrow(() -> new NotFoundException());
        return products.save(product);
    }

    public Product deleteProduct(Integer id){
        products.findById(id)
                .orElseThrow(() -> new NotFoundException());
        Product productFromDB = products.getOne(id);
        products.delete(productFromDB);
        return productFromDB;
    }
}
