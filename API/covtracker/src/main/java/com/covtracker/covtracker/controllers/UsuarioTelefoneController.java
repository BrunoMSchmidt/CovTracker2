package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.UsuarioTelefone;
import com.covtracker.covtracker.repositories.UsuarioTelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/api")
public class UsuarioTelefoneController {

    @Autowired
    private UsuarioTelefoneRepository usuarioTelefoneRepository;

    @GetMapping(path = "/usuariosTelefones")
    public List<UsuarioTelefone> findALl(){
        return usuarioTelefoneRepository.findAll();
    }

    @PostMapping(path = "/usuariosTelefones")
    public List<UsuarioTelefone> saveAll(@RequestBody List<UsuarioTelefone> usuariosTelefones){
        return usuarioTelefoneRepository.saveAll(usuariosTelefones);
    }


}
