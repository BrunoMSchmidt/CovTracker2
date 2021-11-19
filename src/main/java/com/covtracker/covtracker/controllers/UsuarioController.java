package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Usuario;
import com.covtracker.covtracker.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping(path = "/usuarios")
    public List<Usuario> listAll() {
        return this.usuarioRepository.findAll();
    }

    @GetMapping(path = "/usuario/{cpf}")
    public ResponseEntity<Usuario> getById(@PathVariable() BigInteger id) {
        return this.usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok().body(usuario))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/usuario")
    public Usuario create(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @PutMapping(path = "/usuario/{cpf}")
    public ResponseEntity<Usuario> update(@PathVariable() BigInteger id, @RequestBody Usuario usuario){
        return usuarioRepository.findById(id)
                .map(registro -> {
                    registro.setNome(usuario.getNome());
                    registro.setAltura(usuario.getAltura());
                    registro.setCpf(usuario.getCpf());
                    registro.setEmpresa(usuario.getEmpresa());
                    registro.setDataNascimento(usuario.getDataNascimento());
                    registro.setEndereco(usuario.getEndereco());
                    registro.setFuncaoEmpresa(usuario.getFuncaoEmpresa());
                    registro.setSexo(usuario.getSexo());
                    registro.setPeso(usuario.getPeso());
                    Usuario updatedUsuario = usuarioRepository.save(registro);
                    return ResponseEntity.ok().body(updatedUsuario);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/usuario/{cpf}")
    public ResponseEntity delete(@PathVariable() BigInteger id){
        return usuarioRepository.findById(id)
                .map(registro -> {
                    usuarioRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
