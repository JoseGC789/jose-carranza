package com.globant.bootcamp.dia15.controller.user;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.service.UserProductService;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/publish")
public class UserProductController {
    @Autowired
    private SecurityEndpointService securityEndpointService;
    @Autowired
    private UserProductService publishProductService;
    private PersonRoles requiredRoles = PersonRoles.USER;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok().body(publishProductService.getAllByPublisher(validateRequest(token)));
    }

    @PostMapping
    public ResponseEntity<Product> publishProduct(@RequestHeader("Authorization") String token, @RequestBody Product product){
        return ResponseEntity.ok().body(publishProductService.publishProduct(product,validateRequest(token)));
    }

    @DeleteMapping
    public ResponseEntity<Product> withdrawProduct(@RequestHeader("Authorization") String token, @RequestBody Product product){
        return ResponseEntity.ok().body(publishProductService.withdrawProduct(product,validateRequest(token)));
    }

    @PutMapping
    public ResponseEntity<Product> modifyProduct(@RequestHeader("Authorization") String token, @RequestBody Product product){
        return ResponseEntity.ok().body(publishProductService.modifyProduct(product,validateRequest(token)));
    }

    private Person validateRequest(String token){
        return securityEndpointService.validateRequest(token,requiredRoles);
    }
}
