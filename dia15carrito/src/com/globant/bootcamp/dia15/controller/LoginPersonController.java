package com.globant.bootcamp.dia15.controller;


import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginPersonController {
    @Autowired
    private SecurityEndpointService securityEndpointService;

    @PostMapping
    public ResponseEntity<String> loginPerson(@RequestBody Person person){
        return ResponseEntity.ok().body(securityEndpointService.signIn(person));
    }
}
