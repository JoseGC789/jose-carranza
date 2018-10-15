package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import com.globant.bootcamp.dia15.service.crud.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class InactivityFilterServiceTest {

    @Mock
    private PersonService personService;
    @Mock
    private ReservationService reservationService;
    @InjectMocks
    private InactivityFilterService inactivityFilterService;
    private List<Person> personList;
    private List<Reservation> reservationList;

    @Before
    public void SetUp (){
        personList = new ArrayList<>();
        reservationList = new ArrayList<>();
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        Person person4 = new Person();
        Person person5 = new Person();
        Person person6 = new Person();
        Person person7 = new Person();
        Person person8 = new Person();
        Person person9 = new Person();
        person1.setRole(PersonRoles.USER);
        person2.setRole(PersonRoles.USER);
        person3.setRole(PersonRoles.USER);
        person4.setRole(PersonRoles.USER);
        person5.setRole(PersonRoles.USER);
        person6.setRole(PersonRoles.USER);
        person7.setRole(PersonRoles.ADMIN);
        person8.setRole(PersonRoles.ADMIN);
        person9.setRole(PersonRoles.SUPER);
        person1.setLastSeen(new GregorianCalendar(1994,5,5));
        person2.setLastSeen(new GregorianCalendar(1994,5,5));
        person3.setLastSeen(new GregorianCalendar(1994,5,5));
        person4.setLastSeen(new GregorianCalendar(1994,5,5));
        person5.setLastSeen(new GregorianCalendar());
        person6.setLastSeen(new GregorianCalendar());
        person7.setLastSeen(new GregorianCalendar(1994,5,5));
        person8.setLastSeen(new GregorianCalendar(1994,5,5));
        person9.setLastSeen(new GregorianCalendar());
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);
        personList.add(person8);
        personList.add(person9);
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        Reservation reservation3 = new Reservation();
        Reservation reservation4 = new Reservation();
        Reservation reservation5 = new Reservation();
        Reservation reservation6 = new Reservation();
        Reservation reservation7 = new Reservation();
        reservation1.setDateAdded(new GregorianCalendar(1994,5,5));
        reservation2.setDateAdded(new GregorianCalendar(1994,5,5));
        reservation3.setDateAdded(new GregorianCalendar(1994,5,5));
        reservation4.setDateAdded(new GregorianCalendar(1994,5,5));
        reservation5.setDateAdded(new GregorianCalendar(1994,5,5));
        reservation6.setDateAdded(new GregorianCalendar());
        reservation7.setDateAdded(new GregorianCalendar());
        reservationList.add(reservation1);
        reservationList.add(reservation2);
        reservationList.add(reservation3);
        reservationList.add(reservation4);
        reservationList.add(reservation5);
        reservationList.add(reservation6);
        reservationList.add(reservation7);
        when(personService.getAll()).thenReturn(personList);
        when(reservationService.getAll()).thenReturn(reservationList);
    }

    @Test
    public void getAllInactivesByMoreThanDeltaConstantAndIgnoringNonRegularUsersTest(){
        personList = inactivityFilterService.getAllInactives();
        assertEquals(4,personList.size());
    }

    @Test
    public void getAllUnsoldReservationsByMoreThanDeltaConstantTest(){
        reservationList = inactivityFilterService.getAllUnsold();
        assertEquals(5,reservationList.size());
    }
}