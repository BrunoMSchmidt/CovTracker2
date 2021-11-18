package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<Vacina, Integer> {
}