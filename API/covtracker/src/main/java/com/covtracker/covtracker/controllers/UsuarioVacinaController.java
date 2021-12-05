package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.dto.UsuarioVacinaDTO;
import com.covtracker.covtracker.entities.UsuarioVacina;
import com.covtracker.covtracker.repositories.UsuarioVacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UsuarioVacinaController {

    @Autowired
    private UsuarioVacinaRepository usuarioVacinaRepository;

    @GetMapping("usuario-vacina")
    public List<UsuarioVacina> findAll(){
        return this.usuarioVacinaRepository.findAll();
    }

    @GetMapping(path = "usuario-vacina/findByCpf/{cpf}")
    public List<UsuarioVacinaDTO> findAllByCpf(@PathVariable("cpf") BigDecimal cpf){
        List<UsuarioVacina> usuarioVacinas = this.usuarioVacinaRepository.findAllByUsuarioCpf(cpf);
        List<UsuarioVacinaDTO> usuarioVacinasDTO = new ArrayList<>();

        usuarioVacinas.forEach(usuarioVacina -> {
            UsuarioVacinaDTO usuarioVacinaDTO = new UsuarioVacinaDTO();
            usuarioVacinaDTO.setId(usuarioVacina.getId());
            usuarioVacinaDTO.setCpfUsuario(usuarioVacina.getUsuario().getCpf());
            usuarioVacinaDTO.setIdVacina(usuarioVacina.getVacina().getId());
            usuarioVacinaDTO.setData(usuarioVacina.getData());
            usuarioVacinaDTO.setDose(usuarioVacina.getDose());
            usuarioVacinasDTO.add(usuarioVacinaDTO);
        });
        return usuarioVacinasDTO;
    }

    @PostMapping(path = "usuario-vacina")
    public UsuarioVacina create(@RequestBody UsuarioVacina usuarioVacina){
        return this.usuarioVacinaRepository.save(usuarioVacina);
    }

    @DeleteMapping(path = "usuario-vacina/{id}")
    public void deleteById(@PathVariable Integer id){
        this.usuarioVacinaRepository.deleteById(id);
    }
}
