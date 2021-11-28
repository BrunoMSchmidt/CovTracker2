package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.UsuarioSintoma;
import com.covtracker.covtracker.repositories.UsuarioSintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UsuarioSintomaController {

    @Autowired
    private UsuarioSintomaRepository usuarioSintomaRepository;

    @GetMapping(path = "/usuario-sintoma")
    public List<UsuarioSintoma> listAll() {
        return this.usuarioSintomaRepository.findAll();
    }

    @PostMapping(path = "/usuario-sintoma",consumes={"application/json"})
    public UsuarioSintoma save(@RequestBody UsuarioSintoma usuarioSintoma){
        return usuarioSintomaRepository.save(usuarioSintoma);
    }

    @PutMapping(path = "/usuario-sintoma")
    public UsuarioSintoma update(@RequestBody UsuarioSintoma usuarioSintoma){
        return usuarioSintomaRepository.save(usuarioSintoma);
    }

    @GetMapping(path = "/usuario-sintoma/findByCpf/{cpf}")
    public List<UsuarioSintoma> listAllByCpf(@PathVariable BigDecimal cpf) {
        return this.usuarioSintomaRepository.findAllById_Cpfusu(cpf);
    }

}
