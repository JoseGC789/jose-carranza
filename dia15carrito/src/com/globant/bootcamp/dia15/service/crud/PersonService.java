package com.globant.bootcamp.dia15.service.crud;


import com.globant.bootcamp.dia15.constants.ExceptionMessages;
import com.globant.bootcamp.dia15.exceptions.ForbiddenException;
import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.repository.PersonRepository;
import com.globant.bootcamp.dia15.constants.PersonRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ProductService productService;

    public List<Person> getPersonRepository(){
        List<Person> personList = personRepository.findAll();

        for (Person person:personList) {
            setPublisherList(person);
        }

        return personList;
    }

    public Person getPerson(Integer id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PERSON.getString()));

        setPublisherList(person);
        return person;
    }

    public Person getPerson(String username){
        Person person = personRepository.findByUsername(username);
        if (person == null){
            throw new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PERSON.getString());
        }

        setPublisherList(person);
        return person;
    }

    public Person createPerson(Person person){
        protectSuperRole(person.getRole());

        person.setReservations(new ArrayList<>());
        person.setPublished(new ArrayList<>());
        person.setPublishedList(new ArrayList<>());

        Calendar calendar = new GregorianCalendar();
        person.setDateJoined(calendar);
        person.setLastSeen(calendar);

        return personRepository.save(person);
    }

    public Person updatePerson(Person person){
        personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PERSON.getString()));

        protectSuperRole(person.getRole());

        return personRepository.save(person);
    }

    public Person deletePerson(Integer id){
        Person personFromDB = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PERSON.getString()));
        protectSuperRole(personFromDB.getRole());
        personRepository.delete(personFromDB);
        return personFromDB;
    }

    public Person updatePersonLastSeen (Person person){
        person.setLastSeen(new GregorianCalendar());
        personRepository.save(person);
        return person;
    }

    private void protectSuperRole(PersonRoles role){
        if (role == PersonRoles.SUPER){
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_MANIPULATION_SUPER.toString());
        }
    }

    private void setPublisherList (Person person){
        person.setPublishedList(productService.getProductByPublisher(person));
    }
}
