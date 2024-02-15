package com.nakahama.simpenbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class main {

    @GetMapping("/")
    public String getJson() {
        return "{\"message\": \"Hello, World!\"}";
    }

}
