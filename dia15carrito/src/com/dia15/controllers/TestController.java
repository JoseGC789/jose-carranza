package com.dia15.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TestController {
    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping("/")
    public String getHello(){
        return "Hello Spring boot! asd";
    }


}
