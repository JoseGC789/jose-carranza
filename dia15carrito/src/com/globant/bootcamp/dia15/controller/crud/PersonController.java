package com.globant.bootcamp.dia15.controller.crud;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;


    @GetMapping
    public ResponseEntity<List<Person>> getPersons(){
        return ResponseEntity.ok().body(personService.getPersons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Integer id){
        return ResponseEntity.ok().body(personService.getPerson(id));
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person client){
        return ResponseEntity.ok().body(personService.createPerson(client));
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person client){
        return ResponseEntity.ok().body(personService.updatePerson(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable Integer id){
        return ResponseEntity.ok().body(personService.deletePerson(id));
    }
}
