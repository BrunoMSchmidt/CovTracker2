package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.dto.UsuarioVacinaDTO;
import com.covtracker.covtracker.entities.UsuarioVacina;
import com.covtracker.covtracker.repositories.UsuarioVacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UsuarioVacinaController {

    @Autowired
    private UsuarioVacinaRepository usuarioVacinaRepository;

    @GetMapping(path = "usuarioVacina/findByCpf/{cpf}")
    public List<UsuarioVacinaDTO> findByCpf(@PathVariable("cpf") BigDecimal cpf){
        List<UsuarioVacinaDTO> usuarioVacinaDTOS = new ArrayList<>();
        return (List<UsuarioVacinaDTO>) this.usuarioVacinaRepository.findAllByUsuarioCpf(cpf);
    }
}
