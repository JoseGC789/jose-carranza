package com.globant.bootcamp.dia15.controller;

import com.globant.bootcamp.dia15.constants.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.service.InactivityDeletionService;
import com.globant.bootcamp.dia15.service.InactivityRetrievalService;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/inactive")
public class InactivityCheckAndDeleteController {
    @Autowired
    private SecurityEndpointService securityEndpointService;
    @Autowired
    private InactivityRetrievalService inactivityRetrievalService;
    @Autowired
    private InactivityDeletionService inactivityDeletionService;
    private List<PersonRoles> requiredRoles = Arrays.asList(PersonRoles.ADMIN, PersonRoles.SUPER);

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getInactivePersons(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(inactivityRetrievalService.getAllInactives());
    }

    @GetMapping("/reservation")
    public ResponseEntity<List<Reservation>> getUnsoldReservations(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(inactivityRetrievalService.getAllUnsold());
    }

    @DeleteMapping("/person")
    public ResponseEntity<List<Person>> deleteInactivePersons(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(inactivityDeletionService.deleteInactivePerson(inactivityRetrievalService.getAllInactives()));
    }

    @DeleteMapping("/reservation")
    public ResponseEntity<List<Reservation>> deleteUnsoldReservations(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(inactivityDeletionService.deleteUnsoldReservation(inactivityRetrievalService.getAllUnsold()));
    }

    private void validateRequest(String token){
        securityEndpointService.validateRequest(token,requiredRoles);
    }
}
