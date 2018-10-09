package com.globant.bootcamp.dia15.service.crud;


import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository persons;

    public List<Person> getPersons(){
        return persons.findAll();
    }

    public Person getPerson(Integer id){
        persons.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person doesn't exist"));
        return persons.getOne(id);

    }

    public Person createPerson(Person person){
        Calendar calendar = new GregorianCalendar();
        person.setDateJoined(calendar);
        person.setLastSeen(calendar);
        person.setReservations(new ArrayList<>());
        return persons.save(person);
    }

    public Person updatePerson(Person person){
        persons.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Person doesn't exist"));
        return persons.save(person);
    }

    public Person deletePerson(Integer id){
        persons.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person doesn't exist"));
        Person personFromDB = persons.getOne(id);
        persons.delete(personFromDB);
        return personFromDB;
    }
}
