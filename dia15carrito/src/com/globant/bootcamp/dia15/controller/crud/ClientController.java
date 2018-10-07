package com.globant.bootcamp.dia15.controller.crud;

import com.globant.bootcamp.dia15.domain.entity.Client;
import com.globant.bootcamp.dia15.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping
    public ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.ok().body(clientService.getClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Integer id){
        return ResponseEntity.ok().body(clientService.getClient(id));
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        return ResponseEntity.ok().body(clientService.createClient(client));
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        return ResponseEntity.ok().body(clientService.updateClient(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Integer id){
        return ResponseEntity.ok().body(clientService.deleteClient(id));
    }
}
