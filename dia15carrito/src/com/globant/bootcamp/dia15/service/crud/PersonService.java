package com.globant.bootcamp.dia15.service.crud;


import com.globant.bootcamp.dia15.constants.ExceptionMessages;
import com.globant.bootcamp.dia15.exceptions.BadRequestException;
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
    @Autowired
    private ReservationService reservationService;

    public List<Person> getAll(){
        List<Person> personList = personRepository.findAll();

        for (Person person:personList) {
            setPublisherList(person);
        }

        return personList;
    }

    public Person getPerson(Integer id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PERSON.getString()));
        checkNullity(person);
        setPublisherList(person);
        return person;
    }

    public Person getPerson(String username){
        Person person = personRepository.findByUsername(username);
        checkNullity(person);
        setPublisherList(person);
        return person;
    }

    public Person createPerson(Person person){
        checkUsernameUniqueness(person.getUsername());
        protectSuperRole(person.getRole());
        initializeFields(person);
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
        protectPersonWithAssets(personFromDB);
        personRepository.delete(personFromDB);
        return personFromDB;
    }

    public Person updatePersonLastSeen (Person person){
        person.setLastSeen(new GregorianCalendar());
        personRepository.save(person);
        return person;
    }

    private void initializeFields(Person person){
        person.setReservations(new ArrayList<>());
        person.setPublished(new ArrayList<>());
        person.setPublishedList(new ArrayList<>());
        Calendar calendar = new GregorianCalendar();
        person.setDateJoined(calendar);
        person.setLastSeen(calendar);
    }

    private void checkNullity (Person person){
        if (person == null){
            throw new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PERSON.getString());
        }
    }

    private void protectPersonWithAssets(Person person){
        setPublisherList(person);
        if (!person.getPublishedList().isEmpty()){
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST_PERSON_HAS_PRODUCTS_PUBLISHED.getString());
        }
        if (!reservationService.getReservation(person).isEmpty()){
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST_PERSON_HAS_RESERVATION_PENDING.getString());
        }
    }

    private void protectSuperRole(PersonRoles role){
        if (role == PersonRoles.SUPER){
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_MANIPULATION_OF_SUPER.getString());
        }
    }

    private void setPublisherList (Person person){
        person.setPublishedList(productService.getAll(person));
    }

    private void checkUsernameUniqueness(String username){
        if (personRepository.findByUsername(username) != null){
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST_PERSON_USERNAME_MUST_BE_UNIQUE.getString());
        }
    }
}
