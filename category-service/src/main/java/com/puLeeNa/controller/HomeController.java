package com.puLeeNa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String HomeControllerHandler() {
        return "category microservice is up and running";
    }
}
