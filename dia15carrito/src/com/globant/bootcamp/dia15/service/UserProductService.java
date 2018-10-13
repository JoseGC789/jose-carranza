package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.constant.ExceptionMessages;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.exceptions.ForbiddenException;
import com.globant.bootcamp.dia15.service.crud.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProductService {
    @Autowired
    private ProductService productService;

    public List<Product> getAllByPublisher (Person publisher){
        return productService.getAll(publisher);
    }

    public Product publishProduct(Product product, Person publisher){
        product.setPublisher(publisher);
        return productService.createProduct(product);
    }

    public Product withdrawProduct(Product product, Person publisher){
        product = productService.getProduct(product.getId());
        checkOwnership(product.getPublisher().getUsername(),publisher.getUsername());
        return productService.deleteProduct(product.getId());
    }

    public Product modifyProduct (Product product, Person publisher){
        Product productFromDB = productService.getProduct(product.getId());
        checkOwnership(productFromDB.getPublisher().getUsername(),publisher.getUsername());
        productFromDB = product;
        productFromDB.setPublisher(publisher);
        return productService.updateProduct(productFromDB);
    }

    private void checkOwnership (String owner, String publisher){
        if (!owner.equals(publisher)){
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_PRODUCT_IS_NOT_YOURS.getString());
        }

    }
}
