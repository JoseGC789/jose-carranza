package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.exceptions.ForbiddenException;
import com.globant.bootcamp.dia15.service.crud.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class UserProductServiceTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private UserProductService userProductService;
    private Person publisher;
    private Person otherPublisher;
    private Product incorrectProduct;

    @Before
    public void SetUp(){
        publisher = new Person();
        publisher.setId(0);
        publisher.setUsername("test");
        otherPublisher = new Person();
        otherPublisher.setId(1);
        otherPublisher.setUsername("test2");

        incorrectProduct = new Product();
        incorrectProduct.setPublisher(otherPublisher);
        when(productService.getProduct(any(Integer.class))).thenReturn(incorrectProduct);
    }

    @Test(expected = ForbiddenException.class)
    public void withdrawProductShouldThrowWhenPersonIsNotOwnerOfProductTest(){
        //same for modify
        userProductService.withdrawProduct(incorrectProduct,publisher);
    }
}