package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;

@Repository()
public interface UsuarioRepository extends JpaRepository<Usuario, BigDecimal> {
    Usuario findByEmail(String email);
}
