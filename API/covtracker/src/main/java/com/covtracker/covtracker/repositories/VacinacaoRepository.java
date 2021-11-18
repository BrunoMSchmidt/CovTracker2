package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Vacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinacaoRepository extends JpaRepository<Vacinacao, Integer> {
}