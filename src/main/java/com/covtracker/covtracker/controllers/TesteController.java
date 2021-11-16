package com.covtracker.covtracker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TesteController {

    @RequestMapping(value = "/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String saudacoes(@PathVariable String nome) {
        return "Hello " + nome + "!";
    }
}
