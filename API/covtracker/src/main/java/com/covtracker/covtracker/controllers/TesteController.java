package com.covtracker.covtracker.controllers;

import com.covtracker.covtracker.dto.TesteDTO;
import com.covtracker.covtracker.entities.Teste;
import com.covtracker.covtracker.repositories.TesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Este Controller Rest é responsável pelos endpoints relacionadas aos Testes
 * @author Bruno Schmidt
 */
@RestController
@RequestMapping(path = "/api")
public class TesteController {

    /**
     * O repositório de testes
     */
    @Autowired
    TesteRepository testeRepository;

    /**
     * Lista todos os testes
     *
     * @return A lista
     */
    @GetMapping(path = "/testes")
    public List<Teste> listAll() {
        return this.testeRepository.findAll();
    }

    /**
     * Lista de todos os testes pelo cpf do usuário
     *
     * @param cpf CPF do usuário
     * @return A lista
     */
    @GetMapping(path = "/testes/findByCpf/{cpf}")
    public List<TesteDTO> listAllByCpf(@PathVariable BigDecimal cpf) {
        List<Teste> testes = testeRepository.findAllByUsuarioCpf(cpf);
        List<TesteDTO> testesDTO = new ArrayList<>();
        testes.forEach(teste -> {
            testesDTO.add(new TesteDTO(
                    teste.getId(),
                    teste.getUsuario().getCpf(),
                    teste.getResultado(),
                    teste.getDataTeste()
            ));
        });
        return testesDTO;
    }

    /**
     * Encontra um teste pelo ID
     *
     * @param id ID do teste
     * @return O teste
     */
    @GetMapping(path = "/teste/{id}")
    public ResponseEntity<Teste> getById(@PathVariable() Integer id) {
        return this.testeRepository.findById(id)
                .map(teste -> ResponseEntity.ok().body(teste))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Salva um teste
     *
     * @param teste O teste
     * @return O teste
     */
    @PostMapping(path = "/teste")
    public Teste create(@RequestBody Teste teste) {
        return testeRepository.save(teste);
    }

    /**
     * Atualiza um teste
     *
     * @param id    O id
     * @param teste O teste
     * @return O teste
     */
    @PutMapping(path = "/teste/{id}")
    public ResponseEntity<Teste> update(@PathVariable() Integer id, @RequestBody Teste teste) {
        return testeRepository.findById(id)
                .map(registro -> {
                    registro.setDataTeste(teste.getDataTeste());
                    registro.setResultado(teste.getResultado());
                    registro.setUsuario(teste.getUsuario());
                    Teste updatedTeste = testeRepository.save(registro);
                    return ResponseEntity.ok().body(updatedTeste);
                }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deleta um teste
     *
     * @param id O ID
     * @return ResponseEntity vazio
     */
    @DeleteMapping(path = "/teste/{id}")
    public ResponseEntity delete(@PathVariable() Integer id) {
        return testeRepository.findById(id)
                .map(registro -> {
                    testeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
