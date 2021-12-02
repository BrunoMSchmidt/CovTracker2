package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.entities.Usuario;
import com.covtracker.covtracker.repositories.UsuarioRepository;
import com.covtracker.covtracker.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController()
@RequestMapping(path = "/api")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CustomUserDetailsService usersDetailService;

    @GetMapping(path = "/usuarios")
    public List<Usuario> listAll() {
        return this.usuarioRepository.findAll();
    }

    @GetMapping(path = "/usuario/{cpf}")
    public ResponseEntity<Usuario> getByCpf(@PathVariable() BigDecimal cpf) {
        return this.usuarioRepository.findById(cpf)
                .map(usuario -> ResponseEntity.ok().body(usuario))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/usuarios/findByName/{name}")
    public List<Usuario> getByCpf(@PathVariable() String name) {
        return this.usuarioRepository.findByNomeContainingIgnoreCase(name);
    }

    @PostMapping(path = "/usuario")
    public Usuario create(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @PutMapping(path = "/usuario/{cpf}")
    public ResponseEntity<Usuario> update(@PathVariable() BigDecimal cpf, @RequestBody Usuario usuario){
        return usuarioRepository.findById(cpf)
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
    public ResponseEntity delete(@PathVariable() BigDecimal cpf){
        return usuarioRepository.findById(cpf)
                .map(registro -> {
                    usuarioRepository.deleteById(cpf);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
