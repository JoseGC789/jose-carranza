package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.constants.ExceptionMessages;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.constants.Values;
import com.globant.bootcamp.dia15.constants.PersonRoles;
import com.globant.bootcamp.dia15.exceptions.BadRequestException;
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
        String token;
        person = personService.getPerson(person.getUsername());

        if (tokenRepository.containsValue(person)){
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_PERSON_ALREADY_LOGGED_IN.getString());
        }else {
            if (person.getPassword().equals(password)) {
                token = generateToken();
                tokenRepository.put(token, person);
            } else {
                throw new BadRequestException(ExceptionMessages.BAD_REQUEST_PERSON_CHECK_YOUR_DATA.getString());
            }
        }
        personService.updatePersonLastSeen(person);
        return token;
    }

    public Person signOut(String token){
        if (token.equals(Values.SECURITY_TOKEN_SUPER_TOKEN.getString())){
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_CANNOT_LOGOUT_SUPER.getString());
        }
        return tokenRepository.remove(token);
    }

    private String generateToken() {
        String str = Values.SECURITY_TOKEN_INPUT.getString();
        Random rnd = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Values.SECURITY_TOKEN_LENGTH.getNumber(); i++){
            stringBuilder.append(str.charAt(rnd.nextInt(str.length())));
        }
        return stringBuilder.toString();
    }

    private void iterate(){

    }

    public Person validateRequest(String token, List<PersonRoles> roles){
        Person person = tokenRepository.get(token);
        if (person != null && roles.contains(person.getRole())){
            personService.updatePersonLastSeen(person);
            return person;
        }else{
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_INSUFFICIENT_CLEARANCE.getString());
        }
    }

    public Person validateRequest(String token, PersonRoles role){
        Person person = tokenRepository.get(token);
        if (person != null && person.getRole().equals(role)){
            personService.updatePersonLastSeen(person);
            return person;
        }else{
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_INSUFFICIENT_CLEARANCE.getString());
        }
    }

    public Person validateRequest(String token){
        Person person = tokenRepository.get(token);
        if (person != null){
            personService.updatePersonLastSeen(person);
            return person;
        }else{
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_UNMATCHED_TOKEN.getString());
        }
    }

    public static void initializeSUPER(Person superAdmin){
        if (!tokenRepository.containsValue(superAdmin)) {
            tokenRepository.put(Values.SECURITY_TOKEN_SUPER_TOKEN.getString(), superAdmin);
        }
    }
}
