package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Autenticacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface AutenticacaoRepository extends JpaRepository<Autenticacao, BigDecimal> {
}