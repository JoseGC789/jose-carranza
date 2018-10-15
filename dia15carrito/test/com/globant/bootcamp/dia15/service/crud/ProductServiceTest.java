package com.globant.bootcamp.dia15.service.crud;

import com.globant.bootcamp.dia15.constant.ProductState;
import com.globant.bootcamp.dia15.constant.ProductStock;
import com.globant.bootcamp.dia15.domain.entity.Category;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.domain.repository.ProductRepository;
import com.globant.bootcamp.dia15.exceptions.BadRequestException;
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
public class ProductServiceTest {
    @Autowired
    private ProductService realProductService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private PersonService personService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private ReservationService reservationService;
    @InjectMocks
    private ProductService stubProductService;
    private Product productTest;
    private Person personTest;
    private Category categoryTest;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        personTest = new Person();
        personTest.setId(0);
        personTest.setUsername("test");
        personTest.setPassword("test");
        productTest = new Product();
        productTest.setId(0);
        productTest.setName("test");
        productTest.setQuantity(50);
        productTest.setPrice(50);
        productTest.setState(ProductState.NEW);
        productTest.setStatus(ProductStock.AVAILABLE);
        productTest.setPublisher(personTest);
        productTest.setCategories(new ArrayList<>());
        categoryTest = new Category();
        categoryTest.setId(0);
        categoryTest.setName("test");
        categoryTest.setDescription("test");
        when(productRepository.save(any(Product.class))).thenReturn(productTest);
        when(productRepository.findById(any(Integer.class))).thenReturn(Optional.of(productTest));
        when(personService.getPerson(any(Integer.class))).thenReturn(personTest);
        when(categoryService.getCategory(any(Integer.class))).thenReturn(categoryTest);
        when(reservationService.getAll(any(Product.class))).thenReturn(new ArrayList<>(Arrays.asList(new Reservation(),new Reservation())));

    }



    @Test(expected = BadRequestException.class)
    public void createProductWithNullPublishedTest(){
        productTest.setPublisher(null);
        stubProductService.createProduct(productTest);
    }

    @Test(expected = BadRequestException.class)
    public void deleteProductShouldBeProtectedIfItHasReservationsTest(){
        stubProductService.deleteProduct(5);

    }

    @Test
    public void checkProductStateShouldBeAVAILABLETest(){
        productTest = stubProductService.createProduct(productTest);
        assertEquals(ProductStock.AVAILABLE,productTest.getStatus());
    }
    @Test
    public void checkProductStateShouldBeUNAVAILABLETest(){
        productTest.setQuantity(0);
        productTest = stubProductService.createProduct(productTest);
        assertEquals(ProductStock.UNAVAILABLE,productTest.getStatus());
    }

    @Test
    public void createProductWithoutCategoryShouldBeCategorized(){
        productTest = stubProductService.createProduct(productTest);
        assertEquals(categoryTest,productTest.getCategories().get(0));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void createProductWithNonExistentPublisherTest(){
        when(personService.getPerson(any(Integer.class))).thenThrow(new ResourceNotFoundException());
        stubProductService.createProduct(productTest);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void getProductNotFoundWithIdTest(){
        realProductService.getProduct(0);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateProductNotFoundTest(){
        realProductService.updateProduct(productTest);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteProductNotFoundTest(){
        realProductService.deleteProduct(0);
    }
}