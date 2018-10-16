package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.constant.Values;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.exceptions.ForbiddenException;
import com.globant.bootcamp.dia15.exceptions.UnauthorizedException;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class SecurityEndpointServiceTest {
    @Mock
    private PersonService personService;
    @InjectMocks
    private SecurityEndpointService securityEndpointService;
    private Person personTest;
    private Person personTest2;
    private List<PersonRoles> personRoles = Arrays.asList(PersonRoles.ADMIN,PersonRoles.SUPER);

    @Before
    public void SetUp(){
        personTest = new Person();
        personTest.setId(0);
        personTest.setUsername("test");
        personTest.setPassword("test");
        personTest.setRole(PersonRoles.USER);
        personTest2 = new Person();
        personTest2.setId(0);
        personTest2.setUsername("test");
        personTest2.setPassword("test2");
        when(personService.getPerson(any(String.class))).thenReturn(personTest);
        when(personService.updatePersonLastSeen(any(Person.class))).thenReturn(personTest);
    }

    @Test(expected = ForbiddenException.class)
    public void signOutShouldProtectSUPERAdminTest(){
        securityEndpointService.signOut(Values.SECURITY_TOKEN_SUPER_USER_TOKEN.getString());
    }

    @Test(expected = UnauthorizedException.class)
    public void signInWithInvalidCredentialPasswordFieldTest(){
        when(personService.getPerson(any(String.class))).thenReturn(personTest2);
        securityEndpointService.signIn(personTest);
    }

    @Test(expected = ForbiddenException.class)
    public void signInWithAlreadyLoggedUserTest(){
        securityEndpointService.signIn(personTest);
        securityEndpointService.signIn(personTest);
    }

    @Test
    public void validateRequestShouldValidateCorrectTokenTest(){
        String token = securityEndpointService.signIn(personTest);
        assertEquals(personTest,securityEndpointService.validateRequest(token));
    }

    @Test(expected = UnauthorizedException.class)
    public void validateRequestShouldInvalidateIncorrectTokenTest(){
        String token = securityEndpointService.signIn(personTest);
        securityEndpointService.validateRequest(token+"asdas");
    }

    @Test(expected = ForbiddenException.class)
    public void validateRequestShouldInvalidateIncorrectRoleTest(){
        String token = securityEndpointService.signIn(personTest);
        securityEndpointService.validateRequest(token,PersonRoles.ADMIN);
    }

    @Test(expected = ForbiddenException.class)
    public void validateRequestShouldInvalidateIncorrectRoleRangeTest(){
        String token = securityEndpointService.signIn(personTest);
        securityEndpointService.validateRequest(token,personRoles);
    }

}