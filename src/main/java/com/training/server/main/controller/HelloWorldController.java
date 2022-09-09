package com.training.server.main.controller;

import com.training.server.main.model.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
    //using get method and hello-world as URI
    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    //using get method and hello-world as URI
    @GetMapping(path = "/hello-world1")
    public String helloWorld1() {
        return "Hello World1";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World"); //constructor of HelloWorldBean
    }
}