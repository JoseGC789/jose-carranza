package com.globant.bootcamp.dia15.controller.admin.crud;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import com.globant.bootcamp.dia15.service.crud.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SecurityEndpointService securityEndpointService;
    private List<PersonRoles> requiredRoles = Arrays.asList(PersonRoles.ADMIN, PersonRoles.SUPER);


    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@RequestHeader("Authorization") String token, @PathVariable Integer id){
        validateRequest(token);
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestHeader("Authorization") String token, @RequestBody Product product){
        validateRequest(token);
        return ResponseEntity.ok().body(productService.createProduct(product));
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestHeader("Authorization") String token, @RequestBody Product product){
        validateRequest(token);
        return ResponseEntity.ok().body(productService.updateProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@RequestHeader("Authorization") String token, @PathVariable Integer id){
        validateRequest(token);
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }


    private void validateRequest(String token){
        securityEndpointService.validateRequest(token, requiredRoles);
    }
}
