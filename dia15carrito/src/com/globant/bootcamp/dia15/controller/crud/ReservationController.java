package com.globant.bootcamp.dia15.controller.crud;

import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.service.crud.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;


    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations(){
        return ResponseEntity.ok().body(reservationService.getReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Integer id){
        return ResponseEntity.ok().body(reservationService.getReservation(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        return ResponseEntity.ok().body(reservationService.createReservation(reservation));
    }

    @PutMapping
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation){
        return ResponseEntity.ok().body(reservationService.updateReservation(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Integer id){
        return ResponseEntity.ok().body(reservationService.deleteReservation(id));
    }
}
