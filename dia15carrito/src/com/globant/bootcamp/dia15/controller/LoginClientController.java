package com.globant.bootcamp.dia15.controller;

import com.globant.bootcamp.dia15.component.ClientCredentials;
import com.globant.bootcamp.dia15.domain.entity.Client;
import com.globant.bootcamp.dia15.service.LoginClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginClientController {

    @Autowired
    private LoginClientService loginClientService;

    @PostMapping
    public ResponseEntity<Client> loginClient(@RequestBody ClientCredentials credentials){
        return ResponseEntity.ok().body(loginClientService.loginClient(credentials));
    }
}
