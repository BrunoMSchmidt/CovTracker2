package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Endereco;
import com.covtracker.covtracker.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping(path = "/enderecos")
    public List<Endereco> listEnderecos() {
        return this.enderecoRepository.findAll();
    }

    @GetMapping(path = "/endereco/{id}")
    public ResponseEntity<Endereco> getById(@PathVariable() Integer id) {
        return this.enderecoRepository.findById(id)
                .map(empresa -> ResponseEntity.ok().body(empresa))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/endereco")
    public Endereco create(@RequestBody Endereco empresa){
        return enderecoRepository.save(empresa);
    }

    @PutMapping(path = "/endereco/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable() Integer id, @RequestBody Endereco endereco){
        return enderecoRepository.findById(id)
                .map(registro -> {
                    registro.setBairro(endereco.getBairro());
                    registro.setCidade(endereco.getCidade());
                    registro.setComplemento(endereco.getComplemento());
                    registro.setNumero(endereco.getNumero());
                    registro.setRua(endereco.getRua());
                    Endereco enderecoAtualizado = enderecoRepository.save(registro);
                    return ResponseEntity.ok().body(enderecoAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/endereco/{id}")
    public ResponseEntity deleteEndereco(@PathVariable() Integer id){
        return enderecoRepository.findById(id)
                .map(registro -> {
                    enderecoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
