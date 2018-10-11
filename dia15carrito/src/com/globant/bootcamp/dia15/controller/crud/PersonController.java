package com.globant.bootcamp.dia15.controller.crud;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.constants.PersonRoles;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private SecurityEndpointService securityEndpointService;
    private List<PersonRoles> requiredRoles = Arrays.asList(PersonRoles.ADMIN, PersonRoles.SUPER);



    @GetMapping
    public ResponseEntity<List<Person>> getPersons(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(personService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@RequestHeader("Authorization") String token, @PathVariable Integer id){
        validateRequest(token);
        return ResponseEntity.ok().body(personService.getPerson(id));
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestHeader("Authorization") String token, @RequestBody Person client){
        validateRequest(token);
        return ResponseEntity.ok().body(personService.createPerson(client));
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestHeader("Authorization") String token, @RequestBody Person client){
        validateRequest(token);
        return ResponseEntity.ok().body(personService.updatePerson(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@RequestHeader("Authorization") String token, @PathVariable Integer id){
        validateRequest(token);
        return ResponseEntity.ok().body(personService.deletePerson(id));
    }

    private void validateRequest(String token){
        securityEndpointService.validateRequest(token,requiredRoles);
    }
}
