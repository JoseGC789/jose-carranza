package com.globant.bootcamp.dia15.service.crud;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.domain.repository.ReservationRepository;
import com.globant.bootcamp.dia15.exceptions.BadRequestException;
import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;
    @Mock
    private ReservationRepository reservationRepository;
    @InjectMocks
    private ReservationService stubReservationService;
    private Reservation reservationTest;

    @Before
    public void setup(){
        reservationTest = new Reservation();
        reservationTest.setId(0);
        reservationTest.setProduct(new Product());
        reservationTest.setQuantity(2);
        reservationTest.setPerson(new Person());
    }

    @Test(expected = BadRequestException.class)
    public void reservationCantHaveCeroQuantityTest(){
        reservationTest.setQuantity(0);
        stubReservationService.createReservation(reservationTest);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getReservationNotFoundWithIdTest(){
        reservationService.getReservation(0);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateReservationNotFoundTest(){
        reservationService.updateReservation(reservationTest);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteReservationNotFoundTest(){
        reservationService.deleteReservation(0);
    }

}