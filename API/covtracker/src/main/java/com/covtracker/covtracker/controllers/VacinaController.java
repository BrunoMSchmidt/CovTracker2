package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Vacina;
import com.covtracker.covtracker.repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VacinaController {

    @Autowired
    VacinaRepository vacinaRepository;

    @GetMapping(path = "vacinas")
    public List<Vacina> listAll(){
        return vacinaRepository.findAll();
    }
}
