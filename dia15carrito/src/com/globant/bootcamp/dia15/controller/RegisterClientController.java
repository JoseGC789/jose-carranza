package com.globant.bootcamp.dia15.controller;

import com.globant.bootcamp.dia15.domain.entity.Client;
import com.globant.bootcamp.dia15.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> registerClient(@RequestBody Client client){
        return ResponseEntity.ok().body(clientService.createClient(client));
    }
}
