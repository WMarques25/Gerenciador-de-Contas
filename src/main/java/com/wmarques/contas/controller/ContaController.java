package com.wmarques.contas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wmarques.contas.domain.conta.ContaRepository;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaRepository repository;
    
    @GetMapping
    public String listar() {
        return "Listando contas";
    }
}
