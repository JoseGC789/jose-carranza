package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {
    @Autowired
    private PersonService personService;

    public Person registerPerson(Person person){
        person.setRole(PersonRoles.USER);
        return personService.createPerson(person);
    }
}
