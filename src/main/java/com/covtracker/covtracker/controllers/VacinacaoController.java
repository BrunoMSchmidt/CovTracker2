package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Vacinacao;
import com.covtracker.covtracker.repositories.VacinacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class VacinacaoController {

    @Autowired
    VacinacaoRepository vacinacaoRepository;

    @GetMapping(path = "/vacinacoes")
    public List<Vacinacao> listAll() {
        return this.vacinacaoRepository.findAll();
    }

    @GetMapping(path = "/vacinacao/{id}")
    public ResponseEntity<Vacinacao> getById(@PathVariable() Integer id) {
        return this.vacinacaoRepository.findById(id)
                .map(vacinacao -> ResponseEntity.ok().body(vacinacao))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/vacinacao")
    public Vacinacao create(@RequestBody Vacinacao vacinacao){
        return vacinacaoRepository.save(vacinacao);
    }

    @PutMapping(path = "/vacinacao/{id}")
    public ResponseEntity<Vacinacao> update(@PathVariable() Integer id, @RequestBody Vacinacao vacinacao){
        return vacinacaoRepository.findById(id)
                .map(registro -> {
                    registro.setVacina(vacinacao.getVacina());
                    registro.setData(vacinacao.getData());
                    registro.setDose(vacinacao.getDose());
                    registro.setUsuario(vacinacao.getUsuario());
                    Vacinacao updatedVacinacao = vacinacaoRepository.save(registro);
                    return ResponseEntity.ok().body(updatedVacinacao);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/vacinacao/{id}")
    public ResponseEntity delete(@PathVariable() Integer id){
        return vacinacaoRepository.findById(id)
                .map(registro -> {
                    vacinacaoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
