package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class InactivityDeletionServiceTest {
    @Mock
    private PersonService personService;
    @Mock
    private UserReservationService userReservationService;
    @InjectMocks
    private InactivityDeletionService inactivityDeletionService;

    @Before
    public void SetUp(){
        when(personService.deletePerson(any(Integer.class))).thenReturn(new Person());
        when(userReservationService.closeReservation(any(Person.class),any(Integer.class))).thenReturn(new Reservation());
    }

    @Test
    public void deleteInactivePersonTest (){
        List<Person> testarray = new ArrayList<>(Arrays.asList(new Person(),new Person()));
        inactivityDeletionService.deleteInactivePerson(testarray);
        verify(personService,times(testarray.size())).deletePerson(any(Integer.class));
    }


    @Test
    public void deleteUnsoldReservation (){
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        reservation1.setPerson(new Person());
        reservation1.setProduct(new Product());
        reservation2.setPerson(new Person());
        reservation2.setProduct(new Product());
        List<Reservation> testarray = new ArrayList<>(Arrays.asList(reservation1,reservation2));
        inactivityDeletionService.deleteUnsoldReservation(testarray);
        verify(userReservationService,times(testarray.size())).closeReservation(any(Person.class),any(Integer.class));
    }

}