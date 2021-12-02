package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.TipoUsuario;
import com.covtracker.covtracker.repositories.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class TipoUsuarioController {

    @Autowired
    TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping(path = "/tiposUsuario")
    public List<TipoUsuario> listAll() {
        return this.tipoUsuarioRepository.findAll();
    }

    @GetMapping(path = "/tipoUsuario/{id}")
    public ResponseEntity<TipoUsuario> getById(@PathVariable() Integer id) {
        return this.tipoUsuarioRepository.findById(id)
                .map(tipoUsuario -> ResponseEntity.ok().body(tipoUsuario))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/tipoUsuario")
    public TipoUsuario create(@RequestBody TipoUsuario tipoUsuario){
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    @PutMapping(path = "/tipoUsuario/{id}")
    public ResponseEntity<TipoUsuario> update(@PathVariable() Integer id, @RequestBody TipoUsuario tipoUsuario){
        return tipoUsuarioRepository.findById(id)
                .map(registro -> {
                    registro.setNome(tipoUsuario.getNome());
                    TipoUsuario updatedTipoUsuario = tipoUsuarioRepository.save(registro);
                    return ResponseEntity.ok().body(updatedTipoUsuario);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/tipoUsuario/{id}")
    public ResponseEntity delete(@PathVariable() Integer id){
        return tipoUsuarioRepository.findById(id)
                .map(registro -> {
                    tipoUsuarioRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
