package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Comorbidade;
import com.covtracker.covtracker.repositories.ComorbidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ComorbidadeController {
    @Autowired
    ComorbidadeRepository comorbidadeRepository;

    @GetMapping(path = "/comorbidades")
    public List<Comorbidade> listAll() {
        return this.comorbidadeRepository.findAll();
    }

    @GetMapping(path = "/comorbidade/{id}")
    public ResponseEntity<Comorbidade> getById(@PathVariable() Integer id) {
        return this.comorbidadeRepository.findById(id)
                .map(comorbidade -> ResponseEntity.ok().body(comorbidade))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/comorbidade")
    public Comorbidade create(@RequestBody Comorbidade comorbidade){
        return comorbidadeRepository.save(comorbidade);
    }

    @PutMapping(path = "/comorbidade/{id}")
    public ResponseEntity<Comorbidade> update(@PathVariable() Integer id, @RequestBody Comorbidade comorbidade){
        return comorbidadeRepository.findById(id)
                .map(registro -> {
                    registro.setNomcom(comorbidade.getNomcom());
                    Comorbidade updatedComorbidade = comorbidadeRepository.save(registro);
                    return ResponseEntity.ok().body(updatedComorbidade);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/comorbidade/{id}")
    public ResponseEntity delete(@PathVariable() Integer id){
        return comorbidadeRepository.findById(id)
                .map(registro -> {
                    comorbidadeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
