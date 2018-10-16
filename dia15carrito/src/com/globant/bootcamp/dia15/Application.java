package com.globant.bootcamp.dia15;

import com.globant.bootcamp.dia15.domain.entity.Category;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.repository.CategoryRepository;
import com.globant.bootcamp.dia15.domain.repository.PersonRepository;
import com.globant.bootcamp.dia15.constant.PersonRoles;
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
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        Person superAdmin = new Person();
        superAdmin.setId(1);
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
        personRepository.save(superAdmin);

        if(!categoryRepository.findById(1).isPresent()){
            Category category = new Category();
            category.setId(1);
            category.setName("Uncategorized");
            category.setDescription("These products have not been categorized.");
            categoryRepository.save(category);
        }
        SecurityEndpointService.initializeSUPER(superAdmin);

        superAdmin = new Person();
        superAdmin.setUsername("SUPER2");
        superAdmin.setPassword("SUPER2");
        superAdmin.setRole(PersonRoles.USER);
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
        superAdmin.setUsername("SUPER3");
        superAdmin.setPassword("SUPER2");
        superAdmin.setRole(PersonRoles.USER);
        superAdmin.setDateJoined(calendar);
        superAdmin.setLastSeen(calendar);
        superAdmin.setReservations(new ArrayList<>());
        superAdmin.setPublished(new ArrayList<>());
        superAdmin.setEmail("josegcarranza@gmail.com");
        superAdmin.setFirst("Jose");
        superAdmin.setLast("Carranza");
        superAdmin.setLastSeen(new GregorianCalendar(1994,4,6,12,0));
        personRepository.save(superAdmin);

        superAdmin = new Person();
        superAdmin.setUsername("SUPER4");
        superAdmin.setPassword("SUPER2");
        superAdmin.setRole(PersonRoles.USER);
        superAdmin.setDateJoined(calendar);
        superAdmin.setLastSeen(calendar);
        superAdmin.setReservations(new ArrayList<>());
        superAdmin.setPublished(new ArrayList<>());
        superAdmin.setEmail("josegcarranza@gmail.com");
        superAdmin.setFirst("Jose");
        superAdmin.setLast("Carranza");
        superAdmin.setBirth(new GregorianCalendar(1994,4,6,12,0));
        personRepository.save(superAdmin);

    }
}
