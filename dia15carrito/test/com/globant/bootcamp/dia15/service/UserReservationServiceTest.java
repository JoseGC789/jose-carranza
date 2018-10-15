package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.exceptions.BadRequestException;
import com.globant.bootcamp.dia15.exceptions.ForbiddenException;
import com.globant.bootcamp.dia15.service.crud.ProductService;
import com.globant.bootcamp.dia15.service.crud.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class UserReservationServiceTest {
    @Mock
    private ReservationService reservationService;
    @Mock
    private ProductService productService;
    @InjectMocks
    private UserReservationService userReservationService;
    private Reservation reservation= new Reservation();
    private Person person= new Person();
    private Person person2= new Person();
    private Product product= new Product();


    @Before
    public void SetUp(){
        person.setId(0);
        person.setUsername("test");
        person2.setId(1);
        person2.setUsername("test2");
        product.setId(1);
        product.setQuantity(100);
        product.setPublisher(person2);
        reservation.setQuantity(50);
        reservation.setId(1);
        reservation.setProduct(product);
        reservation.setPerson(person2);
        when(productService.getProduct(any(Integer.class))).thenReturn(product);
        when(productService.updateProduct(any(Product.class))).thenReturn(product);
        when(reservationService.createReservation(any(Reservation.class))).thenReturn(reservation);
        when(reservationService.getReservation(any(Integer.class))).thenReturn(reservation);
    }

    @Test
    public void createReservationShouldSubtractQuantityFromProductTest(){
        userReservationService.createReservation(person2,1,50);
        assertEquals(50, product.getQuantity());
    }

    @Test(expected = BadRequestException.class)
    public void createReservationWithoutEnoughStockInProductTest(){
        userReservationService.createReservation(person2,1,101);
    }

    @Test(expected = ForbiddenException.class)
    public void closeReservationThatPersonDoesNotOwnTest(){
        userReservationService.closeReservation(person,1);
    }

    @Test(expected = ForbiddenException.class)
    public void getReservationOfProductThatPersonDoesNotOwnTest(){
        userReservationService.getProductReservations(person,1);
    }


}