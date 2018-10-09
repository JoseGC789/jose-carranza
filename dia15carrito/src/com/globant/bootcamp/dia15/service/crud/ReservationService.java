package com.globant.bootcamp.dia15.service.crud;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.domain.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
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
                .orElseThrow(() -> new ResourceNotFoundException("Reservation doesn't exist"));
        return reservations.getOne(id);

    }

    public List<Reservation> getReservation(Person person){
        return reservations.findByPerson(person);


    }

    public Reservation createReservation(Reservation reservation){
        reservation.setDateAdded(new GregorianCalendar());
        return reservations.save(reservation);
    }

    public Reservation updateReservation(Reservation reservation){
        reservations.findById(reservation.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Reservation doesn't exist"));
        return reservations.save(reservation);
    }

    public Reservation deleteReservation(Integer id){
        reservations.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation doesn't exist"));
        Reservation reservationFromDB = reservations.getOne(id);
        reservations.delete(reservationFromDB);
        return reservationFromDB;
    }
}
