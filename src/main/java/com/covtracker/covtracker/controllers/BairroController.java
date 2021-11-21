package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Bairro;
import com.covtracker.covtracker.entities.Endereco;
import com.covtracker.covtracker.repositories.BairroRepository;
import com.covtracker.covtracker.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class BairroController {
    @Autowired
    BairroRepository bairroRepository;

    @GetMapping(path = "/bairros")
    public List<Bairro> listAll() {
        return this.bairroRepository.findAll();
    }

    @GetMapping(path = "/bairro/{id}")
    public ResponseEntity<Bairro> getById(@PathVariable() Integer id) {
        return this.bairroRepository.findById(id)
                .map(bairro -> ResponseEntity.ok().body(bairro))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/bairro")
    public Bairro create(@RequestBody Bairro bairro){
        return bairroRepository.save(bairro);
    }

    @PutMapping(path = "/bairro/{id}")
    public ResponseEntity<Bairro> update(@PathVariable() Integer id, @RequestBody Bairro bairro){
        return bairroRepository.findById(id)
                .map(registro -> {
                    registro.setCep(bairro.getCep());
                    registro.setNome(bairro.getNome());
                    Bairro bairroAtualizado = bairroRepository.save(registro);
                    return ResponseEntity.ok().body(bairroAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/bairro/{id}")
    public ResponseEntity delete(@PathVariable() Integer id){
        return bairroRepository.findById(id)
                .map(registro -> {
                    bairroRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
