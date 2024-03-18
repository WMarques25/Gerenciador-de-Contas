package com.wmarques.contas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
public class ContasController {

    @GetMapping
    public String listar() {
        return "Listando contas";
    }
}
