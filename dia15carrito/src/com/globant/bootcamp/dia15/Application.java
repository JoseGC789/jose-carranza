package com.globant.bootcamp.dia15;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.repository.PersonRepository;
import com.globant.bootcamp.dia15.constants.PersonRoles;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
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
        superAdmin.setEmail("josegcarranza@gmail.com");
        superAdmin.setFirst("Jose");
        superAdmin.setLast("Carranza");
        superAdmin.setBirth(new GregorianCalendar(1994,4,6,12,0));

        if (!personRepository.findById(1).isPresent()){
            personRepository.save(superAdmin);
        }

        SecurityEndpointService.initializeSUPER(superAdmin);

        superAdmin = new Person();
        superAdmin.setUsername("SUPER2");
        superAdmin.setPassword("SUPER2");
        superAdmin.setRole(PersonRoles.USER);
        calendar = new GregorianCalendar(1994,5,5);
        superAdmin.setDateJoined(calendar);
        superAdmin.setLastSeen(calendar);
        superAdmin.setReservations(new ArrayList<>());
        superAdmin.setPublished(new ArrayList<>());
        superAdmin.setEmail("josegcarranza@gmail.com");
        superAdmin.setFirst("Jose");
        superAdmin.setLast("Carranza");
        superAdmin.setBirth(new GregorianCalendar(1994,4,6,12,0));
        personRepository.save(superAdmin);

        superAdmin = new Person();
        superAdmin.setUsername("SUPER23");
        superAdmin.setPassword("SUPER2");
        superAdmin.setRole(PersonRoles.USER);
        calendar = new GregorianCalendar(1994,5,5);
        superAdmin.setDateJoined(calendar);
        superAdmin.setLastSeen(calendar);
        superAdmin.setReservations(new ArrayList<>());
        superAdmin.setPublished(new ArrayList<>());
        superAdmin.setEmail("josegcarranza@gmail.com");
        superAdmin.setFirst("Jose");
        superAdmin.setLast("Carranza");
        superAdmin.setBirth(new GregorianCalendar(1994,4,6,12,0));
        personRepository.save(superAdmin);

        superAdmin = new Person();
        superAdmin.setUsername("SUPER42");
        superAdmin.setPassword("SUPER2");
        superAdmin.setRole(PersonRoles.USER);
        calendar = new GregorianCalendar(1994,5,5);
        superAdmin.setDateJoined(calendar);
        superAdmin.setLastSeen(calendar);
        superAdmin.setReservations(new ArrayList<>());
        superAdmin.setPublished(new ArrayList<>());
        superAdmin.setEmail("josegcarranza@gmail.com");
        superAdmin.setFirst("Jose");
        superAdmin.setLast("Carranza");
        superAdmin.setBirth(new GregorianCalendar(1994,4,6,12,0));
        personRepository.save(superAdmin);

    }/*
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(500);
        return loggingFilter;
    }*/
}
