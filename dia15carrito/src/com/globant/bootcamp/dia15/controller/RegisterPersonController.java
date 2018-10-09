package com.globant.bootcamp.dia15.controller;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.service.RegisterPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterPersonController {

    @Autowired
    private RegisterPersonService registerClientService;

    @PostMapping
    public ResponseEntity<Person> registerClient(@RequestBody Person client){
        return ResponseEntity.ok().body(registerClientService.registerPerson(client));
    }
}
