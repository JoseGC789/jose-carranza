package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.NotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.domain.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservations;

    public List<Reservation> getReservations(){
        return reservations.findAll();
    }

    public Reservation getReservation(Integer id){
        reservations.findById(id)
                .orElseThrow(() -> new NotFoundException());
        return reservations.getOne(id);

    }

    public Reservation createReservation(Reservation reservation){
        return reservations.save(reservation);
    }

    public Reservation updateReservation(Reservation reservation){
        reservations.findById(reservation.getId())
                .orElseThrow(() -> new NotFoundException());
        return reservations.save(reservation);
    }

    public Reservation deleteReservation(Integer id){
        reservations.findById(id)
                .orElseThrow(() -> new NotFoundException());
        Reservation reservationFromDB = reservations.getOne(id);
        reservations.delete(reservationFromDB);
        return reservationFromDB;
    }
}
