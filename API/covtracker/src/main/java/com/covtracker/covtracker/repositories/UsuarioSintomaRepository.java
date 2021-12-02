package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.UsuarioSintoma;
import com.covtracker.covtracker.entities.UsuarioSintomaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface UsuarioSintomaRepository extends JpaRepository<UsuarioSintoma, UsuarioSintomaId> {
    List<UsuarioSintoma> findAllById_Cpfusu(BigDecimal cpfusu);
}