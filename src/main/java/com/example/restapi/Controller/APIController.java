package com.example.restapi.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.Entity.Request;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class APIController {
    @PostMapping("/add")
    public String postMethodName(@RequestBody Request entity) {
        //TODO: process POST request
        int a = entity.getFirstNumber()+entity.getSecondNumber();
        return String.valueOf(a);
    }
    
}
