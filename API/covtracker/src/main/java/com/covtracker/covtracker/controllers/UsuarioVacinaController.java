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

    @GetMapping(path = "usuario-vacina/findByCpf/{cpf}")
    public List<UsuarioVacinaDTO> findAllByCpf(@PathVariable("cpf") BigDecimal cpf){
        List<UsuarioVacina> usuarioVacinas = this.usuarioVacinaRepository.findAllByUsuarioCpf(cpf);
        List<UsuarioVacinaDTO> usuarioVacinasDTO = new ArrayList<>();

        usuarioVacinas.forEach(usuarioVacina -> {
            UsuarioVacinaDTO usuarioVacinaDTO = new UsuarioVacinaDTO();
            usuarioVacinaDTO.setCpfUsuario(usuarioVacina.getUsuario().getCpf());
            usuarioVacinaDTO.setIdVacina(usuarioVacina.getId());
            usuarioVacinaDTO.setDatusuvac(usuarioVacina.getDatusuvac());
            usuarioVacinaDTO.setDosusuvac(usuarioVacina.getDosusuvac());
            usuarioVacinasDTO.add(usuarioVacinaDTO);
        });
        return usuarioVacinasDTO;
    }
}
