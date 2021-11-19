package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface BairroRepository extends JpaRepository<Bairro, Integer> {
}