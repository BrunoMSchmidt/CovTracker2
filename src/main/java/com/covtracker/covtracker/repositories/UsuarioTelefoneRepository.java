package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.UsuarioTelefone;
import com.covtracker.covtracker.entities.UsuarioTelefoneId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioTelefoneRepository extends JpaRepository<UsuarioTelefone, UsuarioTelefoneId> {
}