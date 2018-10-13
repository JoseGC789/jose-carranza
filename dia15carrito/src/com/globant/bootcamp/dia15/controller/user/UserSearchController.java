package com.globant.bootcamp.dia15.controller.user;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import com.globant.bootcamp.dia15.service.crud.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class UserSearchController {
    @Autowired
    private SecurityEndpointService securityEndpointService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PersonService personService;

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@RequestHeader("Authorization") String token, @PathVariable("name") String name){
        validateRequest(token);
        return ResponseEntity.ok().body(productService.getAll(name));
    }

    @GetMapping("/user/{publisher}")
    public ResponseEntity<List<Product>> getProductsByPublisher(@RequestHeader("Authorization") String token, @PathVariable("publisher") String publisher){
        validateRequest(token);
        return ResponseEntity.ok().body( personService.getPerson(publisher).getPublishedList());
    }

    private Person validateRequest(String token){
        return securityEndpointService.validateRequest(token);
    }

}
