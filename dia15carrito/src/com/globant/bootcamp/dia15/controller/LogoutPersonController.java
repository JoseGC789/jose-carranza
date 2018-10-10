package com.globant.bootcamp.dia15.controller;


import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logout")
public class LogoutPersonController {
    @Autowired
    private SecurityEndpointService securityEndpointService;

    @GetMapping
    public ResponseEntity<Person> logoutPerson(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok().body(securityEndpointService.signOut(token));
    }
}
