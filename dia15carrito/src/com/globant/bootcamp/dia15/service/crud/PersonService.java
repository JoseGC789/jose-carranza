package com.globant.bootcamp.dia15.service.crud;


import com.globant.bootcamp.dia15.exceptions.ForbiddenException;
import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.repository.PersonRepository;
import com.globant.bootcamp.dia15.misc.PersonRoles;
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
        return persons.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
    }

    public Person getPerson(String username){
        Person person = persons.findByUsername(username);
        if (person == null){
            throw new ResourceNotFoundException("User doesn't exist");
        }
        return person;
    }

    public Person createPerson(Person person){
        protectSuperRole(person.getRole());
        Calendar calendar = new GregorianCalendar();
        person.setDateJoined(calendar);
        person.setLastSeen(calendar);
        person.setReservations(new ArrayList<>());
        person.setPublished(new ArrayList<>());
        return persons.save(person);
    }

    public Person updatePerson(Person person){
        persons.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        protectSuperRole(person.getRole());
        return persons.save(person);
    }

    public Person updatePersonLastSeen (Person person){
        person.setLastSeen(new GregorianCalendar());
        persons.save(person);
        return person;
    }

    public Person deletePerson(Integer id){
        Person personFromDB = persons.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        protectSuperRole(personFromDB.getRole());
        persons.delete(personFromDB);
        return personFromDB;
    }

    private void protectSuperRole(PersonRoles role){
        if (role == PersonRoles.SUPER){
            throw new ForbiddenException("Cannot manipulate person with 'SUPER' property");
        }
    }
}
