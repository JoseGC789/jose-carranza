package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.constant.ExceptionMessages;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.constant.Values;
import com.globant.bootcamp.dia15.constant.PersonRoles;
import com.globant.bootcamp.dia15.exceptions.ForbiddenException;
import com.globant.bootcamp.dia15.exceptions.UnauthorizedException;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SecurityEndpointService {

    @Autowired
    private PersonService personService;
    private static Map<String,Person> tokenRepository = new HashMap<>();

    public SecurityEndpointService() {
    }

    public String signIn (Person person){
        String password = person.getPassword();
        person = personService.getPerson(person.getUsername());
        return authorizeUser(person,password);
    }

    public Person signOut(String token){
        if (token.equals(Values.SECURITY_TOKEN_SUPER_USER_TOKEN.getString())){
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_CANNOT_LOGOUT_SUPER.getString());
        }
        return tokenRepository.remove(token);
    }

    public Person validateRequest(String token, List<PersonRoles> roles){
        Person person = tokenRepository.get(token);
        personIsNotNull(person);
        if (roles.contains(person.getRole())){
            personService.updatePersonLastSeen(person);
            return person;
        }else{
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_INSUFFICIENT_CLEARANCE.getString());
        }
    }

    public Person validateRequest(String token, PersonRoles role){
        Person person = tokenRepository.get(token);
        personIsNotNull(person);
        if (person.getRole().equals(role)){
            personService.updatePersonLastSeen(person);
            return person;
        }else{
            throw new ForbiddenException(
                    ExceptionMessages.FORBIDDEN_ACCESS_ROLE_RESTRICTED_PART1.getString()
                    + role.toString().toLowerCase() +
                    ExceptionMessages.FORBIDDEN_ACCESS_ROLE_RESTRICTED_PART2.getString());
        }
    }

    public Person validateRequest(String token){
        Person person = tokenRepository.get(token);
        personIsNotNull(person);
        return personService.updatePersonLastSeen(person);
    }

    public static void initializeSUPER(Person superAdmin){
        if (!tokenRepository.containsValue(superAdmin)) {
            tokenRepository.put(Values.SECURITY_TOKEN_SUPER_USER_TOKEN.getString(), superAdmin);
        }
    }

    private void protectSuperRole(PersonRoles role){
        if (role == PersonRoles.SUPER){
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_MANIPULATION_OF_SUPER.getString());
        }
    }

    private String generateToken() {
        String str = Values.SECURITY_TOKEN_VALUES.getString();
        Random rnd = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Values.SECURITY_TOKEN_LENGTH.getNumber(); i++){
            stringBuilder.append(str.charAt(rnd.nextInt(str.length())));
        }
        return stringBuilder.toString();
    }

    private String authorizeUser(Person person, String password) {
        String token;
        if (tokenRepository.containsValue(person)){
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_PERSON_ALREADY_LOGGED_IN.getString());
        }else {
            if (person.getPassword().equals(password)) {
                token = generateToken();
                tokenRepository.put(token, person);
            } else {
                throw new UnauthorizedException(ExceptionMessages.UNAUTHORIZED_AUTHORIZATION_FAILURE.getString());
            }
        }
        personService.updatePersonLastSeen(person);
        return token;
    }

    private void personIsNotNull(Person person){
        if (person == null){
            throw new UnauthorizedException(ExceptionMessages.UNAUTHORIZED_CREDENTIALS_REQUIRED.getString());
        }

    }

}
