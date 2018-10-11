package com.globant.bootcamp.dia15.service.crud;

import com.globant.bootcamp.dia15.constants.ExceptionMessages;
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
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.findAll();
    }

    public Reservation getReservation(Integer id){
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_RESERVATION.toString()));
    }

    public List<Reservation> getReservation(Person person){
        return reservationRepository.findByPerson(person);
    }

    public Reservation createReservation(Reservation reservation){
        initializeFields(reservation);
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Reservation reservation){
        reservationRepository.findById(reservation.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_RESERVATION.toString()));
        return reservationRepository.save(reservation);
    }

    public Reservation deleteReservation(Integer id){
        Reservation reservationFromDB = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_RESERVATION.toString()));
        reservationRepository.delete(reservationFromDB);
        return reservationFromDB;
    }

    private void initializeFields (Reservation reservation){
        reservation.setDateAdded(new GregorianCalendar());
    }
}
