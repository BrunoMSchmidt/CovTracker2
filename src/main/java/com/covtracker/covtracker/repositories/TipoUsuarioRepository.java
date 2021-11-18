package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
}