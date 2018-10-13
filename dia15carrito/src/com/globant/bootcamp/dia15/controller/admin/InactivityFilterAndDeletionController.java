package com.globant.bootcamp.dia15.controller.admin;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.service.InactivityDeletionService;
import com.globant.bootcamp.dia15.service.InactivityFilterService;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/inactive")
public class InactivityFilterAndDeletionController {
    @Autowired
    private SecurityEndpointService securityEndpointService;
    @Autowired
    private InactivityFilterService inactivityFilterService;
    @Autowired
    private InactivityDeletionService inactivityDeletionService;
    private List<PersonRoles> requiredRoles = Arrays.asList(PersonRoles.ADMIN, PersonRoles.SUPER);

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getInactivePersons(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(inactivityFilterService.getAllInactives());
    }

    @DeleteMapping("/person")
    public ResponseEntity<List<Person>> deleteInactivePersons(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(inactivityDeletionService.deleteInactivePerson(inactivityFilterService.getAllInactives()));
    }

    @GetMapping("/reservation")
    public ResponseEntity<List<Reservation>> getUnsoldReservations(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(inactivityFilterService.getAllUnsold());
    }

    @DeleteMapping("/reservation")
    public ResponseEntity<List<Reservation>> deleteUnsoldReservations(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(inactivityDeletionService.deleteUnsoldReservation(inactivityFilterService.getAllUnsold()));
    }

    private void validateRequest(String token){
        securityEndpointService.validateRequest(token,requiredRoles);
    }
}
