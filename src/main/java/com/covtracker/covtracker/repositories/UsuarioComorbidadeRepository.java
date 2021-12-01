package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.UsuarioComorbidade;
import com.covtracker.covtracker.entities.UsuarioComorbidadeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface UsuarioComorbidadeRepository extends JpaRepository<UsuarioComorbidade, UsuarioComorbidadeId> {
    List<UsuarioComorbidade> findAllById_Cpfusu(BigDecimal cpf);
}