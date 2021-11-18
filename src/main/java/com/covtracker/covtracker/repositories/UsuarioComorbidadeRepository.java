package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.UsuarioComorbidade;
import com.covtracker.covtracker.entities.UsuarioComorbidadeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioComorbidadeRepository extends JpaRepository<UsuarioComorbidade, UsuarioComorbidadeId> {
}