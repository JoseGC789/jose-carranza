package com.globant.bootcamp.dia15.controller.admin.crud;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import com.globant.bootcamp.dia15.service.crud.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private SecurityEndpointService securityEndpointService;
    private List<PersonRoles> requiredRoles = Arrays.asList(PersonRoles.ADMIN, PersonRoles.SUPER);


    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(reservationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@RequestHeader("Authorization") String token, @PathVariable Integer id){
        validateRequest(token);
        return ResponseEntity.ok().body(reservationService.getReservation(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestHeader("Authorization") String token, @RequestBody Reservation reservation){
        validateRequest(token);
        return ResponseEntity.ok().body(reservationService.createReservation(reservation));
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestHeader("Authorization") String token, @RequestBody Reservation reservation){
        validateRequest(token);
        return ResponseEntity.ok().body(reservationService.updateReservation(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> deleteReservation(@RequestHeader("Authorization") String token, @PathVariable Integer id){
        validateRequest(token);
        return ResponseEntity.ok().body(reservationService.deleteReservation(id));
    }

    private void validateRequest(String token){
        securityEndpointService.validateRequest(token, requiredRoles);
    }
}
