package com.dia15.controllers;

import com.dia15.domain.entity.Client;
import com.dia15.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {


    @Autowired
    private ClientRepository clients;

    @GetMapping("/client")
    public ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.ok().body(clients.findAll());
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Integer id){
        Client clientFromDB = clients.getOne(id);
        if (clientFromDB != null){
            return ResponseEntity.ok().body(clientFromDB);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/client")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client clientSaved = clients.save(client);
        return ResponseEntity.ok().body(clientSaved);
    }

    @PutMapping("/client")
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        Client clientFromDB = clients.getOne(client.getId());
        if (clientFromDB != null){
            Client clientSaved = clients.save(client);
            return ResponseEntity.ok().body(clientSaved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Integer id){
        Client clientFromDB = clients.getOne(id);
        if (clientFromDB != null){
            clients.delete(clientFromDB);
            return ResponseEntity.ok().body(clientFromDB);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
