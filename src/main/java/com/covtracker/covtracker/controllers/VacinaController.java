package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Vacina;
import com.covtracker.covtracker.repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VacinaController {

    @Autowired
    VacinaRepository vacinaRepository;

    @GetMapping(path = "/vacinas")
    public List<Vacina> listAll() {
        return this.vacinaRepository.findAll();
    }

    @GetMapping(path = "/vacina/{id}")
    public ResponseEntity<Vacina> getById(@PathVariable() Integer id) {
        return this.vacinaRepository.findById(id)
                .map(vacina -> ResponseEntity.ok().body(vacina))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/vacina")
    public Vacina create(@RequestBody Vacina vacina){
        return vacinaRepository.save(vacina);
    }

    @PutMapping(path = "/vacina/{id}")
    public ResponseEntity<Vacina> update(@PathVariable() Integer id, @RequestBody Vacina vacina){
        return vacinaRepository.findById(id)
                .map(registro -> {
                    registro.setNomvac(vacina.getNomvac());
                    Vacina updatedVacina = vacinaRepository.save(registro);
                    return ResponseEntity.ok().body(updatedVacina);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/vacina/{id}")
    public ResponseEntity delete(@PathVariable() Integer id){
        return vacinaRepository.findById(id)
                .map(registro -> {
                    vacinaRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
