package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}