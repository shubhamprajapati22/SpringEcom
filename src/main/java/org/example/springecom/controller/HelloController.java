package org.example.springecom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/admin/home")
    public String home(){

        return "Shubham";

    }
}
