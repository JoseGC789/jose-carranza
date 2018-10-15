package com.globant.bootcamp.dia15.service.crud;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.domain.repository.PersonRepository;
import com.globant.bootcamp.dia15.exceptions.BadRequestException;
import com.globant.bootcamp.dia15.exceptions.ForbiddenException;
import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class PersonServiceTest {
    @Autowired
    private PersonService realPersonService;
    @InjectMocks
    private PersonService stubPersonService;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private ProductService productService;
    @Mock
    private ReservationService reservationService;
    private Person personTest;

    @Before
    public void setup(){
        List<Product> fakePublishedList = new ArrayList<Product>(Arrays.asList(new Product(),new Product()));
        MockitoAnnotations.initMocks(this);
        when(personRepository.findAll()).thenReturn(new ArrayList<Person>(Arrays.asList(new Person(),new Person())));
        when(productService.getAll(any(Person.class))).thenReturn(fakePublishedList);
        personTest = new Person();
        personTest.setId(0);
        personTest.setUsername("test");
        personTest.setPassword("test");
    }

    @Test
    public void getAllPersonListTest(){
        List<Person> personList = stubPersonService.getAll();
        assertEquals(2,personList.size());
    }

    @Test
    public void getAllWithPersonListGetTheirRespectivePublishedListsFetchedTest(){
        List<Person> personList = stubPersonService.getAll();
        assertEquals(2,personList.get(0).getPublishedList().size());
        assertEquals(2,personList.get(1).getPublishedList().size());
    }

    @Test
    public void updateLastSeenTest(){
        Calendar calendar = new GregorianCalendar(2000,5,5);
        personTest.setLastSeen(calendar);
        stubPersonService.updatePersonLastSeen(personTest);
        assertNotEquals(calendar,personTest.getLastSeen());
    }

    @Test(expected = BadRequestException.class)
    public void denyPersonWithNonUniqueUsernameTest(){
        when(personRepository.findByUsername(any(String.class))).thenReturn(personTest);
        stubPersonService.createPerson(personTest);
    }

    @Test(expected = ForbiddenException.class)
    public void protectPersonWithSuperRolePropertyTest(){
        personTest.setRole(PersonRoles.SUPER);
        stubPersonService.createPerson(personTest);
    }


    @Test(expected = BadRequestException.class)
    public void protectPersonWithAssetsTest(){
        when(personRepository.findById(any(Integer.class))).thenReturn(Optional.of(personTest));
        stubPersonService.deletePerson(4);
    }

    @Test(expected = BadRequestException.class)
    public void protectPersonWithReservationsTest(){
        when(personRepository.findById(any(Integer.class))).thenReturn(Optional.of(personTest));
        when(reservationService.getAll(any(Person.class))).thenReturn(new ArrayList<Reservation>(Arrays.asList(new Reservation(),new Reservation())));
        personTest.setPublishedList(new ArrayList<>());
        stubPersonService.deletePerson(4);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getPersonNotFoundWithIdTest(){
        realPersonService.getPerson(0);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getPersonNotFoundWithUsernameTest(){
        realPersonService.getPerson("");
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updatePersonNotFoundTest(){
        realPersonService.updatePerson(personTest);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deletePersonNotFoundTest(){
        realPersonService.deletePerson(0);
    }
}