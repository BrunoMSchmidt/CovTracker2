package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Teste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TesteRepository extends JpaRepository<Teste, Integer> {
}