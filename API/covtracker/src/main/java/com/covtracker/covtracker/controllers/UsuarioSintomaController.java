package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.dto.UsuarioSintomaDTO;
import com.covtracker.covtracker.entities.UsuarioSintoma;
import com.covtracker.covtracker.entities.UsuarioSintomaId;
import com.covtracker.covtracker.repositories.UsuarioSintomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Este Controller Rest é responsável pelos endpoints relacionadas as relações entre usuário e sintoma
 * @Author Bruno Schmidt
 */
@RestController
@RequestMapping(path = "/api")
public class UsuarioSintomaController {

    @Autowired
    private UsuarioSintomaRepository usuarioSintomaRepository;

    /**
     * Lista todos as relações entre usuário e sintoma.
     *
     * @return A lista de relações entre usuário e sintoma.
     */
    @GetMapping(path = "/usuario-sintoma")
    public List<UsuarioSintoma> listAll() {
        return this.usuarioSintomaRepository.findAll();
    }

    /**
     * Salva relação entre usuário e sintoma.
     *
     * @param usuarioSintoma relação entre usuário e sintoma
     * @return relação entre usuário e sintoma.
     */
    @PostMapping(path = "/usuario-sintoma",consumes={"application/json"})
    public UsuarioSintoma save(@RequestBody UsuarioSintoma usuarioSintoma){
        return usuarioSintomaRepository.save(usuarioSintoma);
    }

    /**
     * Atualiza relação entre usuário e sintoma.
     *
     * @param usuarioSintoma relação entre usuário e sintoma
     * @return relação entre usuário e sintoma
     */
    @PutMapping(path = "/usuario-sintoma")
    public UsuarioSintoma update(@RequestBody UsuarioSintoma usuarioSintoma){
        return usuarioSintomaRepository.save(usuarioSintoma);
    }

    /**
     * Deleta relação entre usuário e sintoma.
     *
     * @param usuarioSintoma relação entre usuário e sintoma
     */
    @DeleteMapping(path = "/usuario-sintoma")
    public void delete(@RequestBody UsuarioSintoma usuarioSintoma){
        usuarioSintomaRepository.delete(usuarioSintoma);
    }

    /**
     * Lista relações entre usuário e sintoma pelo cpf do usuário
     *
     * @param cpf CPF do usuário
     * @return A lista
     */
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
