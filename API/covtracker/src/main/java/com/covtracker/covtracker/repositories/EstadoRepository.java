package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, String> {
}