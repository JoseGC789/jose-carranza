package com.globant.bootcamp.dia15.controller.user;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import com.globant.bootcamp.dia15.service.UserReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/reservation")
public class UserReservationController {
    @Autowired
    private UserReservationService userReservationService;
    @Autowired
    private SecurityEndpointService securityEndpointService;
    private PersonRoles requiredRoles = PersonRoles.SUPER;

    @GetMapping
    public ResponseEntity<List<Reservation>> getUserReservation(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok().body(userReservationService.getUserReservations(validateRequest(token)));
    }

    @GetMapping("/{id3}")
    public ResponseEntity<List<Reservation>> getProductReservation(@RequestHeader("Authorization") String token, @PathVariable("id3") Integer id3){
        return ResponseEntity.ok().body(userReservationService.getProductReservations(validateRequest(token),id3));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Reservation> sellReservation(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){

        return ResponseEntity.ok().body(userReservationService.sellReservation(validateRequest(token),id));
    }

    @PostMapping("/{id}/{amount}")
    public ResponseEntity<Reservation> makeUserReservation(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id, @PathVariable("amount") int amount){

        return ResponseEntity.ok().body(userReservationService.createReservation(validateRequest(token),id,amount));
    }

    @DeleteMapping("/{id2}")
    public ResponseEntity<Reservation> deleteUserReservation(@RequestHeader("Authorization") String token, @PathVariable("id2") Integer id2){

        return ResponseEntity.ok().body(userReservationService.closeReservation(validateRequest(token),id2));
    }

    private Person validateRequest(String token){
        return securityEndpointService.validateRequest(token,requiredRoles);
    }
}
