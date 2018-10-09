package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.Credential;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.PersonRoles;
import com.globant.bootcamp.dia15.domain.repository.PersonRepository;
import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import com.globant.bootcamp.dia15.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SecurityEndpointService {

    @Autowired
    private  PersonRepository personRepository;
    private static Map<String,Credential> tokenRepository = new HashMap<>();

    public SecurityEndpointService() {
    }

    public String signIn (Person person){
        Credential credentials = new Credential();
        credentials.setToken("");

        try {
            credentials.setPerson(personRepository.findByUsername(person.getUsername()));
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Invalid Username/Password fields");
        }

        credentials.setToken(generateToken());
        if (person.getPassword().equals(credentials.getPerson().getPassword())){
            if (tokenRepository.containsValue(credentials)){
                throw new UnauthorizedException("Already logged in the system");
            }
            tokenRepository.put(credentials.getToken(),credentials);
        }
        return credentials.getToken();
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
        Credential credentials = tokenRepository.get(token);
        if (credentials != null && credentials.getPerson().getRole().equals(role)){
            return credentials.getPerson();
        }else{
            throw new UnauthorizedException();
        }
    }

    public Person validateRequest(String token){
        Credential credentials = tokenRepository.get(token);
        if (credentials != null){
            return credentials.getPerson();
        }else{
            throw new UnauthorizedException();
        }
    }
}
