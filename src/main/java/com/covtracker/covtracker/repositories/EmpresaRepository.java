package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface EmpresaRepository extends JpaRepository<Empresa, BigDecimal> {
}