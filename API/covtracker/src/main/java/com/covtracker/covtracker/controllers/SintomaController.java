package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Sintoma;
import com.covtracker.covtracker.repositories.SintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SintomaController {

    @Autowired
    SintomaRepository sintomaRepository;

    @GetMapping(path = "/sintomas")
    public List<Sintoma> listAll() {
        return this.sintomaRepository.findAll();
    }

    @GetMapping(path = "/sintoma/{id}")
    public ResponseEntity<Sintoma> getById(@PathVariable() Integer id) {
        return this.sintomaRepository.findById(id)
                .map(sintoma -> ResponseEntity.ok().body(sintoma))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/sintoma")
    public Sintoma create(@RequestBody Sintoma sintoma){
        return sintomaRepository.save(sintoma);
    }

    @PutMapping(path = "/sintoma/{id}")
    public ResponseEntity<Sintoma> update(@PathVariable() Integer id, @RequestBody Sintoma sintoma){
        return sintomaRepository.findById(id)
                .map(registro -> {
                    registro.setCriticidade(sintoma.getCriticidade());
                    registro.setNome(sintoma.getNome());
                    Sintoma updatedSintoma = sintomaRepository.save(registro);
                    return ResponseEntity.ok().body(updatedSintoma);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/sintoma/{id}")
    public ResponseEntity delete(@PathVariable() Integer id){
        return sintomaRepository.findById(id)
                .map(registro -> {
                    sintomaRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
