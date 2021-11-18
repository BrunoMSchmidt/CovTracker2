package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.UsuarioSintoma;
import com.covtracker.covtracker.entities.UsuarioSintomaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioSintomaRepository extends JpaRepository<UsuarioSintoma, UsuarioSintomaId> {
}