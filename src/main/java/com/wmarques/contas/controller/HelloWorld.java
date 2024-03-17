package com.wmarques.contas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {

    @GetMapping
    public String hello() {
        return "<h1 style=\"font-size: 40px;\">Hello, World!</h1>";
    }
}
