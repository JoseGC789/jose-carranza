package com.globant.bootcamp.dia15.service.crud;

import com.globant.bootcamp.dia15.constant.ExceptionMessages;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.exceptions.BadRequestException;
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

    public List<Reservation> getAll(Product product){
        return reservationRepository.findByProduct(product);
    }

    public List<Reservation> getAll(Person person){
        return reservationRepository.findByPerson(person);
    }

    public Reservation getReservation(Integer id){
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_RESERVATION.getString()));
    }

    public Reservation createReservation(Reservation reservation){
        initializeFields(reservation);
        checkQuantity(reservation.getQuantity());
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Reservation reservation){
        reservationRepository.findById(reservation.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_RESERVATION.getString()));
        checkQuantity(reservation.getQuantity());
        return reservationRepository.save(reservation);
    }

    public Reservation deleteReservation(Integer id){
        Reservation reservationFromDB = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_RESERVATION.getString()));
        reservationRepository.delete(reservationFromDB);
        return reservationFromDB;
    }

    private void checkQuantity (int quantity){
        if (quantity <=0){
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST_RESERVATION_CAN_NOT_HAVE_ZERO_QUANTITY.getString());
        }

    }

    private void initializeFields (Reservation reservation){
        reservation.setDateAdded(new GregorianCalendar());
    }
}
