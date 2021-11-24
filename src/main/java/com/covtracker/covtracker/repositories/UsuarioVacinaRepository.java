package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.dto.UsuarioVacinaDTO;
import com.covtracker.covtracker.entities.UsuarioVacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Collection;

public interface UsuarioVacinaRepository extends JpaRepository<UsuarioVacina, Integer> {
    Collection<? extends UsuarioVacinaDTO> findAllByUsuarioCpf(BigDecimal cpf);
}