package com.github.bmsantos.spring.test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @RequestMapping("/helloWorld")
    public @ResponseBody String helloWorld() {
        return "Hello World!";
    }
}