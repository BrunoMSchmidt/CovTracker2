package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.dto.UsuarioSintomaDTO;
import com.covtracker.covtracker.entities.UsuarioSintoma;
import com.covtracker.covtracker.repositories.UsuarioSintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    public List<UsuarioSintomaDTO> listAllByCpf(@PathVariable BigDecimal cpf) {
        List<UsuarioSintoma> usuarioSintomaList = this.usuarioSintomaRepository.findAllById_Cpfusu(cpf);
        List<UsuarioSintomaDTO> usuarioSintomaDTOList = new ArrayList<>();

        usuarioSintomaList.forEach(usuarioSintoma -> {
            UsuarioSintomaDTO usuarioSintomaDTO = new UsuarioSintomaDTO();
            usuarioSintomaDTO.setCpfUsuario(usuarioSintoma.getId().getCpfusu());
            usuarioSintomaDTO.setIdSintoma(usuarioSintoma.getId().getCodsin());
            usuarioSintomaDTO.setDataFim(usuarioSintoma.getDataFim());
            usuarioSintomaDTO.setDataInicio(usuarioSintoma.getDataInicio());
            usuarioSintomaDTO.setIntensidade(usuarioSintoma.getIntensidade());
            usuarioSintomaDTOList.add(usuarioSintomaDTO);
        });
        return usuarioSintomaDTOList;
    }

}
