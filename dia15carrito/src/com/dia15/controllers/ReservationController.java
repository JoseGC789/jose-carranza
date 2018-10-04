package com.dia15.controllers;


import com.dia15.domain.entity.Product;
import com.dia15.domain.entity.Reservation;
import com.dia15.domain.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository reservations;

    @GetMapping("/reservation")
    public ResponseEntity<List<Reservation>> getReservations(){
        return ResponseEntity.ok().body(reservations.findAll());
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Integer id){
        Reservation reservationFromDB = reservations.getOne(id);
        if (reservationFromDB != null){
            return ResponseEntity.ok().body(reservationFromDB);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        Reservation reservationSaved = reservations.save(reservation);
        return ResponseEntity.ok().body(reservationSaved);
    }

    @PutMapping("/reservation")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation){
        Reservation reservationFromDB = reservations.getOne(reservation.getId());
        if (reservationFromDB != null){
            Reservation reservationSaved = reservations.save(reservation);
            return ResponseEntity.ok().body(reservationSaved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Integer id){
        Reservation reservationFromDB = reservations.getOne(id);
        if (reservationFromDB != null){
            reservations.delete(reservationFromDB);
            return ResponseEntity.ok().body(reservationFromDB);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
