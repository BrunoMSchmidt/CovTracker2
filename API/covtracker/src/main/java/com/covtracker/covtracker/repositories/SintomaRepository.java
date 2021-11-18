package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Sintoma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SintomaRepository extends JpaRepository<Sintoma, Integer> {
}