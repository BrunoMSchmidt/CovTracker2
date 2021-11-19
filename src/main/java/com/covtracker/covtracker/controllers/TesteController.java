package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Teste;
import com.covtracker.covtracker.repositories.TesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TesteController {

    @Autowired
    TesteRepository testeRepository;

    @GetMapping(path = "/testes")
    public List<Teste> listAll() {
        return this.testeRepository.findAll();
    }

    @GetMapping(path = "/teste/{id}")
    public ResponseEntity<Teste> getById(@PathVariable() Integer id) {
        return this.testeRepository.findById(id)
                .map(teste -> ResponseEntity.ok().body(teste))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/teste")
    public Teste create(@RequestBody Teste teste){
        return testeRepository.save(teste);
    }

    @PutMapping(path = "/teste/{id}")
    public ResponseEntity<Teste> update(@PathVariable() Integer id, @RequestBody Teste teste){
        return testeRepository.findById(id)
                .map(registro -> {
                    registro.setDataTeste(teste.getDataTeste());
                    registro.setResultado(teste.getResultado());
                    registro.setUsuario(teste.getUsuario());
                    Teste updatedTeste = testeRepository.save(registro);
                    return ResponseEntity.ok().body(updatedTeste);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/teste/{id}")
    public ResponseEntity delete(@PathVariable() Integer id){
        return testeRepository.findById(id)
                .map(registro -> {
                    testeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
