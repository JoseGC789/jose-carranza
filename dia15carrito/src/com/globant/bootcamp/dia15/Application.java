package com.globant.bootcamp.dia15;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.repository.PersonRepository;
import com.globant.bootcamp.dia15.misc.PersonRoles;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main (String[] args){
        SpringApplication.run(Application.class, args);
    }
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        Person superAdmin = new Person();
        superAdmin.setUsername("SUPER");
        superAdmin.setPassword("SUPER");
        superAdmin.setRole(PersonRoles.SUPER);
        Calendar calendar = new GregorianCalendar();
        superAdmin.setDateJoined(calendar);
        superAdmin.setLastSeen(calendar);
        superAdmin.setReservations(new ArrayList<>());
        superAdmin.setPublished(new ArrayList<>());

        if (!personRepository.findById(1).isPresent()){
            personRepository.save(superAdmin);
        }

        SecurityEndpointService.initializeSUPER(superAdmin);

    }
}
