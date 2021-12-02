package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.dto.UsuarioComorbidadeDTO;
import com.covtracker.covtracker.dto.UsuarioSintomaDTO;
import com.covtracker.covtracker.entities.UsuarioComorbidade;
import com.covtracker.covtracker.repositories.UsuarioComorbidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UsuarioComorbidadeController {

    @Autowired
    UsuarioComorbidadeRepository usuarioComorbidadeRepository;

    @GetMapping(path = "usuario-comorbidade/findByCpf/{cpf}")
    public List<UsuarioComorbidadeDTO> findAllByUsuarioCpf(@PathVariable BigDecimal cpf){
        List<UsuarioComorbidade> usuarioComorbidadesList = this.usuarioComorbidadeRepository.findAllById_Cpfusu(cpf);
        List<UsuarioComorbidadeDTO> usuarioComorbidadeDTOList = new ArrayList<>();

        usuarioComorbidadesList.forEach(usuarioComorbidade -> {
            UsuarioComorbidadeDTO usuarioComorbidadeDTO = new UsuarioComorbidadeDTO();
            usuarioComorbidadeDTO.setIdComorbidade(usuarioComorbidade.getId().getCodcom());
            usuarioComorbidadeDTO.setCpfUsuario(usuarioComorbidade.getId().getCpfusu());
            usuarioComorbidadeDTOList.add(usuarioComorbidadeDTO);
        });
        return usuarioComorbidadeDTOList;
    }
}
