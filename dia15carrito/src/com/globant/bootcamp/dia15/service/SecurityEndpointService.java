package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.PersonRoles;
import com.globant.bootcamp.dia15.exceptions.UnauthorizedException;
import com.globant.bootcamp.dia15.service.crud.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
            throw new UnauthorizedException("Already logged in the system");
        }else {
            if (person.getPassword().equals(password)) {
                token = generateToken();
                tokenRepository.put(token, person);
            } else {
                throw new UnauthorizedException("Invalid Username/Password fields");
            }
        }
        updateLastSeen(person);
        return token;
    }

    private String generateToken() {
        String str = "abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 30; i++){
            stringBuilder.append(str.charAt(rnd.nextInt(str.length())));
        }
        return stringBuilder.toString();
    }

    private void iterate(){

    }

    public Person validateRequest(String token, PersonRoles role){
        Person person = tokenRepository.get(token);
        if (person != null && person.getRole().equals(role)){
            updateLastSeen(person);
            return person;
        }else{
            throw new UnauthorizedException("Cannot validate token");
        }
    }

    public Person validateRequest(String token){
        Person person = tokenRepository.get(token);
        if (person != null){
            updateLastSeen(person);
            return person;
        }else{
            throw new UnauthorizedException("Cannot validate token");
        }
    }

    private void updateLastSeen (Person person){
        person.setLastSeen(new GregorianCalendar());
        personService.updatePerson(person);
    }
}
