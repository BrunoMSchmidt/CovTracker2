package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Empresa;
import com.covtracker.covtracker.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController()
public class EmpresaController {

    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping(path = "/empresas")
    public List<Empresa> listEmpresas() {
        return this.empresaRepository.findAll();
    }

    @GetMapping(path = "/empresa/{cnpj}")
    public ResponseEntity<Empresa> getEmpresaByCnpj(@PathVariable() BigDecimal cnpj) {
        return this.empresaRepository.findById(cnpj)
                .map(empresa -> ResponseEntity.ok().body(empresa))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/empresa")
    public Empresa create(@RequestBody Empresa empresa){
        return empresaRepository.save(empresa);
    }

    @PutMapping(path = "/empresa/{cnpj}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable() BigDecimal cnpj, @RequestBody Empresa empresa){
        return empresaRepository.findById(cnpj)
                .map(registro -> {
                    registro.setEmail(empresa.getEmail());
                    registro.setAreaDeAtuacao(empresa.getAreaDeAtuacao());
                    registro.setEndereco(empresa.getEndereco());
                    registro.setRazaoSocial(empresa.getRazaoSocial());
                    registro.setTelefone(empresa.getTelefone());
                    registro.setNomeFantasia(empresa.getNomeFantasia());
                    Empresa empresaAtualizada = empresaRepository.save(registro);
                    return ResponseEntity.ok().body(empresaAtualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/empresa/{cnpj}")
    public ResponseEntity deleteEmpresa(@PathVariable() BigDecimal cnpj){
        return empresaRepository.findById(cnpj)
                .map(registro -> {
                    empresaRepository.deleteById(cnpj);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
