package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class UserRegisterServiceTest {
    @Mock
    private PersonService personService;
    @InjectMocks
    private UserRegisterService userRegisterService;
    private Person registree;

    @Before
    public void SetUp(){
        registree = new Person();
        registree.setId(0);
        registree.setUsername("asd");
        registree.setRole(PersonRoles.ADMIN);
        when(personService.createPerson(any(Person.class))).thenReturn(registree);
    }

    @Test
    public void registerPersonTest(){
        userRegisterService.registerPerson(registree);
        verify(personService,times(1)).createPerson(any(Person.class));
    }

    @Test
    public void registerPersonShouldChangeRoleToUserTest(){
        assertEquals(PersonRoles.USER,userRegisterService.registerPerson(registree).getRole());
    }

}