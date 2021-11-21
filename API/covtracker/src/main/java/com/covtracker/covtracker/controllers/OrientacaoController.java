package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Orientacao;
import com.covtracker.covtracker.repositories.OrientacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class OrientacaoController {

    @Autowired
    OrientacaoRepository orientacaoRepository;

    @GetMapping(path = "/orientacoes")
    public List<Orientacao> listAll() {
        return this.orientacaoRepository.findAll();
    }

    @GetMapping(path = "/orientacao/{id}")
    public ResponseEntity<Orientacao> getById(@PathVariable() Integer id) {
        return this.orientacaoRepository.findById(id)
                .map(orientacao -> ResponseEntity.ok().body(orientacao))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/orientacao")
    public Orientacao create(@RequestBody Orientacao orientacao){
        return orientacaoRepository.save(orientacao);
    }

    @PutMapping(path = "/orientacao/{id}")
    public ResponseEntity<Orientacao> update(@PathVariable() Integer id, @RequestBody Orientacao orientacao){
        return orientacaoRepository.findById(id)
                .map(registro -> {
                    registro.setDescricao(orientacao.getDescricao());
                    Orientacao updatedOrientacao = orientacaoRepository.save(registro);
                    return ResponseEntity.ok().body(updatedOrientacao);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/orientacao/{id}")
    public ResponseEntity delete(@PathVariable() Integer id){
        return orientacaoRepository.findById(id)
                .map(registro -> {
                    orientacaoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
