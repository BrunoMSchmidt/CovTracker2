package com.covtracker.covtracker.repositories;

import com.covtracker.covtracker.entities.Cidade;
import com.covtracker.covtracker.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    Cidade[] findByEstadoSigla(String sigla);
}