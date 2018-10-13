package com.globant.bootcamp.dia15.controller.internaut;

import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginLogoutPersonController {

    @RestController
    @RequestMapping("/login")
    public class LoginPersonController {
        @Autowired
        private SecurityEndpointService securityEndpointService;

        @PostMapping
        public ResponseEntity<String> loginPerson(@RequestBody Person person){
            return ResponseEntity.ok().body(securityEndpointService.signIn(person));
        }
    }

    @RestController
    @RequestMapping("/logout")
    public class LogoutPersonController {
        @Autowired
        private SecurityEndpointService securityEndpointService;

        @PostMapping
        public ResponseEntity<Person> logoutPerson(@RequestHeader("Authorization") String token){
            return ResponseEntity.ok().body(securityEndpointService.signOut(token));
        }
    }
}
