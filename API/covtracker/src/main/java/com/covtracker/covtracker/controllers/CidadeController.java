package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Cidade;
import com.covtracker.covtracker.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CidadeController {

    @Autowired
    CidadeRepository cidadeRepository;

    @GetMapping(path = "/cidades")
    public List<Cidade> listAll() {
        return this.cidadeRepository.findAll();
    }

    @GetMapping(path = "/cidade/{id}")
    public ResponseEntity<Cidade> getById(@PathVariable() Integer id) {
        return this.cidadeRepository.findById(id)
                .map(cidade -> ResponseEntity.ok().body(cidade))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/cidadeByEstado/{sigla}")
    public Cidade[] getByEstado(@PathVariable String sigla){
        return this.cidadeRepository.findByEstadoSigla(sigla.toUpperCase());
    }

    @PostMapping(path = "/cidade")
    public Cidade create(@RequestBody Cidade cidade){
        return cidadeRepository.save(cidade);
    }

    @PutMapping(path = "/cidade/{id}")
    public ResponseEntity<Cidade> update(@PathVariable() Integer id, @RequestBody Cidade cidade){
        return cidadeRepository.findById(id)
                .map(registro -> {
                    registro.setNome(cidade.getNome());
                    registro.setEstado(cidade.getEstado());
                    Cidade cidadeAtualizado = cidadeRepository.save(registro);
                    return ResponseEntity.ok().body(cidadeAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/cidade/{id}")
    public ResponseEntity delete(@PathVariable() Integer id){
        return cidadeRepository.findById(id)
                .map(registro -> {
                    cidadeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
