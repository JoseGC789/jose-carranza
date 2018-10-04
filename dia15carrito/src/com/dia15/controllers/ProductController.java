package com.dia15.controllers;

import com.dia15.domain.entity.Product;
import com.dia15.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository products;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok().body(products.findAll());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        Product productFromDB = products.getOne(id);
        if (productFromDB != null){
            return ResponseEntity.ok().body(productFromDB);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product productSaved = products.save(product);
        return ResponseEntity.ok().body(productSaved);
    }

    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product productFromDB = products.getOne(product.getId());
        if (productFromDB != null){
            Product productSaved = products.save(product);
            return ResponseEntity.ok().body(productSaved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id){
        Product productFromDB = products.getOne(id);
        if (productFromDB != null){
            products.delete(productFromDB);
            return ResponseEntity.ok().body(productFromDB);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
