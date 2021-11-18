package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Integer> {
}