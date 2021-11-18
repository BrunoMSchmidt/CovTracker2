package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Comorbidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComorbidadeRepository extends JpaRepository<Comorbidade, Integer> {
}