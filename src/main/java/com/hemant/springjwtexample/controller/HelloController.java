package com.hemant.springjwtexample.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class HelloController {

    //localhost:8181/hello
    //localhost:8181/greeting
    @GetMapping({"/hello","/greeting"})
    public String getGreeting(){
        log.info("getGreeting() Executed");
        return "Hello How are you";
    }
}
