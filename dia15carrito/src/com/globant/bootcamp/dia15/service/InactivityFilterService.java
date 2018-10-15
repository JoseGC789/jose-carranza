package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.constant.Values;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import com.globant.bootcamp.dia15.service.crud.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public  class InactivityFilterService {

    private Calendar current;
    @Autowired
    private PersonService personService;
    @Autowired
    private ReservationService reservationService;

    public List<Person> getAllInactives (){
        return checkListInactive(personService.getAll());
    }

    public List<Reservation> getAllUnsold(){
        return checkListUnsold(reservationService.getAll());
    }

    private List<Person> checkListInactive(List<Person> personList){
        current = new GregorianCalendar();
        List<Person> inactivePersonList = new ArrayList<>();
        for (Person person:personList) {
            if (person == null || person.getRole() == PersonRoles.SUPER || person.getRole() == PersonRoles.ADMIN ){
                continue;
            }

            int delta = (int) Math.abs(ChronoUnit.DAYS.between(current.toInstant(),person.getLastSeen().toInstant()));

            if (delta > Values.PERSON_DATE_MAX_DELTA.getNumber()){
                inactivePersonList.add(person);
            }

        }

        return inactivePersonList;
    }

    private List<Reservation> checkListUnsold(List<Reservation> reservationList){
        current = new GregorianCalendar();
        List<Reservation> inactiveReservationList = new ArrayList<>();
        for (Reservation reservation:reservationList) {
            if (reservation == null){
                continue;
            }

            int delta = (int) Math.abs(ChronoUnit.DAYS.between(current.toInstant(),reservation.getDateAdded().toInstant()));

            if (delta > Values.RESERVATION_DATE_MAX_DELTA.getNumber()){
                inactiveReservationList.add(reservation);
            }

        }
        return inactiveReservationList;
    }
}
