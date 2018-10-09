package com.globant.bootcamp.dia15;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.repository.PersonRepository;
import com.globant.bootcamp.dia15.misc.PersonRoles;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main (String[] args){
        SpringApplication.run(Application.class, args);
    }
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!personRepository.findById(1).isPresent()){
            Person superAdmin = new Person();
            superAdmin.setUsername("ADMIN");
            superAdmin.setRole(PersonRoles.ADMIN);
            personService.createPerson(superAdmin);
        }
    }
}
