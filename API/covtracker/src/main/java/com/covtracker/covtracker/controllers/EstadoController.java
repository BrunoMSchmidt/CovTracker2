package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Estado;
import com.covtracker.covtracker.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class EstadoController {

    @Autowired
    EstadoRepository estadoRepository;

    @GetMapping(path = "/estados")
    public List<Estado> listAll() {
        return this.estadoRepository.findAll();
    }

    @GetMapping(path = "/estado/{sigla}")
    public ResponseEntity<Estado> getById(@PathVariable() String sigla) {
        return this.estadoRepository.findById(sigla)
                .map(estado -> ResponseEntity.ok().body(estado))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/estado")
    public Estado create(@RequestBody Estado estado){
        return estadoRepository.save(estado);
    }

    @PutMapping(path = "/estado/{sigla}")
    public ResponseEntity<Estado> update(@PathVariable() String sigla, @RequestBody Estado estado){
        return estadoRepository.findById(sigla)
                .map(registro -> {
                    registro.setNome(estado.getNome());
                    Estado updatedEstado = estadoRepository.save(registro);
                    return ResponseEntity.ok().body(updatedEstado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/estado/{sigla}")
    public ResponseEntity delete(@PathVariable() String sigla){
        return estadoRepository.findById(sigla)
                .map(registro -> {
                    estadoRepository.deleteById(sigla);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
