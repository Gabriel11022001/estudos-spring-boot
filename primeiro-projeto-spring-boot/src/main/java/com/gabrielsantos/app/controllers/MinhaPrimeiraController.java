package com.gabrielsantos.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
public class MinhaPrimeiraController {

    @GetMapping(value = "/ola-mundo")
    public String olaMundo() {
        return "Olá Mundo!";
    }
    @GetMapping(value = "/listar-nomes-pessoas")
    public List<String> listarNomesPessoas() {
        return Arrays.asList("Gabriel", "Maria", "Pedro", "Gustavo", "Maria Eduarda");
    }
}
