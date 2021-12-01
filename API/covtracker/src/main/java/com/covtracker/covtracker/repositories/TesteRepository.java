package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Teste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TesteRepository extends JpaRepository<Teste, Integer> {
    List<Teste> findAllByUsuarioCpf(BigDecimal cpf);
}