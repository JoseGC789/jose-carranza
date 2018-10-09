package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.domain.entity.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class RegisterPersonService {
    @Autowired
    private PersonRepository persons;

    public Person registerPerson(Person person){
        Calendar calendar = new GregorianCalendar();
        person.setDateJoined(calendar);
        person.setLastSeen(calendar);
        person.setRole(PersonRoles.USER);
        person.setReservations(new ArrayList<>());
        return persons.save(person);
    }
}
